package com.example.springdatajpademo.services;

import com.example.springdatajpademo.model.Department;
import com.example.springdatajpademo.repositroy.DepartmentRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentService {
    private DepartmentRepos departmentRepos ;

    @Autowired
    public DepartmentService(DepartmentRepos departmentRepos) {
        this.departmentRepos = departmentRepos;
    }

    public Department findById(long id )
    {
        return departmentRepos.findById(id).orElseThrow();}
    public Department insert (Department department)
    {
        return departmentRepos.save(department);

    }
    public List<Department> findAll()
    {
        return departmentRepos.findAll();
    }

    public Department update (Department department)
    {
        Department current = departmentRepos.findById(department.getId()).get();
        //handle the exception if this department isn't in the db
        // and insert it
        //else edit it and persist updated
        current.setName(department.getName());
        return departmentRepos.save(current);

    }
    
}