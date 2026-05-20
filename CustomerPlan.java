package supermarket_system;

public interface CustomerPlan {
	
	/** Returns the discount multiplier (0.0 = no discount, 0.2 = 20% off). */
    double getDiscount(double totalBeforeDiscount);

    /** Returns the annual subscription fee in euros. */
    double getAnnualFee();

    /** Human-readable plan name, e.g. "prime". */
    String getPlanName();

    /** Delivery cost multiplier (1.0 = full price, 0.5 = half, 0.0 = free). */
    default double getDeliveryCostMultiplier() { return 1.0; }
}
