package com.company.RestCrudDemo.rest;

import com.company.RestCrudDemo.entity.Employee;
import com.company.RestCrudDemo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {
    private EmployeeService employeeService;
    @Autowired
    public EmployeeRestController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> findAll()
    {
        return employeeService.findAll();
    }

    @GetMapping("/{employeeId}")
    public Employee findById(@PathVariable int employeeId)
    {
        Employee employee =employeeService.findById(employeeId);
        if (employee == null )
            throw  new RuntimeException ("Employee id not found "+ employeeId);
        return  employee ;
    }
    @DeleteMapping("/{employeeId}")
    public String delete (@PathVariable int employeeId )
    {
        Employee e =employeeService.findById(employeeId);
        if (e == null)
            throw new RuntimeException("Employee id not found - "+employeeId);
        //else
        employeeService.deleteById(employeeId);

        return "Deleted employee id - "+employeeId;
    }
    @PostMapping
    public Employee AddEmployee (@RequestBody Employee e)
    {
        // to force the insert in dao set id =0
        e.setId(0);
        Employee dbEmployee =employeeService.save(e);
        return dbEmployee;
    }
    @PutMapping
    public Employee updateEmployee(@RequestBody Employee employee)
    {
        return employeeService.save(employee);
    }
}
