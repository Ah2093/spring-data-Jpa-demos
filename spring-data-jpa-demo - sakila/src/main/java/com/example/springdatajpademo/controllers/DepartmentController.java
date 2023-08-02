package com.example.springdatajpademo.controllers;

import com.example.springdatajpademo.model.Department;
import com.example.springdatajpademo.services.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private  DepartmentService departmentService;
    @Autowired
    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }
    @GetMapping()
    public List<Department> AllDep()
    {
        return departmentService.findAll();
    }

    @GetMapping("/{id}")
    public Department find(@PathVariable long id)
    {
        return departmentService.findById(id);
    }
    @PostMapping("")
    public Department insert (@RequestBody  Department department)
    {
        return departmentService.insert(department);
    }
    @PutMapping("")
    public Department update (@RequestBody Department department)
    {
        return departmentService.update(department);
    }
}
