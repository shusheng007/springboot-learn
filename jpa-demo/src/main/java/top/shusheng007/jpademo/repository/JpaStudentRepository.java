package top.shusheng007.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import top.shusheng007.jpademo.entity.Student;

import java.util.Optional;

@Repository
public interface JpaStudentRepository extends JpaRepository<Student, Integer>, JpaSpecificationExecutor<Student> {
    @Query("select s from Student s where s.number = ?1")
    Optional<Student> findByNumber(String number);

    Optional<Student> findByName(String name);

}
