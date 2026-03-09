package retirement_investment_calculator;

import java.math.BigDecimal;

public class Account {
	private int age;
	private BigDecimal balance;
	private BigDecimal annualInterestRate;
	private CompoundFrequency compoundFrequency;
	
	public Account(int age, BigDecimal balance, BigDecimal annualInterestRate, CompoundFrequency compoundFrequency)
	{
		this.age = age;
		this.balance = balance;
		this.annualInterestRate = annualInterestRate;
		this.compoundFrequency = compoundFrequency;
	}
	
	public Account (Account other)
	{
		this.age = other.age;
		this.balance = new BigDecimal(other.balance.toString());
		this.annualInterestRate = new BigDecimal(other.annualInterestRate.toString());
		this.compoundFrequency = other.compoundFrequency;
	}
	
	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}
	
	public BigDecimal getBalance() {return balance;}
	public void setBalance(BigDecimal balance) {this.balance = balance;}
	
	public BigDecimal getAnnualInterestRate() {return annualInterestRate;}
	public void setAnnualInterestRate(BigDecimal annualInterestRate) {this.annualInterestRate = annualInterestRate;}
	
	public CompoundFrequency getCompoundFrequency() {return compoundFrequency;}
	public void setCompoundFrequency(CompoundFrequency compoundFrequency) {this.compoundFrequency = compoundFrequency;}
}
