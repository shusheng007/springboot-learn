package top.ss007.springlearn.service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.security.InvalidParameterException;

/**
 * Copyright (C) 2021 ShuSheng007
 * 完全享有此软件的著作权
 *
 * @author ShuSheng007
 * @time 2021/6/13 16:38
 * @description
 */
@ExtendWith(MockitoExtension.class)
class CompanyTest {
    @Mock
    private Programmer programmer;

    @InjectMocks
    private Company company;


    @BeforeEach
    void setUp() {
        System.out.println("每个方法之前执行");
//        company = new Company(new Programmer());
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getTotalStuff_InvalidParam() {
        int tester = -1;
        int programmer = 5;
        Assertions.assertThrows(InvalidParameterException.class, () -> company.getTotalStuff(tester, programmer));
    }

    @DisplayName("获取员工数成功方法")
    @Test
    void getTotalStuff_Success() {
        int tester = 1;
        int programmer = 5;
        Assertions.assertEquals(6, company.getTotalStuff(tester, programmer));
    }


//    @Disabled
    @Test
    void startProject() {
        System.out.println("startProject");
        Mockito.when(programmer.program()).thenReturn("码字如飞");
        company.startProject();
    }
}