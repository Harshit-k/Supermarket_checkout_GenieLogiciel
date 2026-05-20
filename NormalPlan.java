package supermarket_system;

public class NormalPlan implements CustomerPlan {
	@Override
    public double getDiscount(double total) { return 0.0; }

    @Override
    public double getAnnualFee()             { return 0.0; }

    @Override
    public String getPlanName()               { return "normal"; }
}
