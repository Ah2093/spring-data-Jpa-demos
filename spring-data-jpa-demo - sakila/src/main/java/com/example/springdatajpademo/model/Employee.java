package com.example.springdatajpademo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

//always stay with jpa so if you want to change the implementation it's ok
@Getter
@Setter
@ToString
@Entity
@NamedQuery(name = "Employee.findByLastName",query = "select emp from Employee emp where emp.lastName like :lastName")
@Table(name = "actor")
@AllArgsConstructor
public class Employee {
    public Employee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "actor_id"
    )
    private Long id ;
    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName ;
    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName ;
    @ManyToOne
    @JoinColumn(
            name = "dep_id"
    )
    private Department department ;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;

    public Employee(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
