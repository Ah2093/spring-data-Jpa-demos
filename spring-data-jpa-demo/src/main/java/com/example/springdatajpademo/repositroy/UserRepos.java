package com.example.springdatajpademo.repositroy;

import com.example.springdatajpademo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepos extends JpaRepository<User, Long> {
}