package repository;

import user.User;

public class Repository implements IRepository {
	
	private User[] users;
	public String saveAccount(int userID, double value){
		
		User[] copyOfUsers = new User[users.length+1];
		
		for(int i = 0; i< users.length; i++){
			copyOfUsers[i] = users[i];	
		}
		
		copyOfUsers[users.length] = new User(userID,value);
		users = copyOfUsers;
		
		return "Account Saved";
	}
	
	public boolean findAccount(int userID){
		for(User person:users){
			if(person.getUserID() == userID){
				return false;
			}
		}
		return true;
	}
	
	public User[] findAll(){
		return this.users;
	}

}
