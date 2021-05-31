package vn.techmaster.simpleauthen.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class SecurityConfig {
  @Bean
  public UserDetailsService userDetailsService() {    
    var tom = User.withUsername("tom@gmail.com").password("123").
    authorities("read").build();
    var bob = User.withUsername("bob@gmail.com").password("123").
    authorities("read").build();
    
    return new InMemoryUserDetailsManager(tom, bob);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return NoOpPasswordEncoder.getInstance(); 
  }
}