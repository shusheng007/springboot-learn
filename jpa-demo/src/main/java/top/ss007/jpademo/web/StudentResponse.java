package top.ss007.jpademo.web;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class StudentResponse {

    private Integer id;
    private String name;
    private Integer age;
    private String number;
    private AccountResponse account;
    private SchoolResponse school;
    private List<TeacherResponse> teachers;

}
