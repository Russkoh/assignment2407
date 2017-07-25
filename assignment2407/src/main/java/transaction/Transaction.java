package transaction;

public class Transaction {
	
	private String description;
	private int date;
	private double amount;
	private double balance;
	private int transactionID;
	
	public Transaction(String description, int date, double amount, double balance, int transactionID){
		this.description = description;
		this.date = date;
		this.amount = amount;
		this.balance = balance;
		this.transactionID = transactionID;
	}
	
	public String getTransactionDescription(){
		return this.description;
	}
	
	public int getTransactionDate(){
		return this.date;
	}
	
	public double getAmount(){
		return this.amount;
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public double getTransactionID(){
		return this.transactionID;
	}

}
