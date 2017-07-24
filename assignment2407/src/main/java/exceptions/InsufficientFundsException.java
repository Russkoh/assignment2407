package exceptions;

public class InsufficientFundsException extends Exception {


	private static final long serialVersionUID = 1L;
	
	InsufficientFundsException(String s){
		super(s);
	}

}
