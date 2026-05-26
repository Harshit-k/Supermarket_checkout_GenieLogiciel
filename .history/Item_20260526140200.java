package supermarket_system;

import java.util.Objects;

public class Item {
    private final String id;
    private final String name;
    private final Category category;
    private final double unitPrice;
    private int stock;

    public Item(String id, String name, Category category, double unitPrice, int initialStock) {
        this.id = Objects.requireNonNull(id, "id cannot be null").trim();
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
        this.category = Objects.requireNonNull(category, "category cannot be null");

        if (this.id.isEmpty()) {
            throw new IllegalArgumentException("id cannot be empty");
        }
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        if (unitPrice < 0.0) {
            throw new IllegalArgumentException("unitPrice cannot be negative");
        }
        if (initialStock < 0) {
            throw new IllegalArgumentException("initialStock cannot be negative");
        }

        this.unitPrice = unitPrice;
        this.stock = initialStock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getStock() {
        return stock;
    }

    public void removeFromStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        if (quantity > stock) {
            throw new IllegalStateException("insufficient stock for item " + id);
        }
        stock -= quantity;
    }

    public void addToStock(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be > 0");
        }
        stock += quantity;
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%.2f EUR, stock=%d)", id, name, unitPrice, stock);
    }
}
