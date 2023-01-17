package top.ss007.jpademo.service;

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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class MyService {

    @Autowired
    private JpaStudentRepository studentRepository;

    @Autowired
    private JpaSchoolRepository schoolRepository;

    @Autowired
    private JpaAccountRepository accountRepository;


    public void insertStudent(){
        Student student = new Student();
        student.setName("王二狗");

        Account account = new Account();
        account.setUserName("ShuSheng007");
        account.setPassword("123456");
        student.setAccount(account);

        School school = new School();
        school.setName("北京理工");
        student.setSchool(school);

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(Teacher.builder().name("孔夫子").build());
        teachers.add(Teacher.builder().name("祖冲之").build());
        student.setTeachers(teachers);

        studentRepository.saveAndFlush(student);
    }

    public void updateStudent(){
        Student student = studentRepository.findById(8).get();

        Integer id = student.getAccount().getId();
        Account account = new Account();
        account.setId(id);
        account.setUserName("user666");
        student.setAccount(account);

        Student result = studentRepository.saveAndFlush(student);

        log.info("student:{}",result);
    }

    public void getStudent(){
        Student student = studentRepository.findById(8).get();
        log.info("================================================");

        log.info("student:{}",student);
    }

    public void insertAccount(){
        Account account = new Account();
        account.setUserName("牛翠华");
        account.setPassword("654321");

        Account result = accountRepository.save(account);
    }

    public void updateAccount(){
        Account account = accountRepository.findById(2).get();
        account.setPassword("I-LOVE-YOU");

        Account result = accountRepository.save(account);
    }

    public void insertSchool(){

        School school = new School();
        school.setName("应县一中");

        school.setStudents(Arrays.asList(new Student(),new Student()));
        schoolRepository.saveAndFlush(school);
    }




}
