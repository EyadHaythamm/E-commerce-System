package EcommerceSystem;

// Represents a product that can expire and be shipped
public class ExpirableShippableProduct extends ExpirableProduct implements Shippable {
    private double weight;

    public ExpirableShippableProduct(String name, double price, int quantity, boolean expired, double weight) {
        super(name, price, quantity, expired);
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }
}

