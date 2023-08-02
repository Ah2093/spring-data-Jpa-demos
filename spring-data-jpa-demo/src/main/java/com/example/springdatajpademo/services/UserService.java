package com.example.springdatajpademo.services;

import com.example.springdatajpademo.model.User;
import com.example.springdatajpademo.repositroy.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private UserRepos userRepos ;
    @Autowired

    public UserService(UserRepos userRepos) {
        this.userRepos = userRepos;
    }
    public User findById(long id )
    {
        return userRepos.findById(id).orElseThrow();}
    public User insert (User user)
    {
        return userRepos.save(user);

    }
    public List<User> findAll()
    {
        return userRepos.findAll();
    }


    public User update (User user)
    {
        User current = userRepos.findById(user.getId()).get();
        //handle the exception if this user isn't in the db
        // and insert it
        //else edit it and persist updated
        current.setUserName(user.getUserName());
        current.setPassword(user.getPassword());
        return userRepos.save(current);
    }

}
