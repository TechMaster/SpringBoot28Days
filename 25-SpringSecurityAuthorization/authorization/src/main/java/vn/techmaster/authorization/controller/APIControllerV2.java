package vn.techmaster.authorization.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v2/api")
@PreAuthorize("isAuthenticated()")
public class APIControllerV2 {
  @GetMapping()
  public String demoAuthorizationForController() {
    return "@PreAuthorize có thể áp dụng cho cả 1 controller";
  }
}
