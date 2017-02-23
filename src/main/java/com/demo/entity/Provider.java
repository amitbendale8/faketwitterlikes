package com.demo.entity;

import org.springframework.data.annotation.Id;

public class Provider {
	
	@Id
	private String id;
	
	private String email;
	
	private String name;
	
	private long phoneNumber;
	
	private  String[] areaServed;
	
	private String[] educationQualification;
	
	private Address address;
	
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String[] getAreaServed() {
		return areaServed;
	}
	public void setAreaServed(String[] areaServed) {
		this.areaServed = areaServed;
	}
	public String[] getEducationQualification() {
		return educationQualification;
	}
	public void setEducationQualification(String[] educationQualification) {
		this.educationQualification = educationQualification;
	}

}
