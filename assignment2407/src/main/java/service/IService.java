package service;


import exceptions.IncorrectDateRange;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAccountException;
import exceptions.WithdrawalExcessiveException;
import user.User;

interface IService {

	String createAccount(User u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User withdraw(User u, double value,int date) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User deposit(User u, double value,int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User fundTransfer(User u, User uR, double value,int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User printTransactions(User u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User printTransactions(User u, int datefrom, int dateto)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User showBalance(User u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	
}
