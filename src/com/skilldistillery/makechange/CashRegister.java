package com.skilldistillery.makechange;

import java.util.Scanner;

public class CashRegister {
	
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		boolean isRunning = true;

		while (isRunning) {

			double payment, price;
			System.out.print("Please enter the price of the item: ");
			price = sc.nextDouble();

			System.out.println("How much will you be paying?");
			payment = sc.nextDouble();

			if ((price - payment) == 0) {
				System.out.println("No change is due");
			} else {

				int[] bills = billsCalc(price, payment);
				int bTotal = (bills[0] * 20) + (bills[1] * 10) + (bills[2] * 5) + (bills[3] * 1);
				int[] coins = coinsCalc(price, payment, bTotal);
				int cTotal = (coins[0] * 25) + (coins[1] * 10) + (coins[2] * 5) + (coins[3] * 1);

				if (bTotal > 0 || cTotal > 0) {

					print(bills, coins);
				}

				sc.nextLine();
				String again = sc.nextLine();
				String againLow = again.toLowerCase();

				if (againLow.equals("y")) {
					isRunning = false;
				}

			}

		}

		sc.close();
	}

	public static int[] billsCalc(double price, double payment) {
		int[] bills = { 0, 0, 0, 0, };
		// [0] = twenties [1] = tens [2] = fives [3] = ones

		double diff = price - payment;
		int diffInt = (int) diff;
		double deciVal;
		int deciInt;

		if (diff > 0) {
			deciVal = (diff - diffInt) * 100;
			deciInt = (int) deciVal;
			System.out.println("Insufficient Payment\nPlease provide an additional $" + diffInt + "." + deciInt);
			
		} else if (diff < 0) {
			int after20, find10 = 0, remain10 = 0, after10 = 0, find5 = 0, remain5 = 0, after5 = 0, find1;
			double diffAbs = diff * -1; // change to positive
			diffInt = (int) diffAbs;

//		assessing and adding $20s to bills[0]
			int remain20 = diffInt % 20;
			int find20 = diffInt / 20;
			if (find20 > 0) {
				bills[0] += find20;
			}
			after20 = diffInt - (find20 * 20);

//		assessing $10s and adding to bills[1] 
			if (remain20 >= 10) {
				find10 = after20 / 10;
				if (find10 > 0) {
					bills[1] += find10;
					remain10 = after20 % 10;
				}
			}
			after10 = after20 - (find10 * 10);

//		assessing $5s and adding to bills[2]
			if (remain10 >= 5 || (remain20 < 10 && remain20 >= 5)) {
				find5 = after10 / 5;

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
		int[] coins = { 0, 0, 0, 0 };
//		coins[0] = quarters coins[1] = dimes coins[2] = nickels coins[3] = pennies

		int after25 = 0, find25 = 0, after10 = 0, find10 = 0, after5 = 0, find5 = 0, find1 = 0;

		double remainder = price - payment - (bills * -1);

		if (remainder < 0) {
			double coinWhole = remainder * -100;
			int coinWholeRound = (int) Math.round(coinWhole);
			find25 = coinWholeRound / 25;
//			System.out.println(remainder);

//		assessing and adding quarters to coins[0]
			if (find25 > 0) {
				coins[0] += find25;
			}
			after25 = coinWholeRound - (find25 * 25);

//		assessing and adding nickels to coins[1]
			if (after25 >= 10) {
				find10 = after25 / 10;
				if (find10 > 0) {
					coins[1] += find10;

				}
			}
			after10 = after25 - (find10 * 10);

//		assessing and adding nickels to coins[2]
			if (after10 >= 5 || (after25 < 10 && after25 >= 5)) {
				find5 = after10 / 5;

				if (find5 > 0) {
					coins[2] += find5;

				}
			}
			after5 = after10 - (find5 * 5);

//		assessing and adding pennies to coins[3]
			if (after5 >= 1 || (after25 < 10 && after25 < 5 && after25 >= 1) || (after10 < 5 && after10 >= 1)) {
				find1 = after5 / 1;
				if (find1 > 0) {
					coins[3] += find1;
				}
			}
		}

		return coins;
	}

	public static void print(int[] bills, int[] coins) {
		System.out.print("Your change will be ");
		if (bills[0] != 0) {
			System.out.print(bills[0] + " twent");
			if (bills[0] > 1) {
				System.out.print("ies ");
			} else {
				System.out.print("y ");
			}
		}
		if (bills[1] != 0) {
			System.out.print(bills[1] + " ten");
			if (bills[1] > 1) {
				System.out.print("s ");
			} else {
				System.out.print(" ");
			}
		}
		if (bills[2] != 0) {
			System.out.print(bills[2] + " five");
			if (bills[2] > 1) {
				System.out.print("s ");
			} else {
				System.out.print(" ");
			}
		}
		if (bills[3] != 0) {
			System.out.print(bills[3] + " one");
			if (bills[3] > 1) {
				System.out.print("s ");
			} else {
				System.out.print(" ");
			}
		}
		if (coins[0] != 0) {
			System.out.print(coins[0] + " quarter");
			if (coins[0] > 1) {
				System.out.print("s ");
			} else {
				System.out.print(" ");
			}
		}
		if (coins[1] != 0) {
			System.out.print(coins[1] + " dime");
			if (coins[1] > 1) {
				System.out.print("s ");
			} else {
				System.out.print(" ");
			}
		}
		if (coins[2] != 0) {
			System.out.print(coins[2] + " nickel");
			if (coins[2] > 1) {
				System.out.print("s ");
			} else {
				System.out.print(" ");
			}
		}
		if (coins[3] != 0) {
			System.out.print("and " + coins[3] + " penn");
			if (coins[3] > 1) {
				System.out.print("ies. ");
			} else {
				System.out.print("y. ");
			}
		}

		System.out.println("\nWill that be all? (Y/N)");

	}
}
