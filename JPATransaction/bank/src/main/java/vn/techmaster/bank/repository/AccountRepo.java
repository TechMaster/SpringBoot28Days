package vn.techmaster.bank.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.bank.model.Account;

@Repository
public interface AccountRepo extends JpaRepository<Account, Long> {
  
}
