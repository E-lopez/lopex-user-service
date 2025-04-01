package com.lopez.user_service.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lopez.user_service.dto.LinkToCompany;
import com.lopez.user_service.dto.UpdateUserRisk;
import com.lopez.user_service.dto.UserResponse;
import com.lopez.user_service.model.User;
import com.lopez.user_service.service.UserService;

import org.springframework.http.HttpStatus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/user")
public class UserController {
  private UserService userService;

  Logger logger = LoggerFactory.getLogger(UserController.class);

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<Object> fetchUsers() {
    return userService.fetchUsers();
  }

  @GetMapping("/{id}")
  public ResponseEntity<Object> fetchUserById(@PathVariable String id) {
    return userService.fetchUserById(id);
  }

  @GetMapping("/by-name")
  public ResponseEntity<Object> fetchUserByUsername(@RequestParam String name) {
    return userService.fetchUserByUsername(name);
  }

  @PostMapping
  public ResponseEntity<Object> createUser(@RequestBody UserResponse user) {
    User persistentUser = new User();
    persistentUser.setUserName(user.getUserName());
    persistentUser.setIdNumber(user.getIdNumber());
    persistentUser.setDateOfBirth(user.getDateOfBirth());
    persistentUser.setGender(user.getGender());
    persistentUser.setOccupation(user.getOccupation());
    persistentUser.setRiskLevel(null);
    return userService.createUser(persistentUser);
  }

  @PostMapping("/risk")
  public ResponseEntity<Object> updateUserRisk(@RequestBody UpdateUserRisk payload) {
    UpdateUserRisk updateRisk = new UpdateUserRisk();
    updateRisk.setUserId(payload.getUserId());
    updateRisk.setRiskLevel(payload.getRiskLevel());
    return userService.updateUserRisk(updateRisk);
  }

  @DeleteMapping
  public ResponseEntity<Object> clearUsers() {
    return userService.deleteAll();
  }
}