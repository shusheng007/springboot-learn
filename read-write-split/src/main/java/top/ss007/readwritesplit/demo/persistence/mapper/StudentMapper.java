package top.ss007.readwritesplit.demo.persistence.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import top.ss007.readwritesplit.demo.persistence.po.Student;

import java.util.List;

/**
* @author benwang
* @description 针对表【student】的数据库操作Mapper
* @createDate 2022-10-02 11:19:50
* @Entity top.ss007.springlearn.readwritesplit.persistence.po.Student
*/

@Mapper
public interface StudentMapper {

    Integer insert(@Param("student") Student student);

    List<Student> list();
}




