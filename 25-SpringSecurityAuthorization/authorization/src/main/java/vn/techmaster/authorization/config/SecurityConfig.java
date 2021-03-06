package vn.techmaster.authorization.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = false, jsr250Enabled = false)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  @Bean
  public PasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
     /* .antMatchers("/admin").hasAuthority("ADMIN")
      .antMatchers("/free").hasAnyAuthority("ADMIN", "USER", "AUTHOR", "EDITOR")
      .antMatchers("/author").hasAnyAuthority("AUTHOR")
      .antMatchers("/user").hasAnyAuthority("USER")
      .antMatchers("/editor").hasAnyAuthority("EDITOR")*/
      .antMatchers("/h2-console/**").permitAll().and().csrf().ignoringAntMatchers("/h2-console/**") // https://jessitron.com/2020/06/15/spring-security-for-h2-console/
      .and().headers().frameOptions().sameOrigin().and().formLogin();
  }
}
