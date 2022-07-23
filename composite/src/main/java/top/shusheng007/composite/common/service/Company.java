package top.shusheng007.composite.common.service;

import java.security.InvalidParameterException;

/**
 * Copyright (C) 2021 ShuSheng007
 * 完全享有此软件的著作权
 *
 * @author ShuSheng007
 * @time 2021/6/13 16:29
 * @description
 */
public class Company {
    private Programmer programmer;

    public Company(Programmer programmer) {
        this.programmer = programmer;
    }

    public int getTotalStuff(int testers, int programmers) {
        if (testers < 0 || programmers < 0) {
            throw new InvalidParameterException("不可为负数");
        }
        return testers + programmers;
    }

    public void startProject() {
        System.out.println("开始项目");
        System.out.println(programmer.program());
    }
}
