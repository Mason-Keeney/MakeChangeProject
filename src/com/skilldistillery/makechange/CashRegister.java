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
			int diff = (int) ((price - payment) * -1);
			int coinVal = (int) Math.round((remainder - diff) * 100);

//			IF NO CHANGE
			if (remainder == 0) {
				System.out.println("No change is due");
				isRunning = keepShop(sc);
				continue;
			}

//			IF MORE IS OWED			
			if (remainder < 0) {
				System.out.print("Insufficient Payment\nPlease pay an additional ");
				dAndC((diff * -1), (coinVal * -1));
				isRunning = keepShop(sc);
				continue;
			}

//			IF CHANGE IS OWED			
			int[] change = assignVal(diff, coinVal);
			System.out.print("Your change is ");
			for (int i = 0; i < change.length; i++) {
				if (change[i] > 0) {
					printChange(change[i], changeLabel[i]);
				}
			}
			System.out.print("and totals ");
			dAndC(diff, coinVal);
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
	public static int[] assignVal(int diff, int coinVal) {
			int[] change = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, };
			int[] mod = { 100, 50, 20, 10, 5, 1, 25, 10, 5, 1 };		
			
	//		BILLS
			for (int i = 0; i < 6; i++) {
				change[i] = diff / mod[i];
				diff = diff % mod[i];
			}
	
	//		COINS
			for (int i = 6; i < change.length; i++) {
				change[i] = coinVal / mod[i];
				coinVal = coinVal % mod[i];
			}
	
			return change;
		}
	
//	dAndC prints the dollar(s) and cent(s) lines (its a bulky if tree,)
	public static void dAndC(int diff, int coinVal) {
		if (diff > 0) {
			System.out.print(diff + " dollar");
			if (diff > 1) {
				System.out.print("s and ");
			} else {
				System.out.print(" and ");
			}
		}
		System.out.println(coinVal + " cents.");
	}
	
//	printChange does as it says, adds plurality as needed.
	public static void printChange(int quantity, String denom) {
	
		System.out.print(quantity + " " + denom);
		if ((denom.equals("penn") || denom.equals("Twent") || denom.equals("Fift")) && quantity > 1) {
			System.out.print("ies ");
		} else if (denom.equals("penn") || denom.equals("Twent") || denom.equals("Fift")) {
			System.out.print("y ");
		} else if (quantity > 1) {
			System.out.print("s ");
		} else {
			System.out.print(" ");
		}
	}
}
