package com.cg.mywalletapp.pl;


import java.math.BigDecimal;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;


import com.cg.mywalletapp.beans.*;
import com.cg.mywalletapp.exceptions.*;
import com.cg.mywalletapp.service.*;

public class Client {
	static HashMap<String,Customer> map= new HashMap<>();
	
	public static void main(String[] args) {

		Customer cust1 = new Customer("aditya","1234567890",new Wallet(new BigDecimal(5600.00)),"aditya","asdfgh");
		Customer cust2 = new Customer("nikhil","1234567891",new Wallet(new BigDecimal(6600.00)),"nikhil","zcxzcx");
			
		map.put("aditya", cust1);
		 map.put("nikhil", cust2);	
		WalletService pk = new WalletService(map);
		int counter=0;
		while(counter<10) {
		System.out.println("Please enter your choice");
		System.out.println("1.Create an account");
		System.out.println("2.Login");
		System.out.println("3.Forgot PassWord");
		Scanner sc=new Scanner(System.in);
		int option=sc.nextInt();
		switch(option){
		case 1:
			try {
		System.out.println("Please enter your name");
		String name=sc.next();
		System.out.println("Please enter your mobile no");
		String mob=sc.next();
		System.out.println("Please enter your UserID for login credentials");
		String userid = sc.next();
		System.out.println("Please Enter your password");
		String password=sc.next();
		
		Customer cust = pk.createAccount(name,mob,userid,password);   //call the function to save the data
		System.out.println("You can now access your Account details");
		}
		catch(InputMismatchException e) {
			
			System.out.println("Amount should be in decimal Please choose choice again");
		}
		catch(UserAlreadyRegistered  | MyInvalidInputException e ) {
			e.getMessage();
		}
		break;
		
		case 2:
			try {
				System.out.println("Enter your userid");
				String userid = sc.next();
				System.out.println("Enter your password");
				String password = sc.next();
				pk.Login(userid, password);
			}catch(AuthenticationFailedException e) {
				
			}
		  break;
		case 3:
			
				System.out.println("Please enter your name");
				String name=sc.next();
				System.out.println("Please enter your mobile no");
				String mob=sc.next();
				System.out.println("Please enter your UserID for login credentials");
				String userid = sc.next();
				System.out.println("Your password is: ");
				System.out.println(pk.forgotPassword(name, mob, userid));
				break;
		
		default: System.out.println("Incorrect choice Entered");
		}
		counter++;
		}
	}	
	

}
