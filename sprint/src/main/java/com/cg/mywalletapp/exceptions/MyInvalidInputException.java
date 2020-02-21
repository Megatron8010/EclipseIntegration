package com.cg.mywalletapp.exceptions;

public class MyInvalidInputException extends RuntimeException{
	public MyInvalidInputException(String msg) {
		super(msg);
		System.out.println(msg);
	}
}
