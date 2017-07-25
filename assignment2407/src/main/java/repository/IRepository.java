package repository;

import user.User;


public interface IRepository {

	String saveAccount(int userID, double value);
	int findAccount(int userID);
	User[] findAll();
}
