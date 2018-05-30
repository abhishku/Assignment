package com.assignment.demo;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {
    List<Account> findByaccountId(Long accountId);
}