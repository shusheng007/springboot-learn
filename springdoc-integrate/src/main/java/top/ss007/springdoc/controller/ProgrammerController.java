package top.ss007.springdoc.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
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
@RequestMapping("/api/programmer")
public class ProgrammerController {

    private final ProgrammerService service;

    @Operation(summary = "创建程序员", description = "用于创建一个闷骚的程序员")
    @PostMapping()
    public Programmer createProgrammer(@RequestBody CreateProgrammerRequest request) {
        return new Programmer(666, "王二狗", request.getAge(), request.getProgrammingLang());
    }

    @GetMapping("/{name}")
    public List<Programmer> getProgrammers(@PathVariable("name") String name, @RequestParam("age") Integer age) {
        return service.getProgrammers().stream()
                .filter(p -> p.getName().equals(name) && p.getAge().equals(age))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public Programmer getProgrammer(@PathVariable Integer id) {
        return service.getProgrammers().stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


}
