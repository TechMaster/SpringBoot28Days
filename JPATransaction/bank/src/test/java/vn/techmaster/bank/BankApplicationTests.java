package vn.techmaster.bank;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import vn.techmaster.bank.exception.BankException;
import vn.techmaster.bank.service.BankService;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class BankApplicationTests {
	@Autowired
  private BankService bankService;

	@Test
	@DisplayName("Not enough balance to transfers")
	void notEnoughBalanceTest() {
		try {
			bankService.transfer(1L, 2L, 10000L);
		} catch (BankException e) {
			assertThat(e.getMessage()).isEqualTo("Not enought balance");
		}
		
		
	}

}
