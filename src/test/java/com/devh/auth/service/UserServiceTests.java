package com.devh.auth.service;

import com.devh.auth.vo.UserVO;
import com.devh.module.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserServiceTests {
    @Autowired
    private UserService userService;

//    @Test
    public void test_findByUsername() throws ServiceException {
        UserVO userVO = userService.findByUsername("root");

        System.out.println(userVO);
        assertEquals("root", userVO.getUsername());
    }

//    @Test
    public void test_findAll() throws ServiceException {
        userService.findAll().forEach(System.out::println);
    }

//    @Test
    public void test_save() throws ServiceException {
        UserVO userVO = UserVO.builder()
                .username("root1")
                .password("bK5m/i6cms4hSHSronOsqKhqOUyqxTnjU8txYVbneWcPWVu1hOJV65BIAz6GPIIhNXrS+Q==")
                .name("root1")
                .nickname("root1")
                .build();

        assertTrue(userService.save(userVO));
    }

}
