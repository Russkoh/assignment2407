package exceptions;

public class WithdrawalExcessiveException extends Exception {


	private static final long serialVersionUID = 1L;
	
	WithdrawalExcessiveException(String s){
		
		super(s);
	}

}
