package supermarket_system;

public class Customer {
	// ── Fields ────────────────────────────────────
    private final int    id;
    private final String firstName;
    private final String surname;
    private final String username;
    private       String address;
    private       CustomerPlan plan;

    // ── Constructor ───────────────────────────────
    public Customer(int id, String firstName, String surname,
                    String username, String address) {
        this.id        = id;
        this.firstName = firstName;
        this.surname   = surname;
        this.username  = username;
        this.address   = address;
        this.plan      = new NormalPlan(); // default plan
    }

    // ── Plan management ───────────────────────────
    /**
     * Subscribes the customer to a new plan.
     * Returns the annual fee charged (caller deducts from balance).
     */
    public double subscribeToPlan(CustomerPlan newPlan) {
        this.plan = newPlan;
        return newPlan.getAnnualFee();
    }

    // ── Discount helpers ──────────────────────────
    public double applyDiscount(double subtotal) {
        double rate = plan.getDiscount(subtotal);
        return subtotal * (1.0 - rate);
    }

    public double applyDeliveryDiscount(double deliveryCost) {
        return deliveryCost * plan.getDeliveryCostMultiplier();
    }

    // ── Getters ───────────────────────────────────
    public int          getId()          { return id; }
    public String       getFirstName()   { return firstName; }
    public String       getSurname()     { return surname; }
    public String       getUsername()    { return username; }
    public String       getAddress()     { return address; }
    public CustomerPlan getPlan()        { return plan; }

    // ── Setters ───────────────────────────────────
    public void setAddress(String a)     { this.address  = a; }

    @Override
    public String toString() {
        return String.format("[%d] %s %s (%s)",
                id, firstName, surname, plan.getPlanName());
    }
	
	
	
}
