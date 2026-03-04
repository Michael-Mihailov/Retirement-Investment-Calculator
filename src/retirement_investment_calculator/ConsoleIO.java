package retirement_investment_calculator;
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
	
	public int readCompoundingChoice()
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
