/**
 * FILE 5 of 6
 * TOPIC: Compiling TypeScript and tsconfig Basics
 *
 * TypeScript code doesn't run directly in a browser or in Node.js - it must
 * first be COMPILED ("transpiled") into plain JavaScript by the TypeScript
 * compiler, `tsc`. This file explains the pipeline; `tsconfig.json` in this
 * same folder is a REAL, working config you can point `tsc` at.
 *
 * ---- THE BASIC COMMANDS ----
 *
 *   npm install -D typescript        Installs the compiler as a dev dependency.
 *   npx tsc --init                   Generates a starter tsconfig.json (already done for you here).
 *   npx tsc                          Compiles every file tsconfig.json includes, using its settings.
 *   npx tsc --watch                  Recompiles automatically on every save - great during development.
 *   npx tsc 06-app.ts                Compiles a single file, ignoring tsconfig.json (quick one-offs only).
 *
 * ---- WHAT tsconfig.json ACTUALLY CONTROLS ----
 * Open tsconfig.json in this folder alongside this file. The key settings
 * used there, and why each one matters for a real project:
 *
 *   "target"          Which JavaScript version to compile DOWN to
 *                      (e.g. "ES2020"). Older target = broader browser
 *                      support, but larger/less efficient output code.
 *
 *   "module"          Which module system the compiled .js files use to
 *                      import/export between each other - "commonjs" for
 *                      plain Node.js (what this project uses), "esnext"
 *                      for modern bundlers, "es2015" is what Angular's own
 *                      build tooling typically targets internally.
 *
 *   "strict"          Turns on the FULL set of strict type-checking flags
 *                      at once (strictNullChecks, noImplicitAny, etc).
 *                      Always keep this "true" for new projects - it's the
 *                      single setting that catches the most real bugs,
 *                      like accidentally using a possibly-null value.
 *
 *   "outDir"          Where the compiled .js output files are written
 *                      (keeps compiled output separate from source .ts files).
 *
 *   "rootDir"          Where tsc expects your source files to start from.
 *
 *   "esModuleInterop"  Smooths over differences between CommonJS and ES
 *                      module import styles - almost always left "true".
 *
 *   "include"/"exclude" Which files/folders tsc should (or should not)
 *                      compile - e.g. always exclude "node_modules".
 *
 * ---- WHY ANGULAR CARES ABOUT THIS FILE SPECIFICALLY ----
 * Every Angular CLI project ships with its own tsconfig.json (plus
 * tsconfig.app.json / tsconfig.spec.json that extend it). Angular's build
 * system (`ng build`, `ng serve`) uses these exact settings to compile your
 * whole app - so understanding tsconfig.json here is directly transferable.
 *
 * ---- COMPILE-TIME vs RUNTIME, MADE CONCRETE ----
 * Below, `compileTimeOnlyCheck` only matters to the COMPILER; once compiled,
 * the type annotations are ERASED entirely - they do not exist in the
 * output JavaScript and cost nothing at runtime.
 */

function compileTimeOnlyCheck(accountBalance: number): string {
    // Try changing accountBalance's type above to `string` - tsc will
    // refuse to compile the line below, at COMPILE TIME, before anything runs.
    return `Balance is ${accountBalance.toFixed(2)}`;
}

console.log("=== Compiling TypeScript & tsconfig Basics ===");
console.log(compileTimeOnlyCheck(45230.5));
console.log("Open tsconfig.json in this folder to see the real compiler configuration.");
console.log("Run `npm install` then `npm run build` (see package.json) to compile this whole project.");

export {};
