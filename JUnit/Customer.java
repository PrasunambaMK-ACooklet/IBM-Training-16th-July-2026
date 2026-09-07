import java.util.Objects;

/**
 * Simple domain class representing a bank customer.
 * Kept deliberately small and framework-free so it's easy to unit test.
 */
public class Customer {

    private final String customerId;
    private final String fullName;

    public Customer(String customerId, String fullName) {
        if (fullName == null || fullName.isBlank()) {
            throw new IllegalArgumentException("Customer name cannot be blank");
        }
        this.customerId = customerId;
        this.fullName = fullName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customer)) return false;
        Customer other = (Customer) o;
        return Objects.equals(customerId, other.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customerId);
    }

    @Override
    public String toString() {
        return "Customer{" + customerId + ", " + fullName + "}";
    }
}
