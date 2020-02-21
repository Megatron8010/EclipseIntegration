package com.cg.mywalletapp.service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.cg.mywalletapp.exceptions.*;
import com.cg.mywalletapp.beans.Wallet;

import com.cg.mywalletapp.beans.Customer;

public class WalletService {
	private WalletServiceUtility repo;
	boolean state = false;
	HashMap<String,Customer> data= new HashMap<String,Customer>();
	public WalletService(HashMap<String,Customer> data) {

		repo= new WalletServiceUtility(data);   //passed to utility functions
		this.data = data;
	}

	public Customer createAccount(String name, String mob, String userid,String password)  {

		
		//Regex for validation of 10 digit mobile number
		Pattern p=Pattern.compile("[0-9]{10}");
		Matcher m=p.matcher(mob);
	
		if(m.matches()) {
			Customer new_customer=null;
			
			new_customer=repo.findCustomer(userid);
				if(new_customer==null) {				//if doesn't exist then create
				Wallet ob=new Wallet(new BigDecimal(0.00));
                Customer customer=new Customer(name,mob,ob,userid,password);
		        new_customer=customer;
		        
			    repo.save(customer);   //To update in hashmap
	            return new_customer;
			}
			else {
				throw new UserAlreadyRegistered("This userID is already registered");
			}
		}
		else {
			throw new MyInvalidInputException("please correctly enter 10 digit mobile number");
		}
	}
	public boolean Login(String Userid,String password) {
		
		if(data.containsKey(Userid)) {
			if(data.get(Userid).getPassword()==password) {
				System.out.println("Logged in");
				state= true;
				return state;
			}
			else {
				System.out.println("incorrect password");
			}
		}else {
			System.out.println("user not found");//create exception
		}
		
		return false;
	
	}
	
/*	public Customer showDetails(String mob) {
		Pattern p=Pattern.compile("[0-9]{10}");
		Matcher m=p.matcher(mob);
		if(m.matches())
		{
			Customer customer=null;
			customer = repo.findCustomer(mob);
			if(customer!=null)
				return customer;
		    else
		        throw new MobileNumberNotRegistered("your user is not registered");
		}
		else
		{
			throw new MyInvalidInputException("please correctly enter 10 digit mobile number");
		}
	}
	*/
}
class WalletServiceUtility{
	Map<String, Customer> data; 
	public WalletServiceUtility(Map<String, Customer> data) {
		super();
		this.data = data;
	}

	public Customer findCustomer(String userid) 
	{
	if (data.containsKey(userid)){		
		  return (data.get(userid));
		}
	else{
		return null;	
	}
	
}
	public void save(Customer customer) 
	{
		if(customer !=null) {
		
		data.put(customer.getUserID(), customer);
		}else {
			System.out.println("Customer is null!");	//Placeholder text
		}
		
		
	}
}