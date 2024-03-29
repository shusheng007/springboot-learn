package top.ss007.jpademo.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.ss007.jpademo.entity.Account;
import top.ss007.jpademo.entity.School;
import top.ss007.jpademo.entity.Student;
import top.ss007.jpademo.entity.Teacher;
import top.ss007.jpademo.repository.JpaAccountRepository;
import top.ss007.jpademo.repository.JpaSchoolRepository;
import top.ss007.jpademo.repository.JpaStudentRepository;
import top.ss007.jpademo.repository.JpaTeacherRepository;
import top.ss007.jpademo.service.MyService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class MyServiceImpl implements MyService {

    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private JpaSchoolRepository schoolRepository;

    @Autowired
    private JpaAccountRepository accountRepository;

    @Autowired
    private JpaTeacherRepository teacherRepository;


    @Override
    public void initDatabase() {

        School school = new School();
        school.setName("BIT");

        Student student = new Student();
        student.setName("WangDog2");

        Account account = new Account();
        account.setUserName("ShuSheng007");
        account.setPassword("123456");
        student.setAccount(account);

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(Teacher.builder().name("KongZi").name("TEA0001").build());
        teachers.add(Teacher.builder().name("MengZi").name("TEA0002").build());
        student.setTeachers(teachers);

        school.setStudents(Arrays.asList(student));
        schoolRepository.saveAndFlush(school);
    }

    @Override
    public Student addStudent() {
        Student student = new Student();
        student.setName("CuiHuaNiu");
        student.setNumber("STU0002");

        Account account = new Account();
        account.setUserName("CuiHuaNiu");
        account.setPassword("123456");
        student.setAccount(account);

        Optional<Teacher> kongZiOpt = teacherRepository.findByNumber("TEA0002");
        student.setTeachers(List.of(kongZiOpt.orElseThrow()));

        studentRepository.saveAndFlush(student);

        return student;
    }
}
