package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Component
public class DataLoader implements CommandLineRunner {
    @Autowired
    DepartmentRepository departmentRepository ;

    EmployeeRepository employeeRepository;

    @Override

    public void run(String... strings) throws Exception {
        Department department;

        department= new Department("Department1",  "Director1");
        departmentRepository.save(department);

        department= new Department("Department2",  "Director2");
        departmentRepository.save(department);
        department= new Department("Department3",  "Director3");
        departmentRepository.save(department);



    }


}
