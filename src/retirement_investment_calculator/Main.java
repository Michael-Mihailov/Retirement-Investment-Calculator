package retirement_investment_calculator;

import java.util.*;

/**
 * Michael Mihailov 
 */
public class Main {

	
	public static void main(String[] args) {
		
		// variables
		Scanner user = new Scanner(System.in);
		String inputBuffer = null;
		
		int currentAge = -1;
		int retirementAge = -1;
		double currentBalance = -1.0;
		double annualContribution = -1.0;
		double annualInterestRate = -1.0;
		int compoundingFrequency = -1; // 1 == Annually, 2 == Monthly, 3 == Daily
		double annualContributionIncrease = -1.0;
		
		
		// input prompting
		System.out.println("WELCOME TO THE RETIREMENT INVESTMENT CALCULATOR!!!");
		System.out.println("This program will simulate the growth of a retirement account.");
		
		do // get currentAge
		{
			System.out.print("Current Age: ");
			inputBuffer = user.nextLine();
			
			try
			{
				currentAge = Integer.parseInt(inputBuffer);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Current age must be an integer!");
				continue;
			}
			
			if (currentAge < 18 || currentAge > 100)
			{
				System.out.println("ERROR! Current age must be between 18 and 100 years old!");
				continue;
			}
		}
		while (currentAge < 18 || currentAge > 100);
		
		do // get retirementAge
		{
			System.out.print("Retirement Age: ");
			inputBuffer = user.nextLine();
			
			try
			{
				retirementAge = Integer.parseInt(inputBuffer);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Retirement age must be an integer!");
				continue;
			}
			
			if (retirementAge <= currentAge || retirementAge > 100)
			{
				System.out.println("ERROR! Retirement age must be between " + currentAge + " and 100 years old!");
				continue;
			}
		}
		while (retirementAge <= currentAge || retirementAge > 100);

		do // get currentBalance
		{
			System.out.print("Current Balance: ");
			inputBuffer = user.nextLine();
			
			try
			{
				currentBalance = Double.parseDouble(inputBuffer);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Current balance must be a decimal!");
				continue;
			}
			
			if (currentBalance < 0)
			{
				System.out.println("ERROR! Current balance must be at least $0.00");
				continue;
			}
		}
		while (currentBalance < 0);
		
		do // get annualContribution
		{
			System.out.print("Annual Contribution: ");
			inputBuffer = user.nextLine();
			
			try
			{
				annualContribution = Double.parseDouble(inputBuffer);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Annual contribution must be a decimal!");
				continue;
			}
			
			if (annualContribution < 0)
			{
				System.out.println("ERROR! Annual contribution must be at least $0.00");
				continue;
			}
		}
		while (annualContribution < 0);
		
		do // get annualInterestRate
		{
			System.out.print("Annual Interest Rate: ");
			inputBuffer = user.nextLine();
			
			try
			{
				annualInterestRate = Double.parseDouble(inputBuffer);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Annual interest rate must be a decimal!");
				continue;
			}
			
			if (annualInterestRate < 0 || annualInterestRate > 30)
			{
				System.out.println("ERROR! Annual interest rate must be between %0 and %30");
				continue;
			}
		}
		while (annualInterestRate < 0 || annualInterestRate > 30);
		
		do // get compoundingFrequency
		{
			System.out.println("1: Annualy\n2: Monthly\n3:Daily");
			System.out.print("Compounding Frequency: ");
			inputBuffer = user.nextLine();
			
			try
			{
				compoundingFrequency = Integer.parseInt(inputBuffer);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Compounding frequency must match menu selections!");
				continue;
			}
			
			if (compoundingFrequency < 1 || compoundingFrequency > 3)
			{
				System.out.println("ERROR! Compounding frequency must match menu selections!");
				continue;
			}
		}
		while (compoundingFrequency < 1 || compoundingFrequency > 3);
		
		do // get annualContributionIncrease
		{
			System.out.print("Annual Contribution Increase: ");
			inputBuffer = user.nextLine();
			
			try
			{
				annualContributionIncrease = Double.parseDouble(inputBuffer);
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Annual contribution increase must be a decimal!");
				continue;
			}
			
			if (annualContributionIncrease < 0 || annualContributionIncrease > 20)
			{
				System.out.println("ERROR! Annual contribution increase must be between %0 and %20");
				continue;
			}
		}
		while (annualContributionIncrease < 0 || annualContributionIncrease > 20);
		
		
		// logic
		
	}

}
