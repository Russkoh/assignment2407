package user;

import java.util.ArrayList;
import java.util.List;

import transaction.Transaction;
public class User {

	private double balance;
	private List <Transaction> transactions = new ArrayList<Transaction>();
	private int userID;
	private Customer customer;
	
	public User(int userID){
		super();
		this.userID = userID;
		
	}
	
	public double getBalance(){
		return this.balance;
	}
	
	public int getUserID(){
		return this.userID;
	}
	
	public List<Transaction> getTransactions(){
		return this.transactions;
	}
	
	public void setBalance(double value){
		this.balance = value;
	}
	
	public void setTransactions(Transaction t){
			transactions.add(t);
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
}
