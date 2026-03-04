package retirement_investment_calculator;

public abstract class Account {
	private int age;
	private double balance;
	private double annualInterestRate;
	private int compoundingFrequency; // 1 == Annually, 2 == Monthly, 3 == Daily
	
	public Account(int age, double balance, double annualInterestRate, int compoundingFrequency)
	{
		this.age = age;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
		this.compoundingFrequency = compoundingFrequency;
	}
	
	public Account (Account other)
	{
		this.age = other.age;
		this.balance = other.balance;
		this.annualInterestRate = other.annualInterestRate;
		this.compoundingFrequency = other.compoundingFrequency;
	}
	
}
