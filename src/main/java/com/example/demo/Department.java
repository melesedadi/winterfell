package com.example.demo;

import javax.persistence.*;
import java.util.Set;
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String depname;
    private String directorname;
    @OneToMany(mappedBy = "department",cascade = CascadeType.REMOVE, orphanRemoval = true)
    public Set<Employee> employees;

    public Department() {
    }

    public Department(String depname, String directorname) {
        this.depname = depname;
        this.directorname = directorname;
    }

    public String getDirectorname() {
        return directorname;
    }

    public void setDirectorname(String directorname) {
        this.directorname = directorname;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepname() {
        return depname;
    }

    public void setDepname(String depname) {
        this.depname = depname;
    }



    public Set<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(Set<Employee> employees) {
        this.employees = employees;
    }
}
