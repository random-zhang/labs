package com.wan.service.impl;

import com.jie.dao.EmployeeDaoI;
import com.wan.model.Employee;
import com.wan.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    EmployeeDaoI  employeeDaoI =null;
    EmployeeServiceImpl(){}
    @Transactional(propagation = Propagation.REQUIRES_NEW,isolation= Isolation.READ_COMMITTED)
    public List<Employee> getEmployees() {
        return  employeeDaoI.getEmployees();
    }
}
