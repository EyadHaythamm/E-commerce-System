package EcommerceSystem;

// Handles shipping and calculates total package weight
import java.util.*;

public class ShippingService {
    public static void ship(List<Shippable> items) {
        if (items.isEmpty()) return;

        System.out.println("** Shipment notice **");

        // Group items by name and calculate total weight per product
        Map<String, Double> groupedItems = new LinkedHashMap<>();
        for (Shippable item : items) {
            groupedItems.merge(item.getName(), item.getWeight(), Double::sum);
        }

        double totalWeight = 0;
        for (Map.Entry<String, Double> entry : groupedItems.entrySet()) {
            String name = entry.getKey();
            double weight = entry.getValue();
            long count = items.stream().filter(i -> i.getName().equals(name)).count();
            System.out.printf("%dx %s %.1fg\n", count, name, weight * 1000);
            totalWeight += weight;
        }

        System.out.printf("Total package weight %.1fkg\n", totalWeight);
    }
}
