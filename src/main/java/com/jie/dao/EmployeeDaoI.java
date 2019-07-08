package com.jie.dao;

import com.wan.model.Employee;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDaoI {
    List<Employee>  getEmployees();
}
