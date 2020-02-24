package com.cg.mywalletapp.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.junit.BeforeClass;


import com.cg.mywalletapp.beans.Customer;
import com.cg.mywalletapp.beans.Wallet;
import com.cg.mywalletapp.exceptions.*;
import com.cg.mywalletapp.service.*;

public class Test {
	
	static WalletService wallet;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception 
	{ 
		HashMap<String,Customer> data= new HashMap<String, Customer>();
		
		Customer cust1 = new Customer("aditya","1234567890",new Wallet(new BigDecimal(5600.00)),"aditya","asdfgh");
		Customer cust2 = new Customer("nikhil","1234567891",new Wallet(new BigDecimal(6600.00)),"nikhil","zcxzcx");
			
	 data.put("aditya", cust1);
	 data.put("nikhil", cust2);	
		
	 wallet= new WalletService(data);
	
		
	}
	
	
	
	@org.junit.Test(expected=MyInvalidInputException.class)
	public void testCreateAccount()       //mobile number is less than 10 digit
	{
		
		wallet.createAccount("abhinav", "1233","abhi","abbb");
	}
	@org.junit.Test(expected=MyInvalidInputException.class)
	public void testCreateAccountInvalidPassword()       //password validation failed
	{
		
		wallet.createAccount("aditya", "1233","aditya","abbb");
	}
	@org.junit.Test(expected=UserAlreadyRegistered.class)// mobile number already registerd
	public void testCreateAccount2() 
	{
		
		wallet.createAccount("aditya","1234567890","aditya","asdfgh");
	}
	
	@org.junit.Test
	public void testCreateAccount3()    //succesful creation
	{
		
		assertEquals(new Customer("Ramesh","1234567891",new Wallet(new BigDecimal(0.00)),"Ramesh","zcxzcx"), wallet.createAccount("Ramesh", "1234567891","Ramesh","zcxzcx"));
	}
	@org.junit.Test
	public void testLogin() {				//check if the password is correct
		assertTrue(wallet.Login("aditya","asdfgh"));
	}
	@org.junit.Test(expected=AuthenticationFailedException.class)
	public void failedLogin() {				//check if the password is incorrect and authentication is failed
		wallet.Login("aditya","asdfga");
	}
	@org.junit.Test(expected=AuthenticationFailedException.class)
	public void invalidUserForLogin() {				//check User doesn't exist is incorrect
		wallet.Login("adi","asdfg");
	}
}
