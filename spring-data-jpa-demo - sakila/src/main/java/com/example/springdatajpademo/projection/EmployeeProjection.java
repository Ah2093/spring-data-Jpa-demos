package com.example.springdatajpademo.projection;

import org.springframework.beans.factory.annotation.Value;

public interface EmployeeProjection {
    //create method in the projection interface with the same name of property that I want to return
    //closed interface projection
    long getId ();
    String getFirstName();
    String getLastName();

    //open interface projection

    @Value("#{target.firstName+' '+target.lastName}")
    String getFullName();
}
