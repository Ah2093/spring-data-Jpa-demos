package com.example.springdatajpademo.repositroy;

import com.example.springdatajpademo.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepos extends JpaRepository<Role, Long> {
}