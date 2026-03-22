package retirement_investment_calculator;
import java.util.EnumSet;
import java.util.Scanner;


public class ConsoleIO {
	
	Scanner sc;
	
	public ConsoleIO() 
	{
		sc = new Scanner(System.in);
	}
	
	public void printWelcomeMessage()
	{
		System.out.println();
		System.out.println("WELCOME TO THE RETIREMENT INVESTMENT CALCULATOR!!!");
		System.out.println("This program will simulate the growth of a retirement account.");
		System.out.println();
	}
	
	public int readIntInRange(String prompt, int min, int max)
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
	
	public double readDoubleInRange(String prompt, double min, double max)
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
	
	public CompoundFrequency chooseCompoundFrequency(EnumSet<CompoundFrequency> choices)
	{
		CompoundFrequency res = null;
		int selectionNum = 0;
		
		CompoundFrequency[] choiceArr = choices.toArray(new CompoundFrequency[0]);
		
		do // get compoundingFrequency
		{
			for (int i = 0; i < choiceArr.length; i++)
			{
				System.out.println((i+1) + ": " + choiceArr[i].name);
			}
			System.out.print("Compounding Frequency: ");
			
			try
			{
				selectionNum = Integer.parseInt(sc.nextLine());
			}
			catch (NumberFormatException e)
			{
				System.out.println("ERROR! Compounding frequency must match menu selections!");
				continue;
			}
			
			if (selectionNum < 1 || selectionNum > choiceArr.length)
			{
				System.out.println("ERROR! Compounding frequency must match menu selections!");
				continue;
			}
			
			res = choiceArr[selectionNum - 1];
		}
		while (res == null);
		
		return res;
	}
	
	public boolean askRunAgain()
	{
		System.out.println("Please enter \"YES\" if you would like to run the program again: ");
		return (sc.nextLine().equalsIgnoreCase("YES"));
	}
	
	public void printExitMessage()
	{
		System.out.println("Thank You for using our service!");
	}
}
