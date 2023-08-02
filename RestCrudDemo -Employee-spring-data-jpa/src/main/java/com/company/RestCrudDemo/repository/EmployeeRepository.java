package com.company.RestCrudDemo.repository;

import com.company.RestCrudDemo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
