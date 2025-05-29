package top.shusheng007.jpademo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.shusheng007.jpademo.entity.Student;
import top.shusheng007.jpademo.web.FilterStudentRequest;

public interface MyService {

    void initDatabase();

    Student addStudent();

    Page<Student> filterStudents(FilterStudentRequest filter, Pageable pageable);
}
