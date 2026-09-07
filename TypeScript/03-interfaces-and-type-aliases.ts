/**
 * FILE 3 of 6
 * TOPIC: Interfaces and Type Aliases
 *
 * Both `interface` and `type` describe the SHAPE of data, and TypeScript
 * checks objects against them STRUCTURALLY (if it has the right shape, it
 * satisfies the type - no explicit "implements" keyword needed for plain
 * objects, unlike Java). The two overlap a lot; general guidance:
 *   - Prefer `interface` for object shapes that classes might implement or
 *     that might need to be extended/merged later (e.g. Angular component
 *     @Input() props, service contracts).
 *   - Prefer `type` for unions, tuples, function types, or simple aliases.
 */

// ---- INTERFACE: describes the shape of a Customer object ----
export interface Customer {
    readonly customerId: string; // readonly: cannot be reassigned after creation
    fullName: string;
    email: string;
}

// ---- Interfaces can EXTEND other interfaces (like inheritance for shapes) ----
export interface VipCustomer extends Customer {
    relationshipManager: string;
    perks: string[];
}

// ---- INTERFACE describing the shape every account object must have ----
export interface BankAccount {
    accountNumber: string;
    owner: Customer;
    balance: number;
    deposit(amount: number): void;
    withdraw(amount: number): void;
}

// ---- TYPE ALIAS for a union of specific string literals ----
export type TransactionType = "DEPOSIT" | "WITHDRAWAL" | "TRANSFER_IN" | "TRANSFER_OUT";

// ---- TYPE ALIAS describing an object shape (equivalent in spirit to an interface here) ----
export type Transaction = {
    id: string;
    accountNumber: string;
    type: TransactionType;
    amount: number;
    timestamp: Date;
};

// ---- TYPE ALIAS for a function's shape - useful for callback parameters ----
export type TransactionListener = (transaction: Transaction) => void;

// ---- TYPE ALIAS combining (union of) two different object shapes ----
export type PaymentInstruction =
    | { kind: "CARD"; maskedCardNumber: string; amount: number }
    | { kind: "UPI"; upiId: string; amount: number };

function describePaymentInstruction(instruction: PaymentInstruction): string {
    // TypeScript narrows the union based on the "kind" discriminant field.
    switch (instruction.kind) {
        case "CARD":
            return `Card payment of ${instruction.amount} using ${instruction.maskedCardNumber}`;
        case "UPI":
            return `UPI payment of ${instruction.amount} via ${instruction.upiId}`;
    }
}

// ---- Demo: building plain objects that satisfy these shapes ----
const customer: Customer = {
    customerId: "CUST-01",
    fullName: "Ishaan Verma",
    email: "ishaan@example.com",
};

const vip: VipCustomer = {
    customerId: "CUST-02",
    fullName: "Priya Nair",
    email: "priya@example.com",
    relationshipManager: "Rohan Gupta",
    perks: ["Free locker", "Priority queue"],
};

const cardPayment: PaymentInstruction = { kind: "CARD", maskedCardNumber: "**** 4242", amount: 2500 };
const upiPayment: PaymentInstruction = { kind: "UPI", upiId: "ramesh@upi", amount: 499 };

console.log("=== Interfaces and Type Aliases Demo ===");
console.log("Customer:", customer);
console.log("VIP customer perks:", vip.perks.join(", "));
console.log(describePaymentInstruction(cardPayment));
console.log(describePaymentInstruction(upiPayment));

// customer.customerId = "CHANGED"; // COMPILE ERROR - customerId is readonly
