package com.bank.access.helper;

/**
 * TOPIC: Access Modifiers, Packages
 *
 * Lives in a different package (com.bank.access.helper) from the class that
 * will use it (com.bank.access.AccessModifiersDemo), specifically so we can
 * show how each access modifier behaves ACROSS package boundaries.
 */
public class BranchOffice {

    public String branchName = "Public: anyone, any package, can read this.";

    protected String regionCode = "Protected: visible to subclasses and same package.";

    String internalMemoCode = "Package-private (no modifier): only visible inside com.bank.access.helper.";

    private String managerPassword = "Private: visible ONLY inside this exact class.";

    public String revealManagerPasswordViaMethod() {
        // A private field can still be exposed deliberately through a public method
        return "Manager password (only accessible via this public method): " + managerPassword;
    }
}
