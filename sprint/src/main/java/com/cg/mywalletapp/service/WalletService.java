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
	private WalletServiceUtility repo;		//To access Collection HashMap
	boolean state = false;					//State=True for logged in False for logged out
	HashMap<String,Customer> data= new HashMap<String,Customer>();   
	public WalletService(HashMap<String,Customer> data) {

		repo= new WalletServiceUtility(data);   //passed to utility functions
		this.data = data;
	}

	public Customer createAccount(String name, String mob, String userid,String password)  {

		
		//Regex for validation of 10 digit mobile number
		Pattern p=Pattern.compile("[0-9]{10}");
		Matcher m=p.matcher(mob);
		
		//Regex for validation of password min 6 max 14 and whitespace is not allowed
		Pattern pswd_reg=Pattern.compile("[^\\s]*{5,14}");
		Matcher pswd_m=pswd_reg.matcher(password);
		
		if(m.matches() && pswd_m.matches()) {
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
		else if(m.matches()==false) {
			throw new MyInvalidInputException("please correctly enter 10 digit mobile number");
		}
		else {
			throw new MyInvalidInputException("please correctly enter password without spaces and length of 6-15 characters");
		}
	}
	public boolean Login(String Userid,String password) {
		
		if(data.containsKey(Userid)) {
			if(data.get(Userid).getPassword().contentEquals(password)) {
				System.out.println("Logged in");
				state= true;
				return state;
			}
			else {
				state = false;
				throw new AuthenticationFailedException("Authetication has failed as password is incorrect");
			}
		}else {
			state = false;
			throw new AuthenticationFailedException("Authetication has failed as the user doesn't exists");//create exception
			
		}
	
	
	}
	public String forgotPassword(String name, String mob, String userid) {
		Customer my_customer = repo.findCustomer(userid);
		if(userid.equals(my_customer.getUserID())   && name.equals(my_customer.getName()) && mob.equals(my_customer.getMobileNo()))
			return my_customer.getPassword();
		else {

			return "Your Details do not match please enter correct details to obtain your password";
		}
		 
		
	}
		

}
