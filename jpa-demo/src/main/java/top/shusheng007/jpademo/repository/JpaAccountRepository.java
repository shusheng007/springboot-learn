package top.shusheng007.jpademo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import top.shusheng007.jpademo.entity.Account;

@Repository
public interface JpaAccountRepository extends JpaRepository<Account, Integer> {
}
