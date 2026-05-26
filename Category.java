package supermarket_system;

import java.util.Objects;

public class Category {
    private final String name;
    private final double discountRate;

    public Category(String name) {
        this(name, 0.0);
    }

    public Category(String name, double discountRate) {
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
        if (discountRate < 0.0 || discountRate > 1.0) {
            throw new IllegalArgumentException("discountRate must be between 0.0 and 1.0");
        }
        this.discountRate = discountRate;
    }

    public String getName() {
        return name;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public double getEffectivePrice(double basePrice) {
        if (basePrice < 0.0) {
            throw new IllegalArgumentException("basePrice cannot be negative");
        }
        return basePrice * (1.0 - discountRate);
    }

    @Override
    public String toString() {
        return name;
    }
}
