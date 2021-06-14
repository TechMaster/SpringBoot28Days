package vn.techmaster.vincinema.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vn.techmaster.vincinema.dto.AuthRequest;
import vn.techmaster.vincinema.dto.UserPOJO;
import vn.techmaster.vincinema.model.User;
import vn.techmaster.vincinema.security.JwtTokenUtil;

@RestController
@RequestMapping(path = "api/public")
public class AuthApi {
    @Autowired private AuthenticationManager authenticationManager;
    @Autowired private JwtTokenUtil jwtTokenUtil;
    
    @GetMapping("login")
    public ResponseEntity<String> testLogin() {
        return ResponseEntity.ok().body("Test ok");
    }

    @PostMapping("login")
    public ResponseEntity<UserPOJO> login(@RequestBody @Valid AuthRequest request) {
        try {
            Authentication authenticate = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

            User user = (User) authenticate.getPrincipal();

            return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
                    .body(new UserPOJO(user.getUsername()));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    /*
     * @PostMapping("register") public UserView register(@RequestBody @Valid
     * CreateUserRequest request) { return userService.create(request); }
     */

}
