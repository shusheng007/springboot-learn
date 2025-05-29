package top.shusheng007.springdoc.service;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;
import top.shusheng007.springdoc.entity.Programmer;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProgrammerService {
    private List<Programmer> programmers = new ArrayList<>();

    @PostConstruct
    public void init() {
        Programmer p1 = new Programmer(1, "王二狗", 35, List.of("Java", "Dart"));
        Programmer p2 = new Programmer(2, "牛翠华", 25, List.of("JavaScript", "Html"));
        programmers.add(p1);
        programmers.add(p2);
    }

    public List<Programmer> getProgrammers() {
        return programmers;
    }

}
