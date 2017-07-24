package user;

import transaction.Transaction;;
public class User {

	private double balance;
	private Transaction[] transactions;
	private int userID;
	
	public User(int UserID, double value){
		this.userID = UserID;
		this.setBalance(value);
		
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public int getUserID(){
		return this.userID;
	}
	
	public Transaction[] getTransactions(){
		return this.transactions;
	}
	
	public void setBalance(double value){
		this.balance = value;
	}
	
}
