package top.ss007.springdoc.web;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Schema(description = "创建程序员入参")
@Setter
@Getter
public class CreateProgrammerRequest {

    @NotNull
    @Schema(description = "名称", example = "王二狗")
    private String name;

    @NotNull
    @Min(18)
    @Max(35)
    @Schema(description = "年龄", example = "35")
    private Integer age;

    @Schema(description = "掌握的编程语言", type = "List", example = "[\"Java\",\"Sql\"]")
    private List<String> programmingLang;
}
