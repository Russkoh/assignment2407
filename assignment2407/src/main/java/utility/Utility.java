package utility;

public class Utility {
	private static int iD = 1;
	private static int transactionID = 1;
	
	private Utility(){
		
	}
	
	public static int uniqueIDGenerator(){
		return iD++;
	}
	
	public static int uniqueTransactionGenerator(){
		return transactionID++;
	}
}
