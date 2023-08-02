package com.example.springdatajpademo.services;

import com.example.springdatajpademo.model.Role;
import com.example.springdatajpademo.repositroy.RoleRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RoleService {

    private RoleRepos roleRepos ;
    @Autowired

    public RoleService(RoleRepos roleRepos) {
        this.roleRepos = roleRepos;
    }
    public Role findById(long id )
    {
        return roleRepos.findById(id).orElseThrow();}
    public Role insert (Role role)
    {
        return roleRepos.save(role);

    }
    public List<Role> findAll()
    {
        return roleRepos.findAll();
    }


    public Role update (Role role)
    {
        Role current = roleRepos.findById(role.getId()).get();
        //handle the exception if this Role isn't in the db
        // and insert it
        //else edit it and persist updated
        current.setName(role.getName());
        return roleRepos.save(current);
    }
}
