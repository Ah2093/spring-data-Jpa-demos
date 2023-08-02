package com.company.RestCrudDemo.daorepository;

import com.company.RestCrudDemo.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    List<Employee> findAll();
    Employee findById(int id);
    void add(Employee e);
    Employee save(Employee employee);
    void deleteById(int id);


}
