package com.cg.mywalletapp.service;

import java.util.Map;

import com.cg.mywalletapp.beans.Customer;

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