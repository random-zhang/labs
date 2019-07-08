package com.wan.model;

import java.util.Date;
import java.util.List;

public class Employee {
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
    
    
    public Employee() {
		super();
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
        this.employeeName = employeeName == null ? null : employeeName.trim();
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
        this.phone = phone == null ? null : phone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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

	public Employee(Integer employeeId, String employeeName,
			Integer departmentId, Integer positonId, String phone,
			String email, String status, Integer parentEmployeeId,
			Date createTime, Date updateTime) {
		super();
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
    
}