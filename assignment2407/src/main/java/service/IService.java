package service;


import user.User;

interface IService {

	String createAccount(User u)throws Exception;
	User withdraw(User u, double value,int date) throws Exception;
	User deposit(User u, double value,int date)throws Exception;
	User fundTransfer(User u, User uR, double value,int date)throws Exception;
	User printTransactions(User u)throws Exception;
	User printTransactions(User u, int datefrom, int dateto)throws Exception;
	User showBalance(User u)throws Exception;
	
}
