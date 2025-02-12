package top.ss007.jpademo.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import top.ss007.jpademo.entity.Student;
import top.ss007.jpademo.web.FilterStudentRequest;

public interface MyService {

    void initDatabase();

    Student addStudent();

    Page<Student> filterStudents(FilterStudentRequest filter, Pageable pageable);
}
