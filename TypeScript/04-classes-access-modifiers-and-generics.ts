/**
 * FILE 4 of 6
 * TOPIC: Classes, Access Modifiers and Generics Basics
 */

import { BankAccount, Customer, Transaction, TransactionType } from "./03-interfaces-and-type-aliases";

// ---- CLASS implementing an interface, with ACCESS MODIFIERS ----
// public    -> accessible from anywhere (the default if you omit a modifier)
// private   -> accessible ONLY inside this exact class
// protected -> accessible inside this class AND subclasses
// readonly  -> can be set in the constructor, never reassigned after that
export abstract class Account implements BankAccount {
    public readonly accountNumber: string;
    public readonly owner: Customer;
    private _balance: number; // leading underscore is just a convention, "_" has no special meaning

    protected static accountsCreatedCount: number = 0;

    protected constructor(accountNumber: string, owner: Customer, openingBalance: number) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this._balance = openingBalance;
        Account.accountsCreatedCount++;
    }

    // A "getter" - lets callers read `account.balance` like a property,
    // while the real, mutable field (_balance) stays private.
    public get balance(): number {
        return this._balance;
    }

    public static getAccountsCreatedCount(): number {
        return Account.accountsCreatedCount;
    }

    public deposit(amount: number): void {
        if (amount <= 0) {
            throw new Error("Deposit amount must be positive");
        }
        this._balance += amount;
    }

    public withdraw(amount: number): void {
        if (amount <= 0) {
            throw new Error("Withdrawal amount must be positive");
        }
        if (amount > this._balance) {
            throw new Error(`Insufficient funds in account ${this.accountNumber}`);
        }
        this._balance -= amount;
    }

    // protected: subclasses can adjust the balance directly (e.g. for
    // overdrafts); code OUTSIDE the class hierarchy cannot.
    protected adjustBalance(delta: number): void {
        this._balance += delta;
    }

    // abstract: every concrete subclass MUST provide its own implementation.
    public abstract describe(): string;
    public abstract applyMonthlyMaintenance(): void;
}

// ---- INHERITANCE: SavingsAccount extends the abstract Account class ----
export class SavingsAccount extends Account {
    private static readonly MIN_BALANCE = 1000;
    private static readonly ANNUAL_RATE_PERCENT = 4.0;

    constructor(accountNumber: string, owner: Customer, openingBalance: number) {
        super(accountNumber, owner, openingBalance); // must call super() before using "this"
    }

    // Overriding withdraw() to add the minimum-balance rule.
    public override withdraw(amount: number): void {
        if (this.balance - amount < SavingsAccount.MIN_BALANCE) {
            throw new Error(`Withdrawal would breach minimum balance of ${SavingsAccount.MIN_BALANCE}`);
        }
        super.withdraw(amount);
    }

    public applyMonthlyMaintenance(): void {
        const interest = this.balance * (SavingsAccount.ANNUAL_RATE_PERCENT / 100 / 12);
        this.deposit(interest);
    }

    public describe(): string {
        return `SavingsAccount[${this.accountNumber}, owner=${this.owner.fullName}]`;
    }
}

export class CurrentAccount extends Account {
    private static readonly OVERDRAFT_LIMIT = 5000;

    constructor(accountNumber: string, owner: Customer, openingBalance: number) {
        super(accountNumber, owner, openingBalance);
    }

    public override withdraw(amount: number): void {
        if (this.balance - amount < -CurrentAccount.OVERDRAFT_LIMIT) {
            throw new Error(`Withdrawal exceeds overdraft limit of ${CurrentAccount.OVERDRAFT_LIMIT}`);
        }
        this.adjustBalance(-amount); // protected method, allowed here because we're a subclass
    }

    public applyMonthlyMaintenance(): void {
        this.adjustBalance(-50); // flat monthly fee
    }

    public describe(): string {
        return `CurrentAccount[${this.accountNumber}, owner=${this.owner.fullName}]`;
    }
}

// ---- GENERICS: a reusable, type-safe repository that works for ANY type T ----
// <T> is a placeholder type parameter, filled in when the class is used,
// e.g. Repository<Account> or Repository<Transaction>.
export class Repository<T extends { accountNumber: string } | { id: string }> {
    private items: T[] = [];

    add(item: T): void {
        this.items.push(item);
    }

    findAll(): readonly T[] {
        return this.items;
    }

    count(): number {
        return this.items.length;
    }
}

// A generic function: works for arrays of ANY element type T, and the
// compiler still knows exactly what T is at each call site.
export function findFirstMatching<T>(items: T[], predicate: (item: T) => boolean): T | undefined {
    for (const item of items) {
        if (predicate(item)) {
            return item;
        }
    }
    return undefined;
}

// ---- Demo ----
function runClassesAndGenericsDemo(): void {
    const customer1: Customer = { customerId: "CUST-10", fullName: "Meera Iyer", email: "meera@example.com" };
    const customer2: Customer = { customerId: "CUST-11", fullName: "Arjun Nair", email: "arjun@example.com" };

    const savings = new SavingsAccount("SB-7001", customer1, 20000);
    const current = new CurrentAccount("CA-7002", customer2, 5000);

    console.log("=== Classes, Access Modifiers, Generics Demo ===");
    console.log("Accounts created so far:", Account.getAccountsCreatedCount());

    savings.deposit(2500);
    current.withdraw(8000); // allowed thanks to overdraft

    const accounts: Account[] = [savings, current];
    for (const account of accounts) {
        account.applyMonthlyMaintenance(); // polymorphism: each subclass's own version runs
        console.log(account.describe(), "-> balance:", account.balance.toFixed(2));
    }

    // Using the generic Repository<T> with Account
    const accountRepository = new Repository<Account>();
    accountRepository.add(savings);
    accountRepository.add(current);
    console.log("Accounts stored in generic repository:", accountRepository.count());

    // Using the generic Repository<T> with a completely different type: Transaction
    const transaction: Transaction = {
        id: "TXN-9001",
        accountNumber: savings.accountNumber,
        type: "DEPOSIT" as TransactionType,
        amount: 2500,
        timestamp: new Date(),
    };
    const transactionRepository = new Repository<Transaction>();
    transactionRepository.add(transaction);
    console.log("Transactions stored in generic repository:", transactionRepository.count());

    // Using the generic function findFirstMatching<T>
    const found = findFirstMatching(accounts, (a) => a.balance > 10000);
    console.log("First account with balance > 10000:", found?.describe() ?? "none found");
}

runClassesAndGenericsDemo();
