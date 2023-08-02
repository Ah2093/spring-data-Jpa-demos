package com.example.springdatajpademo.repositroy;

import com.example.springdatajpademo.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepos extends JpaRepository<Department, Long> {
}