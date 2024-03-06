package top.shusheng007.composite.aop;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AopService {

    @Idempotent(name = "ben")
    public List<Student> getStudents(AopRequest request) {

        return List.of(new Student(request.getName(), request.getAge()));
    }
}
