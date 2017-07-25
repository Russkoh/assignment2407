package service;


import exceptions.IncorrectDateRange;
import exceptions.InsufficientFundsException;
import exceptions.InvalidAccountException;
import exceptions.WithdrawalExcessiveException;
import user.Customer;
import user.User;

interface IService {

	User createAccount(Customer customer, double value)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User withdraw(int u, double value,int date) throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User deposit(int u, double value,int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User fundTransfer(int userID, int userReceivingID, double value, int date)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User printTransactions(int u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User printTransactions(int u, int datefrom, int dateto)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	User showBalance(int u)throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange;
	
}
