package com.bank.basics;

/**
 * TOPIC: Datatypes, Literals, Variables, Type Conversion, Casting & Promotion
 */
public class DataTypesDemo {

    public static void main(String[] args) {
        // ---- primitive datatypes & literals ----
        byte branchCode = 12;                 // 8-bit
        short employeeId = 3050;              // 16-bit
        int accountNumber = 100234567;        // 32-bit
        long ifscNumericForm = 400123456789L; // 64-bit, 'L' literal suffix
        float interestRateApprox = 4.5f;      // 32-bit floating point, 'f' suffix
        double accountBalance = 152345.75;    // 64-bit floating point
        char accountTypeCode = 'S';           // single 16-bit Unicode character
        boolean isActive = true;              // true/false only

        // literal forms
        int hexBranchId = 0x1A;     // hexadecimal literal
        int octalOldCode = 012;     // octal literal
        int binaryFlags = 0b1011;   // binary literal
        int readableAmount = 1_000_000; // underscores for readability

        System.out.println("Branch code: " + branchCode);
        System.out.println("Employee id: " + employeeId);
        System.out.println("Account number: " + accountNumber);
        System.out.println("IFSC numeric form: " + ifscNumericForm);
        System.out.println("Interest rate approx: " + interestRateApprox);
        System.out.println("Balance: " + accountBalance);
        System.out.println("Account type code: " + accountTypeCode);
        System.out.println("Is active: " + isActive);
        System.out.println("Hex/Octal/Binary/Underscore literals: " +
                hexBranchId + ", " + octalOldCode + ", " + binaryFlags + ", " + readableAmount);

        // ---- implicit widening (promotion): smaller type -> larger type, automatic ----
        int wholeRupees = 5000;
        double wholeRupeesAsDouble = wholeRupees; // int promoted to double, no data loss
        System.out.println("Widened int->double: " + wholeRupeesAsDouble);

        // ---- explicit narrowing (casting): larger type -> smaller type, may lose data ----
        double preciseInterest = 199.99;
        int truncatedInterest = (int) preciseInterest; // cast required, fraction is dropped
        System.out.println("Narrowed double->int (cast): " + truncatedInterest);

        // ---- arithmetic promotion: byte/short/char are promoted to int before math ----
        byte fee1 = 10;
        byte fee2 = 20;
        int totalFee = fee1 + fee2; // result of byte+byte is promoted to int
        System.out.println("Promoted byte+byte result type is int: " + totalFee);

        // var: type inference, the compiler still assigns a fixed type at compile time
        var branchName = "MG Road Branch"; // inferred as String
        System.out.println("Inferred type var: " + branchName);
    }
}
