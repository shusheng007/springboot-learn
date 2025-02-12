package top.ss007.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.ss007.jpademo.entity.School;

import java.util.Optional;

@Repository
public interface JpaSchoolRepository extends JpaRepository<School, Integer> {
    Optional<School> findByName(String name);
}
