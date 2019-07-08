package com.wan.dto;

import java.util.Date;


public class LoginDto {
	private Integer userId;

	private String emmPassword;
	
	private Integer employeeId;

    private String employeeName;

    private Integer departmentId;

    private Integer positonId;

    private String phone;

    private String email;

    private String status;

    private Integer parentEmployeeId;

    private Date createTime;

    private Date updateTime;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getEmmPassword() {
		return emmPassword;
	}

	public void setEmmPassword(String emmPassword) {
		this.emmPassword = emmPassword;
	}

	public Integer getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public Integer getPositonId() {
		return positonId;
	}

	public void setPositonId(Integer positonId) {
		this.positonId = positonId;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getParentEmployeeId() {
		return parentEmployeeId;
	}

	public void setParentEmployeeId(Integer parentEmployeeId) {
		this.parentEmployeeId = parentEmployeeId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	
	
	
	public LoginDto(Integer userId, String emmPassword, Integer employeeId,
			String employeeName, String status) {
		super();
		this.userId = userId;
		this.emmPassword = emmPassword;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.status = status;
	}

	public LoginDto(Integer userId, String emmPassword, Integer employeeId,
			String employeeName, Integer departmentId, Integer positonId,
			String phone, String email, String status,
			Integer parentEmployeeId, Date createTime, Date updateTime) {
		super();
		this.userId = userId;
		this.emmPassword = emmPassword;
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.departmentId = departmentId;
		this.positonId = positonId;
		this.phone = phone;
		this.email = email;
		this.status = status;
		this.parentEmployeeId = parentEmployeeId;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

	public LoginDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "LoginDto [userId=" + userId + ", emmPassword=" + emmPassword
				+ ", employeeId=" + employeeId + ", employeeName="
				+ employeeName + ", departmentId=" + departmentId
				+ ", positonId=" + positonId + ", phone=" + phone + ", email="
				+ email + ", status=" + status + ", parentEmployeeId="
				+ parentEmployeeId + ", createTime=" + createTime
				+ ", updateTime=" + updateTime + "]";
	}
    
    
}
