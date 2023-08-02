package com.example.springdatajpademo.services;

import com.example.springdatajpademo.model.Employee;
import com.example.springdatajpademo.projection.EmployeeProjection;
import com.example.springdatajpademo.repositroy.EmployeeRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {

    private EmployeeRepos employeeRepos ;
    private DepartmentService departmentService ;

    @Autowired
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
    /*public List<Employee> findBySalary(double salary){
        return employeeRepos.findBySalary(salary);
    }*/

    public List<Employee> findByNameContainingAndDepartmentNameContaining(String empName,String depName){
        return employeeRepos.findByFirstNameContainingAndDepartmentNameContaining(empName,depName);
    }

    public Long countByNameContainingAndDepartmentNameContaining(String empName,String depName)
    {
        return employeeRepos.countByFirstNameContainingAndDepartmentNameContaining(empName,depName);
    }

    public void deleteByNameContainingAndDepartmentNameContaining(String empName,String depName)
    {
        employeeRepos.deleteByFirstNameContainingAndDepartmentNameContaining(empName,depName);
    }

    public Page<EmployeeProjection> filter(String name , int pageNumber , int pageSize , String sortCol , boolean isAsc)
    {
            if(name.isEmpty() || name.isEmpty() || name==null ) {
                name = null;
            }
            Pageable page = PageRequest.of(pageNumber,pageSize,Sort.by((isAsc? Sort.Direction.ASC : Sort.Direction.DESC) ,sortCol));

            return employeeRepos.filter(name,page);
    }

    public List<Employee> findAll()
    {
        return employeeRepos.findAll();
    }

    public List<Employee> findEmpDep(long id)
    {
        return employeeRepos.findByDepartmentId( id);
    }
    public Employee update (Employee employee)
    {
        Employee current = employeeRepos.findById(employee.getId()).get();
        //handle the exception if this employee isn't in the db
        // and insert it
        //else edit it and persist updated
        current.setFirstName(employee.getFirstName());
        current.setLastName(employee.getLastName());
        current.setDepartment(employee.getDepartment());
        //persist to database
        return employeeRepos.save(current);

    }

}
