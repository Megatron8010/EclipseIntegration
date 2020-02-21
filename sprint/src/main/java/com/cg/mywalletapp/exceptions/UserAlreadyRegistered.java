package com.cg.mywalletapp.exceptions;

public class UserAlreadyRegistered extends RuntimeException {
	public UserAlreadyRegistered(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
