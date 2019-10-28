package com.example.demo;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Set;
@Entity
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    /*@NotNull
    @Size(min=3)*/
    private String depname;
    /*@NotNull
    @Size(min=3)*/
    private String headname;
    @OneToMany(mappedBy = "department",cascade ={CascadeType.PERSIST,CascadeType.DETACH ,CascadeType.MERGE},fetch = FetchType.LAZY)
    public Set<Employee> employees;

    public Department() {
    }

    public Department(String depname, String headname) {
        this.depname = depname;
        this.headname = headname;
    }

    public String getHeadname() {
        return headname;
    }

    public void setHeadname(String headname) {
        this.headname = headname;
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

    /*public void add (Employee employees){
        if(employees==null){
            employees= new ArrayList<Employee>() ;

        }

    }*/
    /*
    public void add (Employee employee){
        if(employees==null){employees=new ArrayList<>()}
        employees.add(employee);
        employee.getDepartment(this);
    }*/
}
