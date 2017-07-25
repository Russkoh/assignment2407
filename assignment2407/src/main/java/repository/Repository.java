package repository;

import user.User;

public class Repository implements IRepository {
	
	private User[] users = new User[0];
	public String saveAccount(int userID, double value){
		
		User[] copyOfUsers = new User[users.length+1];
		
		for(int i = 0; i< users.length; i++){
			copyOfUsers[i] = users[i];	
		}
		
		copyOfUsers[users.length] = new User(userID,value);
		users = copyOfUsers;
		
		return "Account has been saved";
	}
	
	public int findAccount(int userID){
		for(int i = 0; i < users.length; i++){
			if(users[i].getUserID() == userID){
				return i;
			}
		}
		return -1;
	}
	
	public User[] findAll(){
		return this.users;
	}

}
