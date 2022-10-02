package top.shusheng007.readwritesplit.demo;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import top.shusheng007.readwritesplit.demo.persistence.po.Student;

import java.util.List;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/2 11:40
 * @description:
 */

@RequiredArgsConstructor
@RestController
@RequestMapping("/rw")
public class DemoController {
    private final DemoService demoService;

    @PostMapping("/save")
    public Student saveStudent(@RequestBody StudentReq student){
       return demoService.saveStudent(student);
    }

    @GetMapping("/list")
    public List<Student> lsitStudents(){
       return demoService.listStudents();
    }


}
