package top.ss007.jpademo.web;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class FilterStudentRequest {
    private String name;
    private Integer ageMin;
    private Integer ageMax;
    private String number;
    private String teacher;
}
