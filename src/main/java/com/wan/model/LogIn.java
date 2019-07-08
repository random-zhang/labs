package com.wan.model;

public class LogIn {
    private Integer userId;

    private Integer employeeId;

    private String emmPassword;

	public LogIn(Integer userId, Integer employeeId, String emmPassword) {
		super();
		this.userId = userId;
		this.employeeId = employeeId;
		this.emmPassword = emmPassword;
	}

	public LogIn() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmmPassword() {
		return emmPassword;
	}

	public void setEmmPassword(String emmPassword) {
		this.emmPassword = emmPassword;
	}

    
    
    
}