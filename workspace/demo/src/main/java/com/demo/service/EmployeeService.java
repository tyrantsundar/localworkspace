package com.demo.service;

import com.demo.entities.entities.Employee;
import org.springframework.stereotype.Service;


public interface EmployeeService {

    Employee createEmployee(Employee employee);
}
