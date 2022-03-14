package com.devh.auth.repository;

import com.devh.auth.entity.Department;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DepartmentRepositoryTests {
    @Autowired
    DepartmentRepository departmentRepository;

    @Test
    public void test_findAll() {
        List<Department> list = departmentRepository.findAll();
        list.forEach(d -> {
            System.out.println("===========================================");
            System.out.println(d);
            System.out.println("===========================================");
        });
        assertEquals(19, list.size());
    }
}
