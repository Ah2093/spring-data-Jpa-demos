package com.company.RestCrudDemo.daorepository;

import com.company.RestCrudDemo.entity.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeDAOImpl  implements EmployeeDAO{

    EntityManager entityManager;

    @Autowired
    public EmployeeDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
    @Override
    public List<Employee> findAll(){
        //creat typed query using jpql
        TypedQuery<Employee>  theQuery =
                entityManager.createQuery("from Employee",Employee.class);
        //execute query and return result list
        return theQuery.getResultList();
    }

    @Override
    public Employee findById(int id) {
        return entityManager.find(Employee.class,id);
    }

    @Override
    public void add(Employee e) {
        entityManager.persist(e);
    }

    @Override
    public Employee save(Employee employee) {
        //save employee ,but it may perform the following
        // it performs an insert or update based on the val of id if 0 it performs an insert else update
        Employee dbEmployee= entityManager.merge(employee);
        //get the dbEmployee[latest version form db update] that you added or inserted to know what actually happened
        return dbEmployee ;
    }

    @Override
    public void deleteById(int id) {
        //get that employee in datasource
        Employee employee = entityManager.find(Employee.class,id);
        //remove that employee if it exists in the datasource
        entityManager.remove(employee);
    }
}
