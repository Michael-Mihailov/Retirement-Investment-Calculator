package retirement_investment_calculator;

import java.util.*;

/**
 * Michael Mihailov 
 */
public class Main {

	
	public static void main(String[] args) {
		
		// constants
		final String CURRENT_AGE_PROMPT = "Current Age: ";
		final String RETIREMENT_AGE_PROMPT = "Retirement Age: ";
		final String CURRENT_BALANCE_PROMPT = "Current Balance: ";
		final String ANNUAL_CONTRIBUTION_PROMPT = "Annual Contribution: ";
		final String ANNUAL_INTEREST_RATE_PROMPT = "Annual Interest Rate: ";
		final String ANNUAL_CONTRIBUTION_INCREASE_PROMPT = "Annual Contribution Increase: ";
		
		final int CURRENT_AGE_MIN = 18;
		final double CURRENT_BALANCE_MIN = 0;
		final double ANNUAL_CONTRIBUTION_MIN = 0;
		final double ANNUAL_INTEREST_RATE_MIN = 0;
		final double ANNUAL_CONTRIBUTION_INCREASE_MIN = 0;
		
		final int CURRENT_AGE_MAX = 100;
		final int RETIREMENT_AGE_MAX = 100;
		final double CURRENT_BALANCE_MAX = Double.MAX_VALUE;
		final double ANNUAL_CONTRIBUTION_MAX = Double.MAX_VALUE;
		final double ANNUAL_INTEREST_RATE_MAX = 30;
		final double ANNUAL_CONTRIBUTION_INCREASE_MAX = 20;
		
		final EnumSet<CompoundFrequency> compoundFrequencyChoices = EnumSet.of(CompoundFrequency.ANNUALY, CompoundFrequency.MONTHLY, CompoundFrequency.DAILY);
		
		// variables
		ConsoleIO IO = new ConsoleIO();
		
		int currentAge;
		int retirementAge;
		double currentBalance;
		double annualContribution;
		double annualInterestRate;
		CompoundFrequency compoundFrequency;
		double annualContributionIncrease;
		
		
		// main loop
		do
		{
			IO.printWelcomeMessage();
			
			currentAge = IO.readIntInRange(CURRENT_AGE_PROMPT, CURRENT_AGE_MIN, CURRENT_AGE_MAX);
			retirementAge = IO.readIntInRange(RETIREMENT_AGE_PROMPT, currentAge + 1, RETIREMENT_AGE_MAX);
			currentBalance = IO.readDoubleInRange(CURRENT_BALANCE_PROMPT, CURRENT_BALANCE_MIN, CURRENT_BALANCE_MAX);
			annualContribution = IO.readDoubleInRange(ANNUAL_CONTRIBUTION_PROMPT, ANNUAL_CONTRIBUTION_MIN, ANNUAL_CONTRIBUTION_MAX);
			annualInterestRate = IO.readDoubleInRange(ANNUAL_INTEREST_RATE_PROMPT, ANNUAL_INTEREST_RATE_MIN, ANNUAL_INTEREST_RATE_MAX);
			compoundFrequency = IO.chooseCompoundFrequency(compoundFrequencyChoices);
			annualContributionIncrease = IO.readDoubleInRange(ANNUAL_CONTRIBUTION_INCREASE_PROMPT, ANNUAL_CONTRIBUTION_INCREASE_MIN, ANNUAL_CONTRIBUTION_INCREASE_MAX);
			
			
			// logic
			System.out.println();
			runSimulation(currentAge, retirementAge, currentBalance, annualContribution, annualInterestRate, compoundingFrequency, annualContributionIncrease);
			System.out.println();
		}
		while (askRunAgain(user) == true);
		
		// end program
		IO.printExitMessage();
	}
	
	private static void runSimulation(int currentAge, int retirementAge, double currentBalance, double annualContribution, double annualInterestRate, int compoundingFrequency, double annualContributionIncrease)
	{
		// setup
		int compoundSteps = 0;
		switch (compoundingFrequency)
		{
			case 1:
				compoundSteps = 1;
				break;
			case 2:
				compoundSteps = 12;
				break;
			case 3:
				compoundSteps = 365;
				break;
			default:
				System.out.println("SIMULATION ERROR!");
				return;
		}
		double stepRate = (annualInterestRate / compoundSteps) / 100;
		double totalContributions = 0;
		double totalInterest = 0;
		
		// print initial state
		System.out.println("Year-by-Year Pojection");
		System.out.printf("%-5s | %-20s | %-20s | %-20s | %-20s%n", 
				"Age", "Start Balance", "Contributions", "Interest Earned", "End");
		
		// run simulation
		for (int year = currentAge + 1; year <= retirementAge; year++)
		{
			double start = currentBalance;
			currentBalance += annualContribution;
			
			double interest = currentBalance;
			for (int step = 0; step < compoundSteps; step++)
			{
				interest *= (stepRate + 1);
			}
			interest -= currentBalance;
			
			// Print Row TODO
			System.out.printf( "%-5d | $%-20.2f | $%-20.2f | $%-20.2f | $%-20.2f%n",
					year, start, annualContribution, interest, currentBalance + interest);
			
			// increment
			totalContributions += annualContribution;
			totalInterest += interest;
			currentBalance += interest;
			annualContribution *= (1 + (annualContributionIncrease / 100));
		}
		
		// print summary
		System.out.println();
		System.out.println("Summary");
		System.out.printf("Total Contributions: $%-20.2f", totalContributions);
		System.out.printf("Total Interest Earned: $%-20.2f", totalInterest);
		System.out.printf("Ending Balance: $%-20.2f", currentBalance);
	}
	
	
	
}
