package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.model.*;
import com.example.demo.repository.*;


@Service
public class EmployeeService{
    @Autowired
    private EmployeeRepo er;
    public Employee createEmployee(Employee p){
        return er.save(p);
    }

    public List<Employee> getAllEmployees(){
        return er.findAll();
    }
    public Optional<Employee> getEmployeeById(Integer employeeId){
        return er.findById(employeeId);
    }
    public boolean updateDetails(int employeeId,Employee e)
    {
        if(this.getEmployeeById(employeeId)==null)
        {
            return false;
        }
        try{
            er.save(e);
        }
        catch(Exception a)
        {
            return false;
        }
        return true;
    }
public boolean deleteEmployee(int employeeId)
    {
        if(this.getEmployeeById(employeeId) == null)
        {
            return false;
        }
        er.deleteById(employeeId);
        return true;
    }


}