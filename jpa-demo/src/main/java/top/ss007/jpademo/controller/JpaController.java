package top.ss007.jpademo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.ss007.jpademo.service.MyService;

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

    @PostMapping("/student")
    public String addStudent() {
        return myService.addStudent().toString();
    }

}
