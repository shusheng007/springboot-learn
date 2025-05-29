package top.shusheng007.springdoc.web;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Schema(description = "创建程序员请求实体")
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
