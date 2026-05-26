package supermarket_system;

import java.util.Objects;

public class Category {
    private final String name;

    public Category(String name) {
        this.name = Objects.requireNonNull(name, "name cannot be null").trim();
        if (this.name.isEmpty()) {
            throw new IllegalArgumentException("name cannot be empty");
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
