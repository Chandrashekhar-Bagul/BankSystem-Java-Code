package entity;

import java.io.Serializable;
import java.util.ArrayList;

import custom_exception.IllegalAmountException;
import custom_exception.InsufficientBalanceException;
import custom_exception.MaximumWithdrawLimitExceedException;
import util.Transaction;

@SuppressWarnings("serial")
public class Account implements Serializable{
	private String accNum;
	private double bal;
	private double maxWithLimit;
	private static double minBal;
	private static long id;

	static {
		minBal = 5000;
		id = 1000;
	}

	public Account() {
		bal = minBal;
	}

	public Account(String accNum, double bal) {
		super();
		this.accNum = accNum;
		this.bal = bal;
		this.maxWithLimit = 50000;
	}

	public String getAccNum() {
		return accNum;
	}

	public void setAccNum(String accNum) {
		this.accNum = accNum;
	}

	public double getBal() {
		return bal;
	}

	public void setBal(double bal) {
		this.bal = bal;
	}

	public double getMaxWithLimit() {
		return maxWithLimit;
	}

	public void setMaxWithLimit(double maxWithLimit) {
		this.maxWithLimit = maxWithLimit;
	}

	public Account createAccount() {
		accNum = "sbi" + ++id; // 1001 ------------ sbi1001
		Account account = new Account(accNum, bal);
		return account;
	}

	public void deposit(double amt) throws IllegalAmountException {
		if (amt <= 0) {
			throw new IllegalAmountException("Invalid amount");
		} else {
			bal += amt;
			
			ArrayList<Account> depositAccounts = new ArrayList<>();
			depositAccounts.add(this);
			Transaction.recordDepositTransation(amt, depositAccounts);
		}
	}

	public void withdraw(double amt)
			throws IllegalAmountException, InsufficientBalanceException, MaximumWithdrawLimitExceedException {
		if (amt <= 0) {
			throw new IllegalAmountException("Invalid amount");
		} else if (amt > maxWithLimit) {
			throw new MaximumWithdrawLimitExceedException(
					"Maximum withdrwal limit exceeds, current withdraw limit: " + maxWithLimit);
		} else if ((bal - amt) < minBal) {
			throw new InsufficientBalanceException("Minimun 5000 amount required");
		} else {
			bal -= amt;
			ArrayList<Account> withdrawalAccounts = new ArrayList<>();
			withdrawalAccounts.add(this);
			Transaction.recordWithdrawTransation(amt, withdrawalAccounts);
		}
	}

	public void applyInterest(float rate) {
		double intAmt = bal * rate;
		bal += intAmt;
		System.out.println(intAmt + " Credited, Available balance: " + bal);
	}

	@Override
	public String toString() {
		return "Account [accNum=" + accNum + ", bal=" + this.getBal() + ", maxWithLimit=" + maxWithLimit + "]";
	}

}
