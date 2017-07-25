package service;

import repository.*;
import transaction.Transaction;
import user.Customer;
import user.User;
import exceptions.*;
import utility.*;

public class Service implements IService {

	private IRepository repo;
	String nu = "Null User";
	String anp = "Account not present";
	
	public Service(IRepository repo){
		super();
		this.repo = repo;
	}

	
	public User createAccount(Customer customer, double value) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		if(customer.getName() == null){
			throw new InvalidAccountException(nu);
		}
		if(value<100.0d){
			throw new InsufficientFundsException(nu);
		}
		
		User u = new User(AccountNumberGenerator.getUniqueID());
		u.setBalance(value);
		u.setCustomer(customer);
		
		if(repo.saveAccount(u)){
			return u;
		}
			return null;
	}
	
	public User withdraw (int userID, double value, int date) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{


		if(value>1000.0d){
			throw new WithdrawalExcessiveException("Cannot withdraw so much money");
		}else if(repo.findAccount(userID)== null){
			
			throw new InvalidAccountException(anp);
			
		}
		
		if(repo.findAccount(userID).getBalance()<value){
			
			throw new InsufficientFundsException("Not enough money");
			
		}
			repo.findAccount(userID).setBalance(repo.findAccount(userID).getBalance()-value);
			repo.findAccount(userID).setTransactions(new Transaction("Withdrawal", date, value, repo.findAccount(userID).getBalance(), TransactionNumberGenerator.getUniqueTransactionID()));
			
			return repo.findAccount(userID);
		
	}
	
	public User deposit(int userID, double value, int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		if(repo.findAccount(userID)== null){
			
			throw new InvalidAccountException(anp);
			
		}
		repo.findAccount(userID).setBalance(repo.findAccount(userID).getBalance()+value);
		repo.findAccount(userID).setTransactions(new Transaction("Deposit", date, value, repo.findAccount(userID).getBalance(), TransactionNumberGenerator.getUniqueTransactionID()));
			return repo.findAccount(userID) ;
		
	}
	
	public User fundTransfer(int userID, int userReceivingID, double value, int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		if(repo.findAccount(userID)== null){
			
			throw new InvalidAccountException("Transferring account not present");
			
		}
		if(repo.findAccount(userReceivingID)== null){
			
			throw new InvalidAccountException("Receiving account not present");
			
		}
		if(repo.findAccount(userID).getBalance()<value){
			
			throw new InsufficientFundsException("Not enough money");
			
		}
		
		repo.findAccount(userID).setBalance(repo.findAccount(userID).getBalance()-value);
		repo.findAccount(userID).setTransactions(new Transaction("Fund transfer", date, value, repo.findAccount(userID).getBalance(), TransactionNumberGenerator.getUniqueTransactionID()));
		repo.findAccount(userReceivingID).setBalance(repo.findAccount(userReceivingID).getBalance()+value);
		repo.findAccount(userReceivingID).setTransactions(new Transaction("Fund transferred", date, value, repo.findAccount(userReceivingID).getBalance(), TransactionNumberGenerator.getUniqueTransactionID()));
			return repo.findAccount(userID);
		
	}
	
	public User printTransactions(int userID)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		
		if(repo.findAccount(userID)== null){
			
			throw new InvalidAccountException(anp);
			
		}
		
		System.out.println("Transactions: \n" + repo.findAccount(userID).getTransactions());
			return repo.findAccount(userID);
			
		
	}
	
	public User printTransactions(int userID, int dateFrom, int dateTo)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		if(repo.findAccount(userID)== null){
			throw new InvalidAccountException(anp);
		}
		
		 for(int i = 0; i< repo.findAccount(userID).getTransactions().size(); i++){
			 if(repo.findAccount(userID).getTransactions().get(i).getTransactionDate()< dateFrom){
				throw new IncorrectDateRange("Date is not applicable");}
		 }
		 for(int i = 0; i< repo.findAccount(userID).getTransactions().size(); i++){
			 if(repo.findAccount(userID).getTransactions().get(i).getTransactionDate()< dateFrom){
				 System.out.println("Transactions: \n" + repo.findAccount(userID).getTransactions().get(i));}
		 }
		return repo.findAccount(userID);
	}
	
	public User showBalance(int userID)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		if(repo.findAccount(userID)== null){
			
			throw new InvalidAccountException(anp);
			
		}
		
		System.out.println(repo.findAccount(userID).getBalance());
		return repo.findAccount(userID);
	}
	
}
