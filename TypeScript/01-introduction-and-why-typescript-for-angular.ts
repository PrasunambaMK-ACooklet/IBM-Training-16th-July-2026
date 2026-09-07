/**
 * FILE 1 of 6
 * TOPIC: Introduction to TypeScript and Why It Matters for Angular
 *
 * WHAT IS TYPESCRIPT?
 * TypeScript is a superset of JavaScript: every valid .js file is already
 * valid TypeScript. What TypeScript ADDS is a static type system that is
 * checked at COMPILE TIME (before the code ever runs), plus modern
 * language features that get compiled ("transpiled") down to plain
 * JavaScript that any browser or Node.js runtime can execute.
 *
 *      YourCode.ts  --(tsc, the TypeScript compiler)-->  YourCode.js
 *
 * WHY DOES THIS MATTER FOR A BANKING APPLICATION SPECIFICALLY?
 * In plain JavaScript, this compiles and runs fine, then blows up at
 * runtime - possibly in front of a real customer:
 *
 *   function transfer(fromAccount, toAccount, amount) {
 *       fromAccount.balance -= amount; // typo-prone, nothing stops
 *       toAccount.blance += amount;    // "blance" (typo!) from being accepted
 *   }
 *
 * With TypeScript, the exact same mistake is caught the moment you type it,
 * red-squiggly-underlined in your editor, and tsc refuses to even compile it.
 * That is enormously valuable for financial code, where a silent typo could
 * mean money silently going to the wrong place.
 *
 * WHY ANGULAR SPECIFICALLY?
 * Angular (unlike some other frontend frameworks) is written IN TypeScript
 * and is designed around it from the ground up:
 *   - Components, Services, and Modules are all TypeScript classes with
 *     decorators (@Component, @Injectable, @NgModule) - decorators are a
 *     TypeScript/JavaScript feature, and Angular relies on the TYPE
 *     information for its Dependency Injection system to know WHAT to inject.
 *   - Angular's CLI (`ng new`, `ng generate`) scaffolds .ts files by default.
 *   - Templates get type-checked against your component's TypeScript class
 *     properties, catching typos like `{{ acount.balance }}` at build time.
 *   - Large Angular codebases (like a real banking front-end with dozens of
 *     screens, forms, and shared services) become far safer to refactor,
 *     because the compiler tells you every single place a change breaks.
 *
 * This mini-project builds a small, framework-free "banking domain" in
 * plain TypeScript first (files 2-6) - the exact same kind of
 * classes/interfaces you would later inject into Angular services and
 * bind to Angular component templates.
 */

console.log("=== Introduction to TypeScript for a Banking Application ===");
console.log("This file has no runtime logic of its own - see the other files");
console.log("in this project for basic types, interfaces, classes, and generics.");

// A tiny taste of what's coming: this line is fully type-checked.
const bankName: string = "Acme National Bank";
console.log(`Welcome to ${bankName}'s TypeScript banking mini-project.`);

// Uncommenting the next line would cause a COMPILE ERROR, not a runtime
// surprise - try it! ("Type 'number' is not assignable to type 'string'.")
// const brokenBankName: string = 12345;

export {};
