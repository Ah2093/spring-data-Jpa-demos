package com.company.RestCrudDemo.service;

import com.company.RestCrudDemo.daorepository.EmployeeDAO;
import com.company.RestCrudDemo.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    //injecting EmployeeDAO to access data source
    private EmployeeDAO employeeDAO ;
    //implement findAll method to get a list of employee by delegating call to employeeDAO.findAll()
    @Autowired
    public EmployeeServiceImpl(EmployeeDAO employeeDAO) {
        this.employeeDAO = employeeDAO;
    }



    @Override
    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }

    @Override
    public Employee findById(int id) {
        return employeeDAO.findById(id);
    }

    @Transactional
    @Override
    public Employee save(Employee employee) {
        return employeeDAO.save(employee);
    }

    @Transactional
    @Override
    public void deleteById(int id) {
        employeeDAO.deleteById(id);
    }
}
