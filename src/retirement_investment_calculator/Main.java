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
		
		// variables
		Scanner user = new Scanner(System.in);
		
		int currentAge;
		int retirementAge;
		double currentBalance;
		double annualContribution;
		double annualInterestRate;
		int compoundingFrequency; // 1 == Annually, 2 == Monthly, 3 == Daily
		double annualContributionIncrease;
		
		
		// main loop
		do
		{
			// input prompting
			System.out.println();
			System.out.println("WELCOME TO THE RETIREMENT INVESTMENT CALCULATOR!!!");
			System.out.println("This program will simulate the growth of a retirement account.");
			System.out.println();;
			
			currentAge = readIntInRange(user, CURRENT_AGE_PROMPT, CURRENT_AGE_MIN, CURRENT_AGE_MAX);
			retirementAge = readIntInRange(user, RETIREMENT_AGE_PROMPT, currentAge + 1, RETIREMENT_AGE_MAX);
			currentBalance = readDoubleInRange(user, CURRENT_BALANCE_PROMPT, CURRENT_BALANCE_MIN, CURRENT_BALANCE_MAX);
			annualContribution = readDoubleInRange(user, ANNUAL_CONTRIBUTION_PROMPT, ANNUAL_CONTRIBUTION_MIN, ANNUAL_CONTRIBUTION_MAX);
			annualInterestRate = readDoubleInRange(user, ANNUAL_INTEREST_RATE_PROMPT, ANNUAL_INTEREST_RATE_MIN, ANNUAL_INTEREST_RATE_MAX);
			compoundingFrequency = readCompoundingChoice(user);
			annualContributionIncrease = readDoubleInRange(user, ANNUAL_CONTRIBUTION_INCREASE_PROMPT, ANNUAL_CONTRIBUTION_INCREASE_MIN, ANNUAL_CONTRIBUTION_INCREASE_MAX);
			
			
			// logic
			System.out.println();
			runSimulation(currentAge, retirementAge, currentBalance, annualContribution, annualInterestRate, compoundingFrequency, annualContributionIncrease);
			System.out.println();
		}
		while (askRunAgain(user) == true);
	}
	
	
	private static boolean askRunAgain(Scanner sc)
	{
		System.out.println("Please enter \"YES\" if you would like to run the program again: ")
		return (sc.nextLine().equalsIgnoreCase("YES"));
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
	
	private static int readIntInRange(Scanner sc, String prompt, int min, int max)
	{
		int res = -1;
		boolean done = false;
		
		while (!done) // get input until correct
		{
			System.out.print(prompt);
			
			try
			{
				res = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Input must be an integer!");
				continue;
			}
			
			if (res < min || res > max)
			{
				System.out.println("ERROR! Input must be between " + min + " and " + max);
				continue;
			}
			
			done = true;
		}
		
		return res;
	}
	
	private static double readDoubleInRange(Scanner sc, String prompt, double min, double max)
	{
		double res = -1.0;
		boolean done = false;
		
		while (!done) // get input until correct
		{
			System.out.print(prompt);
			
			try
			{
				res = Double.parseDouble(sc.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Input must be an decimal!");
				continue;
			}
			
			if (res < min || res > max)
			{
				System.out.println("ERROR! Input must be between " + min + " and " + max);
				continue;
			}
			
			done = true;
		}
		
		return res;
	}
	
	private static int readCompoundingChoice(Scanner sc)
	{
		int res = -1;
		
		do // get compoundingFrequency
		{
			System.out.println("1: Annualy\n2: Monthly\n3:Daily");
			System.out.print("Compounding Frequency: ");
			
			try
			{
				res = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Compounding frequency must match menu selections!");
				continue;
			}
			
			if (res < 1 || res > 3)
			{
				System.out.println("ERROR! Compounding frequency must match menu selections!");
				continue;
			}
		}
		while (res < 1 || res > 3);
		
		return res;
	}
	
}
