package top.ss007.readwritesplit.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import top.ss007.readwritesplit.anotation.Read;
import top.ss007.readwritesplit.anotation.Write;
import top.ss007.readwritesplit.demo.persistence.mapper.StudentMapper;
import top.ss007.readwritesplit.demo.persistence.po.Student;

import java.util.List;

/**
 * Created by shusheng007
 *
 * @author benwang
 * @date 2022/10/2 11:22
 * @description:
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class DemoService {

    private final StudentMapper studentMapper;

    @Write
    public Student saveStudent(StudentReq param){
        Student student = new Student();
        student.setName(param.getName());
        student.setAge(param.getAge());
        student.setCreateTime(param.getCreateTime());
        studentMapper.insert(student);
        try {
            log.info("插入学生:{}",new ObjectMapper().writeValueAsString(student));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return student;
    }

    @Read
    public List<Student> listStudents(){
        List<Student> list = studentMapper.list();
        try {
            log.info("获取学生列表:{}",new ObjectMapper().writeValueAsString(list));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return list;
    }
}
