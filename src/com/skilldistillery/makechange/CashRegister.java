package com.skilldistillery.makechange;

import java.util.Scanner;

public class CashRegister {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		double payment, price;
		System.out.print("Please enter the price of the item: ");
		price = sc.nextDouble();

		System.out.println("How much will you be paying?");
		payment = sc.nextDouble();

		int[] bills = billsCalc(price, payment);
		int bTotal =(bills[0] * 20) + (bills[1] * 10) + (bills[2] * 5) + (bills[3] * 1); 
		int[] coins = coinsCalc(price, payment, bTotal);

		sc.close();
	}

	public static int[] billsCalc(double price, double payment) {
		int[] bills = { 0, 0, 0, 0, };
		// [0] = twenties [1] = tens [2] = fives [3] = ones, [4] = quarters [5] = dimes
		// [6] = nickels [7] = pennies

		double diff = price - payment;
		int diffInt = (int) diff;
		double deciVal;
		int deciInt;

		if (diff > 0) {
			deciVal = (diff - diffInt) * 100;
			deciInt = (int) deciVal;
			System.out.println("Insufficient Payment, please pay $" + diffInt + "." + deciInt + " more");
		} else if (diff < 0) {
			int after20, find10 = 0, remain10 = 0, after10 = 0, find5 = 0, remain5 = 0, after5 = 0, find1;
			double diffAbs = diff * -1; // change to positive
			diffInt = (int) diffAbs;

//		assessing $20s
			int remain20 = diffInt % 20;
			int find20 = diffInt / 20;

//		adding $20s to bills[0]			
			if (find20 > 0) {
				bills[0] += find20;
			}
			after20 = diffInt - (find20 * 20);

//		assessing $10s
			if (remain20 >= 10) {
				find10 = after20 / 10;

//		adding $10s to bills[1]
				if (find10 > 0) {
					bills[1] += find10;

					remain10 = after20 % 10;
				}
			}
			after10 = after20 - (find10 * 10);

//		assessing $5s
			if (remain10 >= 5 || (remain20 < 10 && remain20 >= 5)) {
				find5 = after10 / 5;

//		adding $5s to bills[2]
				if (find5 > 0) {
					bills[2] += find5;
					remain5 = after10 % 5;
				}
			}
			after5 = after10 - (find5 * 5);
//		assessing and adding $1s to bills[3]
			if (remain5 >= 1 || (remain20 < 10 && remain20 < 5 && remain20 >= 1) || (remain10 < 5 && remain10 >= 1)) {
				find1 = after5 / 1;
				if (find1 > 0) {
					bills[3] += find1;
				}
			}

		}

		return bills;
	}
	public static int[] coinsCalc(double price, double payment, int bills) {
		int[] coins = {0, 0, 0, 0};
		int remain25 = 0, after25 = 0, find25= 0, remain10 = 0, after10 = 0, find10 = 0, remain5 = 0, after5 = 0, find5 = 0, find1 = 0;
		
		double remainder = price - payment - (bills * -1);
			if(remainder < 0) {
				double coinWhole = remainder * -100;
				remain25 = (int)coinWhole % 25;
				
				if(remain25 > 0) {
					
				}
				
			}
				
		
		
		
		return coins;
	}
}
