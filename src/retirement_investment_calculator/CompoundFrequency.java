package retirement_investment_calculator;

public enum CompoundFrequency {
	
	DAILY("Daily", 365),
	MONTHLY("Monthly", 12),
	QUARTERLY("Quarterly", 4),
	ANNUALY("Annualy", 1);
	
	public final String name;
	public final int frequency;
	
	private CompoundFrequency(String name, int frequency)
	{
		this.name = name;
		this.frequency = frequency;
	}
}