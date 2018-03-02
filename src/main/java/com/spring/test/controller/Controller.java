package com.spring.test.controller;

import com.spring.test.bean.Student;
import com.spring.test.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.util.List;


@RequestMapping("/cont")
@RestController
public class Controller {


    @Qualifier("dataSource")
    @Autowired
    DataSource dataSource;

    @Autowired
    StudentService service;

    @RequestMapping("/")
    public String index() {
        return "Hello";
    }

    @GetMapping("/getAll")
    public List<Student> getAll() {
        return service.getAll();
    }

    @GetMapping("/getById")
    public Object getById(@RequestParam(value = "ids") int ... ids) {
        return service.getById(ids);
    }

    @RequestMapping("/addStudent")
    public boolean addStudent(String firstName, String lastName) {
        return service.addStudent(firstName, lastName);
    }




}
