package top.ss007.springdoc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.ss007.springdoc.api.Result;
import top.ss007.springdoc.entity.Programmer;
import top.ss007.springdoc.service.ProgrammerService;
import top.ss007.springdoc.web.CreateProgrammerRequest;

import java.util.List;
import java.util.stream.Collectors;


//@SecurityScheme(name = "petstore_auth", type = SecuritySchemeType.OAUTH2,
//        flows = @OAuthFlows(implicit = @OAuthFlow(authorizationUrl = "https://petstore3.swagger.io/oauth/authorize",
//                scopes = {
//                        @OAuthScope(name = "write:pets", description = "modify pets in your account"),
//                        @OAuthScope(name = "read:pets", description = "read your pets")}
//        )))

@Tag(name = "程序员", description = "程序员乐园")
@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/programmer",produces =  "application/json")
public class ProgrammerController {

    private final ProgrammerService service;

    @Operation(summary = "创建程序员", description = "用于创建一个闷骚的程序员")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = Programmer.class))}),
            @ApiResponse(responseCode = "405", description = "非法输入",
                    content = @Content)
    })
    @PostMapping()
    public Programmer createProgrammer(@RequestBody CreateProgrammerRequest request) {
        return new Programmer(666, "王二狗", request.getAge(), request.getProgrammingLang());
    }

    @Parameters(value = {
            @Parameter(name = "name", description = "姓名", in = ParameterIn.PATH),
            @Parameter(name = "age", description = "年龄", in = ParameterIn.QUERY)
    })
    @GetMapping("/query/{name}")
    public List<Programmer> getProgrammers(@PathVariable("name") String name, @RequestParam("age") Integer age) {
        return service.getProgrammers().stream()
                .filter(p -> p.getName().equals(name) && p.getAge().equals(age))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Result<Programmer> getProgrammer(@Parameter(description = "程序员id") @PathVariable Integer id) {
        return Result.ok(service.getProgrammers().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null));
    }


}
