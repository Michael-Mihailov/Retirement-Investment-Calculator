package retirement_investment_calculator;

import java.math.BigDecimal;
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
			Account initialAccount = new Account(currentAge, new BigDecimal(currentBalance), new BigDecimal(annualInterestRate), compoundFrequency);
			InvestmentSimulator simulator = new InvestmentSimulator(initialAccount);
			ArrayList<String> receipt = simulator.runSimulation(retirementAge, annualContribution, annualContributionIncrease);
			for (String s : receipt) System.out.println(s);
		}
		while (IO.askRunAgain() == true);
		
		// end program
		IO.printExitMessage();
	}	
}
