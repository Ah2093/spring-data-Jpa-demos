package com.example.springdatajpademo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id ;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "password")
    private String password ;

    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Employee employee ;
    @ManyToMany
    @JsonIgnore
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )

    private Set<Role>roles =new HashSet<>();
}
