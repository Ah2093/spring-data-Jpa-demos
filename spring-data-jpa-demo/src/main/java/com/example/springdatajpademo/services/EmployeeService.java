package com.example.springdatajpademo.services;

import com.example.springdatajpademo.model.Employee;
import com.example.springdatajpademo.repositroy.EmployeeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepos employeeRepos ;
    private DepartmentService departmentService ;

    public EmployeeService(EmployeeRepos employeeRepos, DepartmentService departmentService) {
        this.employeeRepos = employeeRepos;
        this.departmentService = departmentService;
    }

    public Employee findById(long id )
    {
        return employeeRepos.findById(id).orElseThrow();}
    public Employee insert (Employee employee)
    {
        if(employee.getDepartment()!=null && employee.getDepartment().getId()!= null)
        {
            employee.setDepartment(departmentService.findById(employee.getDepartment().getId()));

        }
        return employeeRepos.save(employee);

    }
    public List<Employee> findBySalary(double salary){
        return employeeRepos.findBySalary(salary);
    }

    public List<Employee> findByNameContainingAndDepartmentNameContaining(String empName,String depName){
        return employeeRepos.findByNameContainingAndDepartmentNameContaining(empName,depName);
    }

    public Long countByNameContainingAndDepartmentNameContaining(String empName,String depName)
    {
        return employeeRepos.countByNameContainingAndDepartmentNameContaining(empName,depName);
    }


    public List<Employee> findAll()
    {
        return employeeRepos.findAll();
    }

    public List<Employee> findEmpDep(long id)
    {
        return employeeRepos.findByDepartment(id);
    }
    public Employee update (Employee employee)
    {
        Employee current = employeeRepos.findById(employee.getId()).get();
        //handle the exception if this employee isn't in the db
        // and insert it
        //else edit it and persist updated
        current.setName(employee.getName());
        current.setSalary(employee.getSalary());
        current.setDepartment(employee.getDepartment());
        return employeeRepos.save(current);

    }

}
