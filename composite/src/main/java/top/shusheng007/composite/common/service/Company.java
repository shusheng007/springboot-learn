package top.shusheng007.composite.common.service;

import java.security.InvalidParameterException;


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
