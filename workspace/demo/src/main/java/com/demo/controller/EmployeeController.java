package com.demo.controller;

import com.demo.entities.entities.Employee;
import com.demo.service.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    private EmployeeService
    employeeService;
    public EmployeeController(EmployeeService
                                      employeeService){
        this.employeeService=employeeService;
    }
    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody
                            Employee
                            employee){
return
        new ResponseEntity<>(employeeService.createEmployee(employee), HttpStatus.CREATED);
    }
}
