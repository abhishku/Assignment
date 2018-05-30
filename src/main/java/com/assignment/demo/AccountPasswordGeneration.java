package com.assignment.demo;
import java.util.Random;

public class AccountPasswordGeneration {
	    public static StringBuilder generatePassword()
	    {
	    		String a="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	    		Random r=new Random();
	    		StringBuilder result=new StringBuilder();
	    		for(int i=0;i<8;++i)
	    		{
	    			int randIndex=r.nextInt(a.length());
	    			result.append(a.charAt(randIndex));
	    		}
	    	return(result);
	    } 
}

