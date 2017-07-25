package bank.assignment2407;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import service.Service;
import utility.Utility;
import user.User;
import exceptions.*;


public class AppTest 
{
	Service service = new Service();
	
    
	@Test
    public void addingAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
    	
		User u = new User(Utility.uniqueIDGenerator(), 150.0d);
    	assertEquals("Account has been saved",service.createAccount(u));
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void addingInsufficientAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
		User u = new User(Utility.uniqueIDGenerator(), 10.0d);
		service.createAccount(u);
	}
	
	
	@Test
	public void withdrawingMoney() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		User u = new User(Utility.uniqueIDGenerator(), 150.0d);
    	service.createAccount(u);
		
		assertEquals((int) 100,(int) service.withdraw(u, 50.0d, 0724).getBalance());
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void withdrawingMoreThanInAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		new Utility();
		User u = new User(Utility.uniqueIDGenerator(), 140.0d);
    	service.createAccount(u);
		service.withdraw(u, 150.0d,0724);
	}
	
	@Test(expected = WithdrawalExcessiveException.class)
	public void withdrawingMoreThanLimit() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		new Utility();
		User u = new User(Utility.uniqueIDGenerator(), 1400.0d);
    	service.createAccount(u);
    	service.withdraw(u, 1100.0d,0724);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void withdrawingFromInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(2, 1400.0d);
		User p = null;
		service.createAccount(u);
		service.withdraw(p, 1100.0d,0724);
		
	}
	

	@Test
	public void depositingMoney() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		new Utility();
		User u = new User(Utility.uniqueIDGenerator(), 1400.0d);
		service.createAccount(u);
		
		assertEquals((int) 2500.0d,(int) service.deposit(u, 1100.0d,0724).getBalance());
	}
	
	@Test(expected = InvalidAccountException.class)
	public void depositingToInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		User u = new User(1, 1400.0d);
		
		User p = new User(1, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.deposit(p, 1100.0d,0724);
	}
	
	@Test
	public void viewingBalance() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		new Utility();
		User u = new User(Utility.uniqueIDGenerator(), 1400.0d);
		service.createAccount(u);
		
		assertEquals(1400, (int)service.showBalance(u).getBalance());
	}
	
	@Test(expected = InvalidAccountException.class)
	public void viewBalanceOfInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(1, 1400.0d);
		User p = new User(1, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.showBalance(p);
	}
	
	@Test
	public void transferFunds()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
	
		User u = new User(1, 1400.0d);
		User p = new User(2, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		;
		assertEquals(600,(int)service.fundTransfer(u, p, 800.0d,0724).getBalance());
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void transferFundsWithInsufficientFunds()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = new User(1, 700.0d);
		User p = new User(2, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.fundTransfer(u, p, 800.0d,0724);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void transferFundsToInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(1, 1400.0d);
		User p = new User(1, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.fundTransfer(u, p, 800.0d,0724);
	}

	@Test
	public void transactionHistory()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = new User(1, 1400.0d);
		User p = new User(2, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.fundTransfer(u, p, 800.0d,0724);
		assertEquals("Fund transfer",service.printTransactions(u).getTransactions()[0].getTransactionDescription());
	}
	
	@Test(expected = InvalidAccountException.class)
	public void transactionHistoryOfInvalidAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = new User(1, 1400.0d);
		User p = new User(1, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.fundTransfer(u, p, 800.0d,0724);
		service.fundTransfer(u, p, 100.0d,0725);
		
	}
	
	@Test
	public void transactionHistoryWithDates()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = new User(1, 1400.0d);
		User p = new User(2, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.fundTransfer(u, p, 800.0d,0724);
		service.fundTransfer(u, p, 100.0d,0725);
		assertEquals(2,service.printTransactions(p,0724,0725).getTransactions().length);
	}
	
	@Test(expected = IncorrectDateRange.class)
	public void viewingTransactionHistoryWithoutValidDateRange()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = new User(1, 1400.0d);
		User p = new User(2, 1400.0d);
		service.createAccount(u);
		service.createAccount(p);
		service.fundTransfer(u, p, 800.0d,0723);
		service.printTransactions(p,0724,0725);
		
	}
    
    
}
