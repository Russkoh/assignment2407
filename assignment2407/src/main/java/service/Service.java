package service;

import repository.*;
import transaction.Transaction;
import user.User;
import exceptions.*;
import utility.*;

public class Service implements IService {

	private IRepository repo = new Repository();
	String nu = "Null User";
	String anp = "Account not present";
	

	
	public String createAccount(User u) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		if(u == null){
			throw new InvalidAccountException(nu);
		}
		else if(u.getBalance()<100.0d){
			throw new InsufficientFundsException(nu);
		}
		else if(checkAccount(u.getUserID())){
			
			throw new InvalidAccountException("Account already exists");
			
		}else{
			return repo.saveAccount(u.getUserID(), u.getBalance());
			
		}
		
		
	}
	
	public User withdraw (User u, double value, int date) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		if(u == null){
			throw new InvalidAccountException(nu);
		}
		else if(value>1000.0d){
			throw new WithdrawalExcessiveException("Cannot withdraw so much money");
		}
		else if(!checkAccount(u.getUserID())){
			
			throw new InvalidAccountException(anp);
			
		}else if(u.getBalance()<value){
			
			throw new InsufficientFundsException("Not enough money");
			
		}else{
			
			repo.findAll()[repo.findAccount(u.getUserID())].setBalance(u.getBalance() - value);
			repo.findAll()[repo.findAccount(u.getUserID())].setTransactions(new Transaction("Withdrawal", date, value, u.getBalance(), Utility.uniqueTransactionGenerator()));
			return repo.findAll()[repo.findAccount(u.getUserID())];
		}
	}
	
	public User deposit(User u, double value, int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		if(u == null){
			throw new InvalidAccountException(nu);
		}
		else if(!checkAccount(u.getUserID())){
			
			throw new InvalidAccountException(anp);
			
		}else{
			repo.findAll()[repo.findAccount(u.getUserID())].setBalance(value+u.getBalance());
			repo.findAll()[repo.findAccount(u.getUserID())].setTransactions(new Transaction("Deposit", date, value, u.getBalance(), Utility.uniqueTransactionGenerator()));
			return repo.findAll()[repo.findAccount(u.getUserID())] ;
		}
	}
	
	public User fundTransfer(User u, User ur, double value, int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		if(u == null){
			throw new InvalidAccountException("Null transfering user");
		}
		else if(ur == null){
			throw new InvalidAccountException("Null receiving user");
		}
		else if(!checkAccount(u.getUserID())){
			
			throw new InvalidAccountException("Transferring account not present");
			
		}else if(!checkAccount(ur.getUserID())){
			
			throw new InvalidAccountException("Receiving account not present");
			
		}else if(u.getBalance()<value){
			
			throw new InsufficientFundsException("Not enough money");
			
		}else{
			repo.findAll()[repo.findAccount(ur.getUserID())].setBalance(value+ur.getBalance());
			repo.findAll()[repo.findAccount(u.getUserID())].setBalance(u.getBalance() - value);
			repo.findAll()[repo.findAccount(u.getUserID())].setTransactions(new Transaction("Fund transfer", date, value, u.getBalance(), Utility.uniqueTransactionGenerator()));
			repo.findAll()[repo.findAccount(ur.getUserID())].setTransactions(new Transaction("Fund transferred", date, value, ur.getBalance(), Utility.uniqueTransactionGenerator()));
			return repo.findAll()[repo.findAccount(u.getUserID())];
		}
	}
	
	public User printTransactions(User u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		if(u == null){
			throw new InvalidAccountException("Null transfering user");
		}
		else if(!checkAccount(u.getUserID())){
			
			throw new InvalidAccountException(anp);
			
		}else{
			return repo.findAll()[repo.findAccount(u.getUserID())];
			
		}
	}
	
	public User printTransactions(User u, int dateFrom, int dateTo)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		if(u == null){
			throw new InvalidAccountException("Null transfering user");
		}
		else if(!checkAccount(u.getUserID())){
			
			throw new InvalidAccountException(anp);
			
		}else{
			
			Transaction[] newTransactionArray = new Transaction[0];
			int j = 0;
			for(int i = 0; i<repo.findAll()[repo.findAccount(u.getUserID())].getTransactions().length; i++){
				
				if(dateFrom<=repo.findAll()[repo.findAccount(u.getUserID())].getTransactions()[i].getTransactionDate() && dateTo>=repo.findAll()[repo.findAccount(u.getUserID())].getTransactions()[i].getTransactionDate()){
					
						Transaction[] newTransactionsArrayCopy = new Transaction[newTransactionArray.length + 1];
							
						for(int k = 0 ; k< newTransactionArray.length; k++){
								newTransactionsArrayCopy[k] = newTransactionArray[k];
							}
							newTransactionArray = newTransactionsArrayCopy;
							newTransactionArray[j] = repo.findAll()[repo.findAccount(u.getUserID())].getTransactions()[i];
							j++;
					
					
				}else{
					throw new IncorrectDateRange("Date is not applicable");
				}
			}
			u.setTransactionsArray(newTransactionArray);	
			return u;
			
		}
	}
	
	public User showBalance(User u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		if(u == null){
			throw new InvalidAccountException("Null transfering user");
		}else if(!checkAccount(u.getUserID())){
			
			throw new InvalidAccountException(anp);
			
		}else{
			for(User person:repo.findAll()){
				if(person.getUserID() == u.getUserID()){
					return person;
				}
			}
		return null;
		}
	}
	
	public boolean checkAccount(int userID){
		for(int i = 0; i< repo.findAll().length; i++){
			if(repo.findAll()[i].getUserID() == userID){
				return true;
			}
		}
		return false;
	}
	
}
