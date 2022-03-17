package com.devh.auth.service;

import com.devh.auth.vo.AuthorityVO;
import com.devh.module.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthorityServiceTests {

    @Autowired
    AuthorityService authorityService;

//    @Test
    public void test_save() throws ServiceException {
        authorityService.save(AuthorityVO.builder()
                .id(5L)
                .name("test")
                .description("test")
        .build());
    }

//    @Test
    public void test_update() throws ServiceException {
        authorityService.update(AuthorityVO.builder()
                .id(5L)
                .name("test_up")
                .description("test_up")
        .build());
    }

//    @Test
    public void test_delete() throws ServiceException {
        authorityService.deleteById(5L);
    }
}
