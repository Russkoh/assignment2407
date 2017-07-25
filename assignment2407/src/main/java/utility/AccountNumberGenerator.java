package utility;

public class AccountNumberGenerator{

	private static int iD;
	
	static{
		iD = 1;
	}
	
	private AccountNumberGenerator(){
		
	}
	
	public static int getUniqueID(){
		return iD++;
	}
	
	
}
