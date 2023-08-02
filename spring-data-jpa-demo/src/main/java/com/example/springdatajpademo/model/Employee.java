package com.example.springdatajpademo.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.proxy.HibernateProxy;

import java.util.Objects;

//always stay with jpa so if you want to change the implementation it's ok
@Getter
@Setter
@ToString
@Builder
@Entity
@NamedQuery(name = "Employee.findBySalary",query = "select emp from Employee emp where emp.salary >= :salary")
@Table(name = "hr_employee")
@AllArgsConstructor
public class Employee {
    public Employee() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(
            name = "id"
    )
    Long id ;
    @Column(
            name = "name",
            nullable = false
    )
    private String name ;
    @Column(
            name = "salary"
    )
    private Double salary;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(
            name = "dep_id"
    )
    private Department department ;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user ;
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;
        Class<?> oEffectiveClass = o instanceof HibernateProxy ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass() : o.getClass();
        Class<?> thisEffectiveClass = this instanceof HibernateProxy ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass() : this.getClass();
        if (thisEffectiveClass != oEffectiveClass) return false;
        Employee employee = (Employee) o;
        return getId() != null && Objects.equals(getId(), employee.getId());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }
}
