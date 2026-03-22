package retirement_investment_calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class InvestmentSimulator {
	
	private Account account;
	
	public InvestmentSimulator (Account account)
	{
		this.account = account;
	}
	
	public ArrayList<String> runSimulation(int retirementAge, double annualContribution, double annualContributionIncrease)
	{
		ArrayList<String> res = new ArrayList();
		Account simAccount = new Account(account);
		
		// setup
		int compoundSteps = simAccount.getCompoundFrequency().frequency;
		BigDecimal stepRate = simAccount.getAnnualInterestRate().divide(new BigDecimal(compoundSteps * 100), 100, RoundingMode.HALF_EVEN).add(new BigDecimal(1));
		BigDecimal totalContributions = new BigDecimal(0);
		BigDecimal totalInterest = new BigDecimal(0);
		
		// print initial state
		res.add("Year-by-Year Pojection");
		res.add( String.format("%-5s | %-20s | %-20s | %-20s | %-20s%n", 
				"Age", "Start Balance", "Contributions", "Interest Earned", "End") );
		
		// run simulation
		for (int year = simAccount.getAge() + 1; year <= retirementAge; year++)
		{
			BigDecimal start = simAccount.getBalance();
			simAccount.setBalance((new BigDecimal(annualContribution)).add(simAccount.getBalance()) );
			
			BigDecimal interest = simAccount.getBalance();
			for (int step = 0; step < compoundSteps; step++)
			{
				interest = interest.multiply(stepRate);
			}
			interest = interest.subtract(simAccount.getBalance());
			
			// Print Row
			res.add( String.format( "%-5d | $%-20.2f | $%-20.2f | $%-20.2f | $%-20.2f%n",
					year, start, annualContribution, interest, interest.add(simAccount.getBalance())) );
			
			// increment
			totalContributions = totalContributions.add(new BigDecimal(annualContribution));
			totalInterest = totalInterest.add(interest);
			simAccount.setBalance(simAccount.getBalance().add(interest));
			annualContribution *= (1 + (annualContributionIncrease / 100));
		}
		
		// print summary
		res.add("");
		res.add("Summary");
		res.add( String.format("Total Contributions: $%-20.2f", totalContributions) );
		res.add( String.format("Total Interest Earned: $%-20.2f", totalInterest) );
		res.add( String.format("Ending Balance: $%-20.2f", simAccount.getBalance()) );
		
		return res;
	}
	
}
