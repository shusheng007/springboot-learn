package top.ss007.jpademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import top.ss007.jpademo.service.MyService;
import top.ss007.jpademo.service.impl.MyConvertor;
import top.ss007.jpademo.web.FilterStudentRequest;
import top.ss007.jpademo.web.StudentResponse;

import java.util.List;

@RestController
@RequestMapping("/jpa")
public class JpaController {

    @Autowired
    private MyService myService;

    @PostMapping("/gen-db")
    public String initDb() {
        myService.initDatabase();
        return "ok";
    }

    @PostMapping("/students")
    public StudentResponse addStudent() {

        return MyConvertor.toStudentResponse(myService.addStudent());
    }


    @PostMapping("students/filter")
    public List<StudentResponse> studentFilter(@RequestBody FilterStudentRequest request,
                                               @RequestParam Integer pageNumber, @RequestParam Integer pageSize) {

        return myService.filterStudents(request, PageRequest.of(pageNumber, pageSize))
                .stream().map(s -> MyConvertor.toStudentResponse(s)).toList();
    }

}
