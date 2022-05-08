package com.skilldistillery.makechange;

import java.util.Scanner;

public class CashRegister {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;
		double price, payment;
		String[] changeLabel = { "Hundred", "Fift", "Twent", "Ten", "Five", "Dollar", "quarter", "dime", "nickel",
				"penn" };

		while (isRunning) {
			
//			USER INPUT
			System.out.print("Please enter the price of the item: ");
			price = sc.nextDouble();
			System.out.print("Payment: ");
			payment = sc.nextDouble();
			sc.nextLine();

//			MATH
			double remainder = (price - payment) * -1;
			int difference = (int) ((price - payment) * -1);
			int coinValue = (int) Math.round((remainder - difference) * 100);

//			IF NO CHANGE
			if (remainder == 0) {
				System.out.println("No change is due");
				isRunning = keepShop(sc);
				continue;
			}

//			IF MORE IS OWED			
			if (remainder < 0) {
				System.out.print("Insufficient Payment\nPlease pay an additional ");
				printChangeTotal((difference * -1), (coinValue * -1));
				isRunning = keepShop(sc);
				continue;
			}

//			IF CHANGE IS OWED			
			int[] change = assignVal(difference, coinValue);
			System.out.print("Your change is ");
			for (int i = 0; i < change.length; i++) {
				if (change[i] > 0) {
					printChange(change[i], changeLabel[i]);
				}
			}
			System.out.print("and totals ");
			printChangeTotal(difference, coinValue);
			isRunning = keepShop(sc);
		}
		
		if(!isRunning) {
			sc.close();
		}

	}
// keepShop uses input to decide if the program keeps running
	public static boolean keepShop(Scanner sc) {
		boolean isRunning = true;
		System.out.println("Would you like to continue shopping with us? (Y/N)");
		String in = sc.nextLine();
		String answer = in.toLowerCase();

		if (answer.equals("y")) {
			System.out.println("Thank you!\n");

		} else {
			System.out.println("Have a wonderful day!");
			isRunning = false;

		}
		return isRunning;
	}
	
//	assignVal uses two integers to assign values to an int[] using the appropriate math
	public static int[] assignVal(int difference, int coinValue) {
			int[] change = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
			int[] mod = { 100, 50, 20, 10, 5, 1, 25, 10, 5, 1 };		
			
	//		BILLS
			for (int i = 0; i < 6; i++) {
				change[i] = difference / mod[i];
				difference = difference % mod[i];
			}
	
	//		COINS
			for (int i = 6; i < change.length; i++) {
				change[i] = coinValue / mod[i];
				coinValue = coinValue % mod[i];
			}
	
			return change;
		}
	
//	dAndC prints the dollar(s) and cent(s) lines (its a bulky if tree,)
	public static void printChangeTotal(int difference, int coinValue) {
		if (difference > 0) {
			System.out.print(difference + " dollar");
			if (difference > 1) {
				System.out.print("s and ");
			} else {
				System.out.print(" and ");
			}
		}
		System.out.println(coinValue + " cents.");
	}
	
//	printChange does as it says, adds plurality as needed.
	public static void printChange(int quantity, String denomination) {
		boolean needsAltPlurality = denomination.equals("penn") || denomination.equals("Twent") || denomination.equals("Fift");
		
		System.out.print(quantity + " " + denomination);
		if (needsAltPlurality && quantity > 1) {
			System.out.print("ies ");
		} else if (needsAltPlurality) {
			System.out.print("y ");
		} else if (quantity > 1) {
			System.out.print("s ");
		} else {
			System.out.print(" ");
		}
	}
}
