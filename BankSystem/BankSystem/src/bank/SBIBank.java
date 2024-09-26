package bank;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import custom_exception.IllegalAmountException;
import custom_exception.InsufficientBalanceException;
import custom_exception.MaximumWithdrawLimitExceedException;
import entity.Account;
import entity.Customer;
import entity.TransactionRecord;
import util.Transaction;

public class SBIBank {

	public static Account getValidAccount(String accNum, Customer[] customers) {
		for (Customer customer : customers) {
			if (customer.account.getAccNum().equals(accNum)) {
				return customer.account;
			}
		}
		return null;
	}

	static final String BANK_NAME = "SBI";

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		System.out.println("Welcome to " + BANK_NAME);
		System.out.println("Enter bank size: ");
		int size = sc.nextInt();
		Customer[] customers = new Customer[size];
		int count = 0;
		boolean exit = true;
		while (exit) {
			System.out.println(
					"Enter choice: \n1.Add Customer.\n2.Deposit.\n3.Withdraw.\n4.Transfer.\n5.Get all customers.\n6.Get all transactions.\n7.Bank account statement.\n10.Exit");
			int choice = sc.nextInt();
			switch (choice) {
			case 1: {
				if (count < size) {
					System.out.println("Register new customer");
					System.out.println("Enter Name, address");
					/*
					 * Account acc = new Account(); Account newAcc =acc.createAccount();
					 * customers[count] = new Customer(sc.next(), sc.next(),
					 * newAcc.createAccount());
					 */
					customers[count] = new Customer(sc.next(), sc.next(), new Account().createAccount());
					System.out.println(customers[count]);
					++count;
				} else {
					System.out.println("Sorry Bank is full.");
				}
				break;
			}
			case 2: {
				int flag = 0;
				System.out.println("Enter account number to deposit");
				String accNum = sc.next();
				for (Customer customer : customers) {
					if (accNum.equals(customer.account.getAccNum())) {
						flag = 1;
						System.out.println("Enter amount: ");
						double amt = sc.nextDouble();
						try {
							customer.account.deposit(amt);
							System.out.println(amt + " Credited, Available balance: " + customer.account.getBal());
						} catch (IllegalAmountException e) {
							e.printStackTrace();
						}
						break;
					}
				}
				if (flag == 0) {
					System.out.println("Account not exist, Enter correct account number");
				}
				break;
			}
			case 3: {
				System.out.println("Enter account number for withdraw: ");
				String accNum = sc.next();
				Account validAcc = getValidAccount(accNum, customers);
				if (validAcc != null) {
					System.out.println("Enter amount: ");
					double amt = sc.nextDouble();
					try {
						validAcc.withdraw(amt);
						System.out.println(amt + " Debited, Available balance: " + validAcc.getBal());
					} catch (IllegalAmountException | InsufficientBalanceException
							| MaximumWithdrawLimitExceedException e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Account not exist, Enter correct account number");
				}
				break;
			}
			case 4: {
				System.out.println("Enter Sender account number: ");
				Account sender = getValidAccount(sc.next(), customers);
				System.out.println("Enter Receiver account number: ");
				Account receiver = getValidAccount(sc.next(), customers);

				if (sender != null && receiver != null) {
					System.out.println("Enter ammount for transfer: ");
					try {
						Transaction.transfer(sender, receiver, sc.nextDouble());
					} catch (Exception e) {
						e.printStackTrace();
					}
				} else {
					System.out.println("Invalid accounts.");
				}
				break;
			}
			case 5: {
				for (Customer customer : customers) {
					System.out.println(customer);

				}
				break;
			}
			case 6: {
				ArrayList<TransactionRecord> transactionRecord = Transaction.getTransactions();
				try (FileOutputStream fileOutputStream = new FileOutputStream(
						"D:\\Java_Pract\\BankSystem\\src\\util\\Transactions.txt",true); // true is for for append the objects into file.
						ObjectOutputStream outputStream = new ObjectOutputStream(fileOutputStream);) {
					for (TransactionRecord record : transactionRecord) {
						System.out.println(record);
						outputStream.writeObject(record + "\n");
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
			case 7:{
				System.out.println("Enter account number: ");
				String accNum = sc.next();
				Account validAcc = getValidAccount(accNum, customers);
				ArrayList<TransactionRecord> records = Transaction.getTransactions();
				if(validAcc != null) {
					for (TransactionRecord transactionRecord : records) {
						if(transactionRecord.getAccounts().contains(validAcc)) {
							System.out.println(transactionRecord);
						}
					}
				}else {
					System.out.println("Invalid account, enter correct account number.");
				}
				break;
			}
			case 10: {
				exit = false;
				break;
			}

			default:
				System.out.println("Invalid choice!! try again");
				break;
			}
		}
		System.out.println("Thank you !! visit again");
		sc.close();
	}

}

// Jack Kolhapur
// Jill Pune
