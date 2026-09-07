import { Account } from "./account";

let accounts: Account[] = [
    { id: 1, name: "Prasunamba", balance: 5000 },
    { id: 2, name: "Meher", balance: 3000 }
];

export const getAccounts = (): Account[] => accounts;

export const deposit = (id: number, amount: number): Account | undefined => {
    const acc = accounts.find(a => a.id === id);
    if (acc) acc.balance += amount;
    return acc;
};

export const withdraw = (id: number, amount: number): Account | undefined => {
    const acc = accounts.find(a => a.id === id);
    if (acc && acc.balance >= amount) acc.balance -= amount;
    return acc;
};
