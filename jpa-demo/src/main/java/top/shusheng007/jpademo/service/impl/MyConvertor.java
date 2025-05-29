package top.shusheng007.jpademo.service.impl;

import lombok.experimental.UtilityClass;
import top.shusheng007.jpademo.entity.Account;
import top.shusheng007.jpademo.entity.School;
import top.shusheng007.jpademo.entity.Student;
import top.shusheng007.jpademo.entity.Teacher;
import top.shusheng007.jpademo.web.AccountResponse;
import top.shusheng007.jpademo.web.SchoolResponse;
import top.shusheng007.jpademo.web.StudentResponse;
import top.shusheng007.jpademo.web.TeacherResponse;

import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class MyConvertor {


    public static StudentResponse toStudentResponse(Student student) {
        StudentResponse studentResponse = new StudentResponse();
        studentResponse.setId(student.getId());
        studentResponse.setName(student.getName());
        studentResponse.setAge(student.getAge());
        studentResponse.setNumber(student.getNumber());
        studentResponse.setAccount(toAccountResponse(student.getAccount()));
        studentResponse.setSchool(toSchoolResponse(student.getSchool()));
        studentResponse.setTeachers(toTeacherResponses(student.getTeachers()));
        return studentResponse;
    }

    public static AccountResponse toAccountResponse(Account account) {
        AccountResponse accountResponse = new AccountResponse();
        accountResponse.setId(account.getId());
        accountResponse.setUserName(account.getUserName());
        return accountResponse;
    }

    public static List<TeacherResponse> toTeacherResponses(List<Teacher> teachers) {
        return teachers.stream().map(t -> toTeacherResponse(t)).collect(Collectors.toList());
    }

    public static TeacherResponse toTeacherResponse(Teacher teacher) {
        TeacherResponse teacherResponse = new TeacherResponse();
        teacherResponse.setId(teacher.getId());
        teacherResponse.setName(teacher.getName());
        teacherResponse.setNumber(teacher.getNumber());
        return teacherResponse;
    }

    public static SchoolResponse toSchoolResponse(School school) {
        SchoolResponse schoolResponse = new SchoolResponse();
        schoolResponse.setId(school.getId());
        schoolResponse.setName(school.getName());
        return schoolResponse;
    }


}
