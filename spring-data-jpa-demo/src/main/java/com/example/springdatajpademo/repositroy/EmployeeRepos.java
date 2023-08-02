package com.example.springdatajpademo.repositroy;

import com.example.springdatajpademo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface EmployeeRepos extends JpaRepository<Employee, Long> {

    List<Employee> findBySalary(double salary);

    //spring will write sql form method name this is called Drived query
    List<Employee> findByName(String name);

    List<Employee> findByNameContainingAndDepartmentNameContaining(String empName,String depName);
    Long countByNameContainingAndDepartmentNameContaining(String empName,String depName);
    @Transactional
    Long deleteByNameContainingAndDepartmentNameContaining(String empName,String depName);

    //using JPQL
    @Query(value = "select emp from Employee emp where emp.name = :name")
    List<Employee> filter(String name);

    @Query(value = "select * form hr_employee where name = :name" ,nativeQuery = true)
    List<Employee> filterNative(String name);

    List<Employee> findByDepartmentId(long depId);

    //make use of JPQL to rewrite the same query
    // Declared query
    @Query(value = "select emp from Employee emp Join emp.department dept where dept.id = :depId")
    List<Employee> findByDepartment(long depId);




}
