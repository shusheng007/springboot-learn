package top.shusheng007.jpademo.service.impl;

import cn.hutool.core.util.StrUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import top.shusheng007.jpademo.entity.Account;
import top.shusheng007.jpademo.entity.School;
import top.shusheng007.jpademo.entity.Student;
import top.shusheng007.jpademo.entity.Teacher;
import top.shusheng007.jpademo.repository.JpaAccountRepository;
import top.shusheng007.jpademo.repository.JpaSchoolRepository;
import top.shusheng007.jpademo.repository.JpaStudentRepository;
import top.shusheng007.jpademo.repository.JpaTeacherRepository;
import top.shusheng007.jpademo.repository.specification.StudentSpecification;
import top.shusheng007.jpademo.service.MyService;
import top.shusheng007.jpademo.web.FilterStudentRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

        Student wangDog2 = new Student();
        wangDog2.setName("WangDog2");
        wangDog2.setAge(36);
        wangDog2.setNumber("STU0001");

        Account account = new Account();
        account.setUserName("ShuSheng007");
        account.setPassword("123456");
        wangDog2.setAccount(account);

        List<Teacher> teachers = new ArrayList<>();
        teachers.add(Teacher.builder().name("KongZi").number("TEA0001").build());
        teachers.add(Teacher.builder().name("MengZi").number("TEA0002").build());
        wangDog2.setTeachers(teachers);


        school.setStudents(List.of(wangDog2));
        schoolRepository.saveAndFlush(school);
    }

    @Override
    public Student addStudent() {
        Student wuXue = new Student();
        wuXue.setName("ShangGuanWuXue");
        wuXue.setNumber("STU0003");

        Account account = new Account();
        account.setUserName("ShangGuanWuXue");
        account.setPassword("666666");
        wuXue.setAccount(account);

        teacherRepository.findByNumber("TEA0001").ifPresent(t -> wuXue.setTeachers(List.of(t)));
        schoolRepository.findByName("BIT").ifPresent(s -> wuXue.setSchool(s));

        return wuXue;
    }

    @Override
    public Page<Student> filterStudents(FilterStudentRequest filter, Pageable pageable) {

        Specification<Student> spec = Specification.where(null);

        if (StrUtil.isNotBlank(filter.getName())) {
            spec = spec.and(StudentSpecification.nameLike(filter.getName()));
        }
        if (!Objects.isNull(filter.getAgeMin()) && !Objects.isNull(filter.getAgeMax())) {
            spec = spec.and(StudentSpecification.ageBetween(filter.getAgeMin(), filter.getAgeMax()));
        }

        if (StrUtil.isNotBlank(filter.getNumber())) {
            spec = spec.and(StudentSpecification.hasNumber(filter.getNumber()));
        }

        if (StrUtil.isNotBlank(filter.getTeacher())) {
            spec = spec.and(StudentSpecification.hasTeacher(filter.getTeacher()));
        }

        return studentRepository.findAll(spec, pageable);
    }
}
