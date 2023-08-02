package com.example.springdatajpademo.repositroy;

import com.example.springdatajpademo.HRStatisticProjection;
import com.example.springdatajpademo.model.Employee;
import com.example.springdatajpademo.projection.EmployeeProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface EmployeeRepos extends JpaRepository<Employee, Long> {


    //spring will write sql form method name this is called Drived query
    List<Employee> findByFirstName(String name);
    List<Employee> findByLastName (String lastName);
    List<Employee> findByFirstNameContainingAndDepartmentNameContaining(String empName,String depName);
    Long countByFirstNameContainingAndDepartmentNameContaining(String empName,String depName);
    @Transactional
    Long deleteByFirstNameContainingAndDepartmentNameContaining(String empName,String depName);

    //using JPQL and Pageable object
    //using interface projection without specifying mapping

    @Query(value = "select emp from Employee emp where (:name is null or emp.firstName = :name)")
    Page<EmployeeProjection> filter(String name , Pageable pageable);


    /*
    //using entity constructor injection

    @Query(value = "select new Employee(emp.id,emp.firstName,emp.lastName) from Employee emp where (:name is null or emp.firstName = :name)")
    Page<Employee> filter(String name , Pageable pageable);
    */
    /*
    //using JPQL
    @Query(value = "select emp from Employee emp where (:name is null or emp.firstName = :name)")
    List<Employee> filter(String name );
*/
    /*
    //using JPQL and sorting object
    @Query(value = "select emp from Employee emp where (:name is null or emp.firstName = :name)")
    List<Employee> filter(String name , Sort sort);
*/
//    @Query(value = "select * form actor where name = :name" ,nativeQuery = true)
//  List<Employee> filterNative(String name);

    List<Employee> findByDepartmentId(long depId);

    //make use of JPQL to rewrite the same query
    // Declared query
    /*
    @Query(value = "select emp from Employee emp Join emp.department dept where dept.id = :depId")
    List<Employee> findByDepartment(long depId);
    */

    // using interface projection to project wanted attributes from entity and create a query for them
    @Query(value = "select (select count(*) from hr_department ) deptCount ," +
            "(select count(*) from actor ) empCount ," +
            "(select count(*) from user) userCount)",nativeQuery = true)
    HRStatisticProjection getHrStatistic();

}
