package bank.assignment2407;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import service.Service;
import user.Customer;
import user.User;
import exceptions.*;
import repository.Repository;


public class AppTest 
{
	private Service service;
	@Before
	public void init(){
		
		service = new Service(new Repository());
	}
	
	@Test
    public void addingAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
    	
    	assertEquals(300, service.createAccount(new Customer("Sagar Kulkarni"), 300.0d).getBalance(),0);
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void addingInsufficientAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
		service.createAccount(new Customer("Sagar Kulkarni"), 30.0d);
	}
	
	
	@Test
	public void withdrawingMoney() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{

		User u = service.createAccount(new Customer("Sagar Kulkarni"), 300.0d);
		User p = service.withdraw(u.getUserID(), 200.0d, 0724);
		
		assertEquals(100, p.getBalance(),0);
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void withdrawingMoreThanInAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		
		User u = service.createAccount(new Customer("Sagar Kulkarni"), 300.0d);
		service.withdraw(u.getUserID(), 400.0d, 0724);
		
	}
	
	@Test(expected = WithdrawalExcessiveException.class)
	public void withdrawingMoreThanLimit() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		service.withdraw(service.createAccount(new Customer("Sagar Kulkarni"), 1300.0d).getUserID(), 1100.0d, 0724);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void withdrawingFromInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		service.withdraw(2, 100.0d, 0724);
	}
	
	@Test
	public void depositingMoney() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = service.createAccount(new Customer("Sagar Kulkarni"), 300.0d);
		u = service.deposit(u.getUserID(), 100.0d, 0724);
		
		assertEquals(400,u.getBalance(),0);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void depositingToInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		service.deposit(2, 1100.0d, 0724);
	}
	
	@Test
	public void viewingBalance() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		
		assertEquals(1300, service.createAccount(new Customer("Sagar Kulkarni"), 1300.0d).getBalance(),0);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void viewBalanceOfInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = new User(2);
		u.setBalance(10.0d);
		service.showBalance(2);
	}
	
	@Test
	public void transferFunds()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
	
		User u = service.createAccount(new Customer("Sagar Kulkarni"), 300.0d);
		User p = service.createAccount(new Customer("Peter"), 300.0d);
		User o = service.fundTransfer(u.getUserID(), p.getUserID(), 100.0d, 0724);
		assertEquals(200,o.getBalance(),0);
	}
	
	@Test(expected = InsufficientFundsException.class)
	public void transferFundsWithInsufficientFunds()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = service.createAccount(new Customer("Sagar Kulkarni"), 300.0d);
		User p = service.createAccount(new Customer("Peter"), 300.0d);
		service.fundTransfer(u.getUserID(), p.getUserID(), 400.0d, 0724);
	}
	
	@Test(expected = InvalidAccountException.class)
	public void transferFundsToInvalidAccount() throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange{
		User u = service.createAccount(new Customer("Sagar Kulkarni"), 300.0d);
		service.fundTransfer(u.getUserID(), 2, 200.0d, 0724);
	}

	@Test
	public void transactionHistory()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User o = service.createAccount(new Customer("Sagar Kulkarni"), 1300.0d);
		User u = service.withdraw(o.getUserID(), 100.0d, 0724);
		User p = service.printTransactions(u.getUserID());
	}
	
	@Test(expected = InvalidAccountException.class)
	public void transactionHistoryOfInvalidAccount()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User o = service.withdraw(service.createAccount(new Customer("Sagar Kulkarni"), 1300.0d).getUserID(), 100.0d, 0724);
		User p = service.printTransactions(3);
		
	}
	
	@Test
	public void transactionHistoryWithDates()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = service.withdraw(service.createAccount(new Customer("Sagar Kulkarni"), 1300.0d).getUserID(), 100.0d, 0724);
		service.printTransactions(u.getUserID(),0724,0725);
	}
	
	@Test(expected = IncorrectDateRange.class)
	public void viewingTransactionHistoryWithoutValidDateRange()throws InsufficientFundsException,WithdrawalExcessiveException, InvalidAccountException, IncorrectDateRange {
		User u = service.withdraw(service.createAccount(new Customer("Sagar Kulkarni"), 1300.0d).getUserID(), 100.0d, 0723);
		service.printTransactions(u.getUserID(),0724,0725);
		
	}
    
    
}
