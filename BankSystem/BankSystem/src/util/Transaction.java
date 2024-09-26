package util;

import java.util.ArrayList;

import custom_exception.IllegalAmountException;
import custom_exception.IllegalTransferException;
import custom_exception.InsufficientBalanceException;
import custom_exception.MaximumWithdrawLimitExceedException;
import entity.Account;
import entity.TransactionRecord;

public class Transaction {
	
	public static void transfer(Account sender, Account receiver, double amt) throws IllegalAmountException, InsufficientBalanceException, MaximumWithdrawLimitExceedException {
		if(sender == receiver) {
			throw new IllegalTransferException("Both accounts are same");
		}else {
			sender.withdraw(amt);
			receiver.deposit(amt);
			System.out.println(amt+" transfered to "+receiver.getAccNum()+"\nAvailable balnace: "+sender.getBal());
			ArrayList<Account> transerAccounts = new ArrayList<>();
			transerAccounts.add(sender);
			transerAccounts.add(receiver);
			recordTransferTransation(amt, transerAccounts);
		}
	}
	
	public void enforceWithdrawalLimit(Account account, double amt) throws IllegalAmountException {
		if(amt<=0) {
			throw new IllegalAmountException("Invalid amount");
		}else if(account.getMaxWithLimit()>amt) {
			System.out.println("amount should be greater than current withdraw limit: "+account.getMaxWithLimit());
		}else {
			account.setMaxWithLimit(amt);
		}
	}
	
	static ArrayList<TransactionRecord> transactionRecords = new ArrayList<>();
	
	public static void recordDepositTransation(double amount, ArrayList<Account> accounts) {
		transactionRecords.add(new TransactionRecord("Deposit", amount, accounts));
	}
	public static void recordWithdrawTransation(double amount, ArrayList<Account> accounts) {
		transactionRecords.add(new TransactionRecord("Withdraw", amount, accounts));
	}
	public static void recordTransferTransation(double amount, ArrayList<Account> accounts) {
		transactionRecords.add(new TransactionRecord("Transfer", amount, accounts));
	}
	
	public static ArrayList<TransactionRecord> getTransactions(){
		return transactionRecords;
	}
}
