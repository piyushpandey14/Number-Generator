package com.assignment.numbergenerator.util;

public enum Status {

	SUCCESS("Request is successfully completed"),
	IN_PROGRESS("Request is in progress"),
	ERROR("Some error occurred while processing request");
	
	private final String description;
	
	Status(String description){
		this.description = description;
	}
	
	public String getDescription(){
		return description;
	}
}
