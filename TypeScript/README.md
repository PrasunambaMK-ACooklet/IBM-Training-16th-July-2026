# Banking Application — TypeScript Mini Project

A small, flat-folder TypeScript project (no subfolders) demonstrating core
TypeScript concepts using a banking domain — ready to drop straight into a
GitHub repo.

## How to run

```bash
npm install
npm run build     # compiles all .ts files into ./dist using tsconfig.json
npm start         # runs the compiled entry point (dist/06-app.js)

# or, in one step during development:
npm run dev

# or, to recompile automatically on every save:
npm run watch
```

Requires Node.js and npm. No Angular CLI or browser needed — this project
runs on plain Node.js to keep the focus on TypeScript itself.

## Topic → File index

| Topic | File |
|---|---|
| Introduction to TypeScript and Why It Matters for Angular | `01-introduction-and-why-typescript-for-angular.ts` |
| Basic Types and Type Annotations | `02-basic-types-and-type-annotations.ts` |
| Interfaces and Type Aliases | `03-interfaces-and-type-aliases.ts` |
| Classes, Access Modifiers and Generics Basics | `04-classes-access-modifiers-and-generics.ts` |
| Compiling TypeScript and tsconfig Basics | `05-compiling-typescript-and-tsconfig-basics.ts`, `tsconfig.json` |
| (entry point tying it all together) | `06-app.ts` |

## Project structure

```
BankingTS/
├── 01-introduction-and-why-typescript-for-angular.ts
├── 02-basic-types-and-type-annotations.ts
├── 03-interfaces-and-type-aliases.ts   (Customer, BankAccount, Transaction, PaymentInstruction)
├── 04-classes-access-modifiers-and-generics.ts   (Account, SavingsAccount, CurrentAccount, Repository<T>)
├── 05-compiling-typescript-and-tsconfig-basics.ts
├── 06-app.ts                            (entry point / main())
├── tsconfig.json
├── package.json
└── README.md
```

Files 1–5 each run their own small demo when imported (via top-level
`console.log` calls) and also export the types/classes that later files
build on — `06-app.ts` imports all of them and runs one final end-to-end
scenario.

## Why this maps directly to Angular

Everything here — typed interfaces for domain models, classes with access
modifiers, generics for reusable, type-safe containers — is exactly what you
write inside Angular **services** (`@Injectable`) and **components**
(`@Component`). This project intentionally has zero Angular/browser
dependencies so you can focus purely on the TypeScript language itself
before adding a framework on top.
