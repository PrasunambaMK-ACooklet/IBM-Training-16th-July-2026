/**
 * FILE 2 of 6
 * TOPIC: Basic Types and Type Annotations
 *
 * A type annotation is the `: Type` you write after a variable, parameter,
 * or return value, telling the compiler exactly what's allowed there.
 */

// ---- primitive types ----
let accountNumber: string = "SB-100234567";
let balance: number = 152345.75; // TypeScript has ONE numeric type: number
let isActive: boolean = true;

// ---- array types (two equivalent syntaxes) ----
let recentDeposits: number[] = [1500, 2300.5, 999.99];
let branchCodes: Array<string> = ["MUM01", "DEL02", "BLR03"];

// ---- tuple: a FIXED-length array where each position has its own type ----
// Here: [accountNumber, balance, isVip]
let accountSummary: [string, number, boolean] = ["SB-100234567", 152345.75, true];
console.log("Tuple element 0 (string):", accountSummary[0]);
console.log("Tuple element 2 (boolean):", accountSummary[2]);

// ---- enum: a named set of constants, similar to a Java enum ----
enum AccountType {
    Savings = "SAVINGS",
    Current = "CURRENT",
    FixedDeposit = "FIXED_DEPOSIT",
}
let myAccountType: AccountType = AccountType.Savings;
console.log("Account type enum value:", myAccountType);

// ---- union types: a value that can be one of SEVERAL specific types ----
let transactionId: string | number = "TXN-001";
transactionId = 1001; // also legal - it's still within the union
// transactionId = true; // COMPILE ERROR - boolean is not part of the union

// ---- literal types: an even narrower union of exact allowed values ----
type TransactionStatus = "PENDING" | "COMPLETED" | "FAILED";
let status: TransactionStatus = "PENDING";
// status = "CANCELLED"; // COMPILE ERROR - not one of the three allowed literals

// ---- any: opts OUT of type checking entirely (use sparingly!) ----
let legacyPayload: any = { whatever: "shape", this: 123, might: true };
legacyPayload = "now it's a string, TypeScript won't complain"; // allowed, but risky

// ---- unknown: the SAFE alternative to any - must be narrowed before use ----
let externalApiResponse: unknown = fetchSomeExternalData();
if (typeof externalApiResponse === "number") {
    // Only inside this narrowed block does TypeScript treat it as a number.
    console.log("Narrowed unknown to number:", externalApiResponse.toFixed(2));
}

// ---- void: a function that returns nothing meaningful ----
function logTransaction(message: string): void {
    console.log("[LOG]", message);
}

// ---- null and undefined as explicit, distinct types ----
let closedAccountBalance: number | null = null; // explicitly "no balance, on purpose"
let notYetAssignedManager: string | undefined = undefined;

// ---- function parameter and return type annotations ----
function calculateSimpleInterest(principal: number, ratePercent: number, years: number): number {
    return (principal * ratePercent * years) / 100;
}
console.log("Simple interest on 100000 @ 8.5% for 2 years:", calculateSimpleInterest(100000, 8.5, 2));

// ---- optional parameters (?) and default parameter values ----
function openAccount(customerName: string, openingBalance: number = 0, isVip?: boolean): string {
    const vipTag = isVip ? " (VIP)" : "";
    return `Opened account for ${customerName}${vipTag} with balance ${openingBalance}`;
}
console.log(openAccount("Asha Rao"));
console.log(openAccount("Vikram Shah", 5000, true));

function fetchSomeExternalData(): unknown {
    return 42; // pretend this came from an untyped external API
}

console.log("\nBasic types demo complete. Balance:", balance, "| Active:", isActive);
console.log("Recent deposits:", recentDeposits, "| Branches:", branchCodes);

export {};
