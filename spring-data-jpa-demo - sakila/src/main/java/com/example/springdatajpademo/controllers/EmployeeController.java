package com.example.springdatajpademo.controllers;

import com.example.springdatajpademo.model.Employee;
import com.example.springdatajpademo.model.EmployeeResponse;
import com.example.springdatajpademo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/{id}")
    public EmployeeResponse findById(@PathVariable long id) {
        Employee employee = employeeService.findById(id);
        EmployeeResponse response = new EmployeeResponse();
        response.setId(employee.getId());
        response.setFirstName(employee.getFirstName());
        response.setLastName(employee.getLastName());
        response.setDepartment(employee.getDepartment());
        response.setUser(employee.getUser());
        return response;
    }

    @GetMapping("/emp/dep")
    public List<Employee> findEmpDepartment(@RequestParam String empName, @RequestParam String depName) {
        return employeeService.findByNameContainingAndDepartmentNameContaining(empName, depName);
    }

    @GetMapping("count/emp/dep")
    public Long countByNameDepartment(@RequestParam String empName, @RequestParam String depName) {
        return employeeService.countByNameContainingAndDepartmentNameContaining(empName, depName);
    }

    @GetMapping("/department/{DepId}")
    public List<Employee> findEmpInDep(@PathVariable long DepId) {
        return employeeService.findEmpDep(DepId);
    }

    @PostMapping()
    public Long insert(@RequestBody Employee employee) {
        return employeeService.insert(employee).getId();
    }

    @PutMapping()
    public Long update(@RequestBody Employee employee) {
        return employeeService.update(employee).getId();
    }

    @GetMapping("/filter")
    public ResponseEntity<?> findByName(@RequestParam String name, @RequestParam int pageNumber, @RequestParam int pageSize,
                                        @RequestParam String sortCol, @RequestParam boolean isAsc) {
        return ResponseEntity.ok(employeeService.filter(name, pageNumber, pageSize, sortCol, isAsc));
    }

}
