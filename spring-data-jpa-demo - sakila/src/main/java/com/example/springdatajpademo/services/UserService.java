package com.example.springdatajpademo.services;

import com.example.springdatajpademo.model.Role;
import com.example.springdatajpademo.model.User;
import com.example.springdatajpademo.repositroy.UserRepos;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {

    private final UserRepos userRepos ;
    private final RoleService roleService;

    public UserService(UserRepos userRepos, RoleService roleService) {
        this.userRepos = userRepos;
        this.roleService = roleService;
    }

    @Autowired


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

    //the following example is an example of using @Transactional annotation in service layer
    // as service talk to another service, and it's a repository
    // it will rap all logic in method into one transaction commit or discard all
    // if i don't put it for each save will start transaction and save and commit it
    // and if for any reason it can't perform save it will rollback this save only
    // and others will be saved and this will break the logic
    @Transactional
    public void addRoleForAllUsers(String roleName)
    {
        //start transaction
        Role role = roleService.findByName(roleName);
        this.findAll().forEach(user ->{
            user.addRole(role);
            userRepos.save(user);
        }
        );
        //commit
        
        //end trasaction
    }

}
