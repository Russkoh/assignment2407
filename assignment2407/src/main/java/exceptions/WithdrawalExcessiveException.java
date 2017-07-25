package exceptions;

public class WithdrawalExcessiveException extends Exception {


	private static final long serialVersionUID = 1L;
	
	public WithdrawalExcessiveException(String s){
		
		super(s);
	}

}
