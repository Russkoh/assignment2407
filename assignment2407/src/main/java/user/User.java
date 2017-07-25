package user;

import transaction.Transaction;
public class User {

	private double balance;
	private Transaction[] transactions = new Transaction[0];
	private int userID;
	
	public User(int userID, double value){
		this.userID = userID;
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
	public void setTransactions(Transaction t){
		
			
			Transaction[] copyOfTransactions = new Transaction[transactions.length+1];
			
			for(int i = 0; i< transactions.length; i++){
				copyOfTransactions[i] = transactions[i];	
			}
			
			copyOfTransactions[ transactions.length] = t;
			transactions = copyOfTransactions;
		
		
	}
	public void setTransactionsArray(Transaction[] t){
		this.transactions = t;
	}
	
}
