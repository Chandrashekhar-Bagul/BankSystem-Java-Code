package entity;

public class Customer {
	private String name;
	private String addr;
	public Account account;
	
	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Customer(String name, String addr, Account account) {
		super();
		this.name = name;
		this.addr = addr;
		this.account = account;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	@Override
	public String toString() {
		account.getAccNum();
		return "Customer [name=" + name + ", addr=" + addr + ", account=" + account + "]";
	}
	
}
