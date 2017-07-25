package utility;

public class Utility {
	private static int ID = 1;
	private static int transactionID = 1;
	
	public static int uniqueIDGenerator(){
		return ID++;
	}
	
	public static int uniqueTransactionGenerator(){
		return transactionID++;
	}
}
