package service;

import repository.Repository;
import user.User;
import exceptions.*;

public class Service implements IService {

	private Repository repo = new Repository();

	
	public String createAccount(User u) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		if(repo.findAccount(u.getUserID())){
			repo.saveAccount(u.getUserID(), u.getBalance());
			return "Account has been saved";
		}else{
			throw new InvalidAccountException("Account already exists");

		}
		
		
	}
	
	public User withdraw (User u, double value) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		return null;
	}
	
	public User deposit(User u, double value)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		return null;
	}
	
	public User fundTransfer(User u, User ur, double value)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		return null;
	}
	
	public User printTransactions(User u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		return null;
	}
	
	public User showBalance(User u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		for(User person:repo.findAll()){
			if(person.getUserID() == u.getUserID()){
				return person;
			}
		}
		return null;
		
	}
}
