package vn.techmaster.vincinema.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@EnableWebSecurity
@Slf4j
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired private JwtTokenFilter jwtTokenFilter;

  // Cấu hình password Encoder
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    // Enable CORS and disable CSRF
    http = http.cors().and().csrf().disable();

    // Set session management to stateless
    http = http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and();

    // Set unauthorized requests exception handler
    http = http.exceptionHandling().authenticationEntryPoint((request, response, ex) -> {
      log.error("Unauthorized request - {}", ex.getMessage());
      response.sendError(HttpServletResponse.SC_UNAUTHORIZED, ex.getMessage());
    }).and();

    http = http.authorizeRequests()
        .mvcMatchers("/h2-console/**").permitAll()
        .and().csrf().ignoringAntMatchers("/h2-console/**") //https://jessitron.com/2020/06/15/spring-security-for-h2-console/
        .and().headers().frameOptions().sameOrigin()
        .and();

    // Set permissions on endpoints
    http.authorizeRequests()
        // Our public endpoints
        .antMatchers(HttpMethod.GET, "/api/cinema").permitAll()
        // Our private endpoints
        .anyRequest().authenticated();

    

    // Add JWT token filter
    http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);
  }

  @Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

}
