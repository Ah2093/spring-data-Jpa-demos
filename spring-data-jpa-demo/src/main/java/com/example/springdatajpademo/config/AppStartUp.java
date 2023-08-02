package com.example.springdatajpademo.config;

import com.example.springdatajpademo.model.Role;
import com.example.springdatajpademo.model.User;
import com.example.springdatajpademo.services.RoleService;
import com.example.springdatajpademo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppStartUp implements CommandLineRunner {
    private UserService userService ;
    private RoleService roleService;
    @Autowired
    public AppStartUp(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        if (userService.findAll().isEmpty()) {
            // create roles to use in users
            Role role1 = new Role();
            role1.setName("Admin");
            Role role2 = new Role();
            role2.setName("User");

            roleService.insert(role1);
            roleService.insert(role2);

            Set<Role> adminRole = new HashSet<>();
            adminRole.add(role1);

            Set<Role> userRole = new HashSet<>();
            userRole.add(role2);
            //create users
            User user1 = new User();
            user1.setUserName("Admin");
            user1.setPassword("123");
            user1.setRoles(adminRole);
            userService.insert(user1);

            User user2 = new User();
            user2.setUserName("User");
            user2.setPassword("123");
            user2.setRoles(userRole);
            userService.insert(user2);
        }
    }
}
