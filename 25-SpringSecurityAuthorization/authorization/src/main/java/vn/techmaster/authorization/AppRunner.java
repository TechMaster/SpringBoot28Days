package vn.techmaster.authorization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import vn.techmaster.authorization.service.ISecurityService;

@Component
public class AppRunner implements CommandLineRunner {
  @Autowired
  private ISecurityService securityService;
  
  @Override
  public void run(String... args) throws Exception {
    securityService.generateUsersRoles();
  }
}
