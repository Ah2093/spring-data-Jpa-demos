package com.example.springdatajpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hr_department")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;
    @Column(name = "dep_name")
    private String name ;
    //to make the relationship bidirectional by passing  it property name of other side of relation
    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<Employee> employees ;
}
