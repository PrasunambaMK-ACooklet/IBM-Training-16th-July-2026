/**
 * FILE 6 of 6
 * Main entry point for the banking TypeScript mini-project.
 *
 * Importing the other files runs their top-level demo code (each file logs
 * its own section). This file then runs one more end-to-end scenario that
 * pulls together interfaces (file 3) and classes/generics (file 4).
 *
 * Run the whole project with:  npm run build && npm start
 * (see package.json / tsconfig.json in this same folder)
 */

import "./01-introduction-and-why-typescript-for-angular";
import "./02-basic-types-and-type-annotations";
import "./03-interfaces-and-type-aliases";
import { SavingsAccount, Repository } from "./04-classes-access-modifiers-and-generics";
import "./05-compiling-typescript-and-tsconfig-basics";
import { Customer, Transaction } from "./03-interfaces-and-type-aliases";

function main(): void {
    console.log("\n=== End-to-end mini scenario using types, interfaces, classes & generics together ===");

    const customer: Customer = {
        customerId: "CUST-99",
        fullName: "Neha Kulkarni",
        email: "neha@example.com",
    };

    const account = new SavingsAccount("SB-9999", customer, 15000);
    account.deposit(1000);

    const transactionLog = new Repository<Transaction>();
    transactionLog.add({
        id: "TXN-1",
        accountNumber: account.accountNumber,
        type: "DEPOSIT",
        amount: 1000,
        timestamp: new Date(),
    });

    console.log(account.describe(), "-> final balance:", account.balance.toFixed(2));
    console.log("Transactions logged:", transactionLog.count());
    console.log("\nAll 5 topics demonstrated. See each numbered file for details.");
}

main();
