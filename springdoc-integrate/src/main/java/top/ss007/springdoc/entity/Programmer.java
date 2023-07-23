package top.ss007.springdoc.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

//@Schema()
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Programmer {

    @Schema(description = "id", example = "666")
    private Integer id;

    @Schema(description = "姓名", example = "王二狗")
    private String name;

    @Schema(description = "年龄", example = "35")
    private Integer age;

    @Schema(description = "掌握的编程语言", example = "[\"Java\",\"Kotlin\"]")
    private List<String> programmingLang;
}
