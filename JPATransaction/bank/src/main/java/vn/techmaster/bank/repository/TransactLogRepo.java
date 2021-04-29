package vn.techmaster.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.bank.model.TransactLog;

@Repository
public interface TransactLogRepo extends CrudRepository<TransactLog, Long> {
  
}
