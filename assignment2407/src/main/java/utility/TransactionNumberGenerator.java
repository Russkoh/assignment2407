package utility;

public class TransactionNumberGenerator {
	
	public static int transactionID;
	
	static{
		transactionID =1;
	}

	private TransactionNumberGenerator(){
		
	}
	
	public static int getUniqueTransactionID(){
		return transactionID++;
	}
}
