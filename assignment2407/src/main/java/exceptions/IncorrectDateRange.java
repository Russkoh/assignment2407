package exceptions;

public class IncorrectDateRange extends Exception{

	private static final long serialVersionUID = 1L;

	IncorrectDateRange(String s){
		super(s);
	}
}
