package com.assignment.demo;

public class RegistrationResponse {

public RegistrationResponse(String descriptionString, boolean success, StringBuilder stringBuilder) {
		this.descriptionString = description.SUCCESSFULL_STRING.toString();
		this.success = success;
		this.password = stringBuilder;
	}
public RegistrationResponse(String string, boolean b) {
	this.descriptionString = description.FAILURE_STRING.toString();
	this.success = b;
}
private String descriptionString;
private boolean success;
public String getDescriptionString() {
	return descriptionString;
}
public void setDescriptionString(description a) {
	this.descriptionString = a.toString();
}
public boolean isSuccess() {
	return success;
}
public void setSuccess(boolean success) {
	this.success = success;
}
public StringBuilder getPassword() {
	return password;
}
public void setPassword(StringBuilder password) {
	this.password = password;
}
private enum description{
	SUCCESSFULL_STRING("Your account is opened"),
	FAILURE_STRING("Account with that ID already exists");
private final String text;
description(final String text) {
    this.text = text;
}
@Override
public String toString() {
    return text;
}
}
private StringBuilder password;
@Override
public String toString() {
    if(this.password != null)
	return String.format(
            "{\"success\": \"%s\", \"description\": \"%s\", \"password\": \"%s\"}",
            success,descriptionString,password );
	return String.format(
            "{\"success\": \"%s\", \"description\": \"%s\"}",
            success,descriptionString);
}


}
