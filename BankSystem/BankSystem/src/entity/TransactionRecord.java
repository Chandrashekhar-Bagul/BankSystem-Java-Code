package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class TransactionRecord  implements Serializable{
	private long transId;
	private String transType;
	private double amount;
	private LocalDateTime transDateTime;
	private ArrayList<Account> accounts;
	private static long id = 5000;
	
	public TransactionRecord() {
		// TODO Auto-generated constructor stub
	}

	public TransactionRecord( String transType, double amount, ArrayList<Account> accounts) {
		super();
		this.transId = ++id;
		this.transType = transType;
		this.amount = amount;
		this.transDateTime = LocalDateTime.now();
		this.accounts = accounts;
	}

	public long getTransId() {
		return transId;
	}

	public void setTransId(long transId) {
		this.transId = transId;
	}

	public String getTransType() {
		return transType;
	}

	public void setTransType(String transType) {
		this.transType = transType;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public LocalDateTime getTransDateTime() {
		return transDateTime;
	}

	public void setTransDateTime(LocalDateTime transDateTime) {
		this.transDateTime = transDateTime;
	}

	public ArrayList<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(ArrayList<Account> accounts) {
		this.accounts = accounts;
	}

	@Override
	public String toString() {
		return "TransactionRecord [transId=" + transId + ", transType=" + transType + ", amount=" + amount
				+ ", transDateTime=" + transDateTime + ", accounts=" + accounts + "]";
	}
	
	
}
