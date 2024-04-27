package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController{
    @Autowired
    private EmployeeService es;

    @PostMapping("/employee")
    public ResponseEntity<Employee> createEmployee(@RequestBody Employee employee){
        Employee createdEmployee=es.createEmployee(employee);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdEmployee);
    }
    @GetMapping("/employees")
    public List<Employee> getAllEmployees(){
        return es.getAllEmployees();
    }

    @GetMapping("/employee/{employeeId}")
    public Optional<Employee> getEmployeeById(@PathVariable int employeeId){
        Optional<Employee> employee=es.getEmployeeById(employeeId);
        return employee;
    }
    @PutMapping("/employee/{employeeId}")
    public ResponseEntity<Employee> putMethod(@PathVariable("employeeId") int employeeId,@RequestBody Employee e)
    {
        if(es.updateDetails(employeeId,e) == true)
        {
            return new ResponseEntity<>(e,HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @DeleteMapping("/employee/{employeeId}")
    public ResponseEntity<Boolean> delete(@PathVariable("employeeId") int employeeId)
    {
        if(es.deleteEmployee(employeeId) == true)
        {
            return new ResponseEntity<>(true,HttpStatus.OK);
        }
        return new ResponseEntity<>(false,HttpStatus.NOT_FOUND);
    }

}
