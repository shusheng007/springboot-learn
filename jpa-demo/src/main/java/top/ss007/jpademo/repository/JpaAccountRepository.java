package top.ss007.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.ss007.jpademo.entity.Account;
import top.ss007.jpademo.entity.School;

@Repository
public interface JpaAccountRepository extends JpaRepository<Account, Integer> {
}
