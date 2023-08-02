package com.company.RestCrudDemo.service;

import com.company.RestCrudDemo.entity.Employee;
import com.company.RestCrudDemo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    //injecting EmployeeDAO to access data source
    private EmployeeRepository employeeRepository ;
    //implement findAll method to get a list of employee by delegating call to employeeDAO.findAll()
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    @Override
    public List<Employee> findAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Employee findById(int id) {
         Optional<Employee> reslut = employeeRepository.findById(id);
         Employee employee = null ;
         if (reslut.isPresent())
             employee=reslut.get();
         else throw new RuntimeException("Employee id not found - "+id);
         return employee ;
    }


    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    //you can remove @Transactional as it is provided by default in spring data jpa
    @Override
    public void deleteById(int id) {
        employeeRepository.deleteById(id);
    }
}
