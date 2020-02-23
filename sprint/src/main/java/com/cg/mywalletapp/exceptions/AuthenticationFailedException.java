package com.cg.mywalletapp.exceptions;

public class AuthenticationFailedException extends RuntimeException{
	public AuthenticationFailedException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
