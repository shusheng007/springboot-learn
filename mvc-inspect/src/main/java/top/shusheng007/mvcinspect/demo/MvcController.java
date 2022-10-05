package top.shusheng007.mvcinspect.demo;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/2 21:39
 * @description:
 */
@RestController
@RequestMapping("/mvc")
public class MvcController {

    @GetMapping("/obj")
    public ResponseEntity<MyResponse> getObj(@RequestParam("name") String name){
        MyResponse response = new MyResponse();
        response.setName(name);
        response.setAge(18);

        ResponseEntity<MyResponse> entity = new ResponseEntity<>(response, HttpStatus.OK);
        return entity;
    }

    @GetMapping("/str")
    public String helloMvc(){
        return "hello mvc";
    }
}
