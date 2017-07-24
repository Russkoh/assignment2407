package bank.assignment2407;

import org.junit.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import service.Service;
import utility.Utility;
import user.User;
import exceptions.*;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
	Service service = new Service();
    
    public void addingAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
    	User u = new User(new Utility().UniqueIDGenerator(), 150.0d);
    	service.createAccount(u);
		
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void addingInsufficientAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = new User(new Utility().UniqueIDGenerator(), 10.0d);
		service.createAccount(u);
	}
	
	
	public void withdrawingMoney() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(new Utility().UniqueIDGenerator(), 150.0d);
    	service.createAccount(u);
		service.withdraw(u, 50.0d);
		assertEquals(100.0d ,u.getBalance());
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void withdrawingMoreThanInAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = new User(new Utility().UniqueIDGenerator(), 140.0d);
    	service.createAccount(u);
		service.withdraw(u, 150.0d);
	}
	
	@Test(expected = WithdrawalExcessiveException.class)
	public void withdrawingMoreThanLimit() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(new Utility().UniqueIDGenerator(), 1400.0d);
    	service.createAccount(u);
    	service.withdraw(u, 1100.0d);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void withdrawingFromInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(2, 1400.0d);
		User p = null;
		service.createAccount(u);
		service.withdraw(p, 1100.0d);
		
	}
	
	
	public void depositingMoney() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(new Utility().UniqueIDGenerator(), 1400.0d);
		service.createAccount(u);
		service.deposit(u, 1100.0d);
		assertEquals(2500.0d, u.getBalance());
	}
	
	@Test(expected = InvalidAccountException.class)
	public void depositingToInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(new Utility().UniqueIDGenerator(), 1400.0d);
		User p = new User(new Utility().UniqueIDGenerator(), 1400.0d);
		service.createAccount(u);
		service.deposit(p, 1100.0d);
	}
	
	
	public void viewingBalance() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(new Utility().UniqueIDGenerator(), 1400.0d);
		service.createAccount(u);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void viewBalanceOfInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
	}
	
	
	public void transferFunds()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
	}
	@Test(expected = InsufficientFundsException.class)
	public void transferFundsWithInsufficientFunds()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
	}
	
	@Test(expected = InvalidAccountException.class)
	public void transferFundsToInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
	}

	
	public void transactionHistory()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
	}
	
	@Test(expected = InvalidAccountException.class)
	public void transactionHistoryOfInvalidAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
	}
	
	
	public void transactionHistoryWithDates()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
	}
	
	@Test(expected = IncorrectDateRange.class)
	public void viewingTransactionHistoryWithoutValidDateRange()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
	}
    
    
}
