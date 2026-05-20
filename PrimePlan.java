package supermarket_system;

public class PrimePlan implements CustomerPlan {
	private static final double THRESHOLD  = 50.0;
    private static final double DISCOUNT   = 0.20;

    @Override
    public double getDiscount(double total) {
        return total >= THRESHOLD ? DISCOUNT : 0.0;
    }

    @Override
    public double getAnnualFee()  { return 50.0; }

    @Override
    public String getPlanName()   { return "prime"; }

    @Override
    public double getDeliveryCostMultiplier() { return 0.5; }
}
