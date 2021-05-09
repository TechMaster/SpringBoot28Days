package vn.techmaster.bank.service;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vn.techmaster.bank.exception.BankErrorCode;
import vn.techmaster.bank.model.AllLog;
import vn.techmaster.bank.repository.AllLogRepo;

@Service
public class LoggingService {
  @Autowired
  private AllLogRepo allLogRepo;

  
  //@Transactional(value = TxType.NOT_SUPPORTED, dontRollbackOn={ BankException.class })
  
  @Transactional(value = TxType.NEVER)  //lưu được all log thành công vì tạo ra 2 transaction context khác nhau
  //@Transactional(value = TxType.REQUIRED) //Nằm trong transaction context của hàm gọi, nên không lưu được mọi log
  //@Transactional(value = TxType.REQUIRED, dontRollbackOn={ BankException.class })  
  //@Transactional(value = TxType.SUPPORTS) //Không ghi được hết log
  //@Transactional(value = TxType.NOT_SUPPORTED) //Cũng ghi được nhiều log thành công
  //@Transactional(value = TxType.NEVER) //Báo lỗi Existing transaction found for transaction marked with propagation 'never'
  public void saveLog(long fromID, long toID, Long amount, BankErrorCode resultCode, String detail) {
    allLogRepo.save(new AllLog(fromID, toID, amount, resultCode, detail));
  }
}
