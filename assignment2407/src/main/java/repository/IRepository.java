package repository;

import java.util.List;

import user.User;


public interface IRepository {

	boolean saveAccount(User u);
	User findAccount(int userID);

}
