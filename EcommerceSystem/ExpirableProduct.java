package EcommerceSystem;

// Represents a product that can expire
public class ExpirableProduct extends Product {
    private boolean expired;

    public ExpirableProduct(String name, double price, int quantity, boolean expired) {
        super(name, price, quantity);
        this.expired = expired;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }
}
