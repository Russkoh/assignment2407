package serverAssignment;

public class Utility {
	
	private static int ID = 1;
	private static int transactionID = 1;
	
	public int UniqueIDGenerator(){
		return ID++;
	}
	
	public int uniqueTransactionGenerator(){
		return transactionID++;
	}

}
