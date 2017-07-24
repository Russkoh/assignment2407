package service;

import user.User;

interface IService {

	String createAccount(User u)throws Exception;
	User withdraw(User u, double value) throws Exception;
	User deposit(User u, double value)throws Exception;
	User fundTransfer(User u, User uR, double value)throws Exception;
	User printTransactions(User u)throws Exception;
	User showBalance(User u)throws Exception;
	
}
