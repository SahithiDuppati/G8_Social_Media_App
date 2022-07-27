package app.socialmedia.controller;

import app.socialmedia.model.LoginRequest;
import app.socialmedia.model.Response;
import app.socialmedia.model.User;
import app.socialmedia.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class AuthController {

  @Autowired AuthService authService;

  @PostMapping(value = "/signup", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Response> signup( @Valid @RequestBody User user) {
    try {
        Response signupResponse = authService.signupUser(user);
      if (signupResponse.isStatus()) {
        return new ResponseEntity<Response>(signupResponse, HttpStatus.OK);
      } else {
        return new ResponseEntity<Response>(signupResponse, HttpStatus.BAD_REQUEST);
      }

    } catch (Exception e) {
     Response signupResponse = null;
      return new ResponseEntity<Response>(signupResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @PostMapping(value = "/login", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Response> login( @Valid @RequestBody LoginRequest loginRequest) {
    try {
      Response loginResponse = authService.loginUser(loginRequest);
      if (loginResponse.isStatus()) {
        return new ResponseEntity<Response>(loginResponse, HttpStatus.OK);
      } else {
        return new ResponseEntity<Response>(loginResponse, HttpStatus.BAD_REQUEST);
      }

    } catch (Exception e) {
      Response loginResponse = null;
      return new ResponseEntity<Response>(loginResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
