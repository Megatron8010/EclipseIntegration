package com.cg.mywalletapp.beans;

import java.math.BigDecimal;

public class Wallet {
private BigDecimal balance;

public Wallet(BigDecimal amount) {
	this.balance=amount;
}

public BigDecimal getBalance() {
	return balance;
}

public void setBalance(BigDecimal balance) {
	this.balance = balance;
}

@Override
	public String toString() {
	return ", balance="+balance;
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + ((balance == null) ? 0 : balance.hashCode());
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	Wallet other = (Wallet) obj;
	if (balance == null) {
		if (other.balance != null)
			return false;
	} else if (!balance.equals(other.balance))
		return false;
	return true;
}

}