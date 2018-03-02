package com.spring.test.service;

import com.spring.test.bean.Student;
import com.spring.test.dao.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    Repository repository;

    public List<Student> getAll() {
        return repository.getAll();
    }

    public Object getById(int ... ids) {
        List<Integer> idList = new ArrayList<>();
        for (int id : ids) {
            idList.add(id);
        }
        return repository.getByID(idList);
    }

    public boolean addStudent(String firstName, String lastName) {
        return repository.addStudent(firstName, lastName);
    }
}
