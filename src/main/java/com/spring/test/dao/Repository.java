package com.spring.test.dao;

import com.spring.test.bean.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Repository
public class Repository {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public List<Student> getAll() {

        return jdbcTemplate.query("SELECT * FROM customer",
                                    (rs, rowNum) -> new Student(rs.getInt("id"),
                                                                rs.getString("firstName"),
                                                                rs.getString("lastName"))
        );
    }

    public Object getByID(List<Integer> ids) {

        SqlParameterSource params = new MapSqlParameterSource("ids", ids);
        String query = "SELECT * FROM customer WHERE id IN (:ids)";

        return jdbcTemplate.query(query, params, (rs, rowNum) -> new Student(rs.getInt("id"),
                                                                rs.getString("firstName"),
                                                                rs.getString("lastName")));
    }

    public boolean addStudent(String firstName, String lastName) {

        Map<String, String> params = new HashMap<>();
        params.put("firstName", firstName);
        params.put("lastName", lastName);

        int i = jdbcTemplate.update("INSERT INTO customer(firstName, lastName) VALUES (:firstName, :lastName)", params);
        return i > 0;
    }
}
