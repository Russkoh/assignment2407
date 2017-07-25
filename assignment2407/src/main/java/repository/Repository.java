package repository;

import java.util.ArrayList;
import java.util.List;

import user.User;

public class Repository implements IRepository {
	
	private List<User> users;
	
	public Repository(){
		super();
		users = new ArrayList<User>();
	}
	
	public boolean saveAccount(User u){
		
		users.add(u);
		
		return true;
	}
	
	public User findAccount(int userID){
		//System.out.println(userID);
		for(User u : users) {
			if(u.getUserID() == userID) {
				return u;
			}
		}
		return null;
	}

	

	
}
