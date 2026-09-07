package com.bank.access;

import com.bank.access.helper.BranchOffice;

/**
 * TOPIC: Access Modifiers, Packages
 *
 * Java has four access levels, from most to least restrictive:
 *   private            -> only within the declaring class
 *   (no modifier)      -> "package-private", within the same package only
 *   protected          -> same package, PLUS subclasses in other packages
 *   public             -> everywhere
 *
 * RegionalBranchOffice below is a SUBCLASS of BranchOffice living in a
 * DIFFERENT package, which is exactly the case where "protected" grants
 * access but package-private does not.
 */
class RegionalBranchOffice extends BranchOffice {
    void printProtectedFieldFromSubclass() {
        // Allowed: protected members ARE visible to subclasses, even across packages.
        System.out.println("Subclass can see protected field: " + regionCode);

        // NOT allowed (would fail to compile) - package-private in a different package:
        // System.out.println(internalMemoCode);
    }
}

public class AccessModifiersDemo {

    public static void main(String[] args) {
        BranchOffice branch = new BranchOffice();

        // public - accessible from anywhere, any package
        System.out.println(branch.branchName);

        // protected - NOT directly accessible here because AccessModifiersDemo
        // is neither in the same package nor a subclass of BranchOffice.
        // System.out.println(branch.regionCode); // <-- would NOT compile

        // package-private - NOT accessible here either, different package.
        // System.out.println(branch.internalMemoCode); // <-- would NOT compile

        // private - NOT accessible here, only exposed through a public method.
        // System.out.println(branch.managerPassword); // <-- would NOT compile
        System.out.println(branch.revealManagerPasswordViaMethod());

        // A subclass in another package CAN reach the protected member.
        RegionalBranchOffice regional = new RegionalBranchOffice();
        regional.printProtectedFieldFromSubclass();
    }
}
