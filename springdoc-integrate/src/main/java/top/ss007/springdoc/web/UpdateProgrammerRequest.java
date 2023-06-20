package top.ss007.springdoc.web;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class UpdateProgrammerRequest {

    private Integer age;

    private List<String> programmingLang;
}
