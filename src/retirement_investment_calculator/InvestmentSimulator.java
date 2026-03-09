package retirement_investment_calculator;

import java.math.BigDecimal;
import java.util.ArrayList;

public class InvestmentSimulator {
	
	Account account;
	
	public InvestmentSimulator (Account Account)
	{
		this.account = account;
	}
	
	public ArrayList<String> runSimulation(int retirementAge, double annualContribution, double annualContributionIncrease)
	{
		ArrayList<String> res = new ArrayList();
		Account simAccount = new Account(account);
		
		// setup
		int compoundSteps = simAccount.getCompoundFrequency().frequency;
		BigDecimal stepRate = simAccount.getAnnualInterestRate().divide(new BigDecimal(compoundSteps * 100)).add(new BigDecimal(1));
		BigDecimal totalContributions = new BigDecimal(0);
		BigDecimal totalInterest = new BigDecimal(0);
		
		// print initial state
		System.out.println("Year-by-Year Pojection");
		System.out.printf("%-5s | %-20s | %-20s | %-20s | %-20s%n", 
				"Age", "Start Balance", "Contributions", "Interest Earned", "End");
		
		// run simulation
		for (int year = simAccount.getAge() + 1; year <= retirementAge; year++)
		{
			BigDecimal start = simAccount.getBalance();
			simAccount.setBalance((new BigDecimal(annualContribution)).add(simAccount.getBalance()) );
			
			BigDecimal interest = new BigDecimal(simAccount.getBalance().toString());
			for (int step = 0; step < compoundSteps; step++)
			{
				interest = interest.multiply(stepRate);
			}
			interest.subtract(simAccount.getBalance());
			
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
		System.out.println();
		System.out.println("Summary");
		System.out.printf("Total Contributions: $%-20.2f", totalContributions);
		System.out.printf("Total Interest Earned: $%-20.2f", totalInterest);
		System.out.printf("Ending Balance: $%-20.2f", simAccount.getBalance());
		
		return res;
	}
	
}
