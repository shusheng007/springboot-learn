package top.shusheng007.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.shusheng007.jpademo.entity.Teacher;

import java.util.Optional;

@Repository
public interface JpaTeacherRepository extends JpaRepository<Teacher, Integer> {
    Optional<Teacher> findByNumber(String number);

}
