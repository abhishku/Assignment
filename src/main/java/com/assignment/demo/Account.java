package com.assignment.demo;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class Account {
    @Id
    private Long accountId;
    private StringBuilder password;

    public StringBuilder getPassword() {
		return password;
	}

	public void setPassword(StringBuilder password) {
		this.password = password;
	}

	protected Account() {}

    public Account(Long accountId) {
        this.accountId = accountId;
        this.password = AccountPasswordGeneration.generatePassword();
    }


	public Long getAccountId() {
		return accountId;
	}
	 @Override
	    public String toString() {
	        return String.format(
	                "Account[id=%d, password='%s']",
	                accountId,password );
	    }	 
}