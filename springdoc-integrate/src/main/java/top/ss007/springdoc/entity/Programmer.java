package top.ss007.springdoc.entity;

import lombok.*;

import java.util.List;

//@Schema()
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Programmer {

    private Integer id;

    private String name;

    //    @Schema(description = "年龄", example = "35")
    private Integer age;

    //    @Schema(description = "掌握的编程语言")
    private List<String> programmingLang;
}
