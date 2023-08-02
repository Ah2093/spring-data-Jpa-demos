package com.example.springdatajpademo.model;

import lombok.*;
import org.springframework.stereotype.Component;

@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Component
public class EmployeeResponse {
    private Long id ;
    private String firstName ;
    private String lastName ;
    private Department department ;
    private User user ;
}
