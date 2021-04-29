package vn.techmaster.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import vn.techmaster.bank.model.AllLog;

@Repository
public interface AllLogRepo extends CrudRepository<AllLog, Long>{
  
}
