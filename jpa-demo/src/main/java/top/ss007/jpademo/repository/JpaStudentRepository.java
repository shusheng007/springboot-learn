package top.ss007.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import top.ss007.jpademo.entity.Student;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {

}
