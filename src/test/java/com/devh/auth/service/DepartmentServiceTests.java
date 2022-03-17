package com.devh.auth.service;

import com.devh.auth.vo.DepartmentVO;
import com.devh.module.exception.ServiceException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class DepartmentServiceTests {

    @Autowired
    DepartmentService departmentService;

    @Test
    public void test_findTree() {
        try {
            System.out.println(departmentService.findTree());
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
//    @Test
    public void test_save() {
        try {
            DepartmentVO d = DepartmentVO.builder()
                    .name("test_save")
                    .description("test_description")
                    .build();

            departmentService.save(d);
            DepartmentVO v = departmentService.findOneByName("test_save");
            System.out.println(v);
            assertNotNull(v);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

    @Transactional
//    @Test
    public void test_update() {
        try {
            DepartmentVO d = DepartmentVO.builder()
                    .id(20L)
                    .name("test_save55")
                    .description("test_description55")
                    .parentId(1L)
                    .build();
            departmentService.update(d);
            DepartmentVO v = departmentService.findOneByName("test_save55");
            System.out.println(v);
            assertNotNull(v);
        } catch (ServiceException e) {
            System.out.println(e.getMessage());
        }
    }

//    @Test
    public void test_delete() throws ServiceException {
        departmentService.deleteById(12L);
    }
}
