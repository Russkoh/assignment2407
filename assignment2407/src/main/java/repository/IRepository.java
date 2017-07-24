package repository;

interface IRepository {

	String saveAccount(int userID, double value);
	boolean findAccount(int userID);
}
