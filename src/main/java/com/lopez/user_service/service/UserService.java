package com.lopez.user_service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.lopez.user_service.dto.LinkToCompany;
import com.lopez.user_service.dto.UserResponse;
import com.lopez.user_service.model.User;
import com.lopez.user_service.repository.UserRepository;

@Service
public class UserService {
  private UserRepository userRepository;

  Logger logger = LoggerFactory.getLogger(UserService.class);

  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public ResponseEntity<Object> createUser(User user) {
    try {
      return new ResponseEntity<>(userRepository.save(user), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Object> fetchUsers() {
    List<User> users = userRepository.findAll();
    if (users.isEmpty()) {
      return new ResponseEntity<>("No users found!", HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity<>(users, HttpStatus.OK);
  }

  public ResponseEntity<Object> fetchUserById(String id) {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      UserResponse userResponse = new UserResponse(
          user.get().getId(),
          user.get().getUserName(),
          user.get().getIdNumber(),
          user.get().getDateOfBirth(),
          user.get().getGender(),
          user.get().getOccupation(),
          user.get().getRiskLevel());
      return new ResponseEntity<>(userResponse, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("No user Id found", HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Object> fetchUserByUsername(String userName) {
    Optional<User> user = userRepository.findByUserName(userName);
    if (user.isPresent()) {
      UserResponse userResponse = new UserResponse(
          user.get().getId(),
          user.get().getUserName(),
          user.get().getIdNumber(),
          user.get().getDateOfBirth(),
          user.get().getGender(),
          user.get().getOccupation(),
          user.get().getRiskLevel());
      return new ResponseEntity<>(userResponse, HttpStatus.OK);
    } else {
      return new ResponseEntity<>("No user Name found", HttpStatus.NOT_FOUND);
    }
  }

  public ResponseEntity<Object> linkToCompany(LinkToCompany updateData) {
    try {
      logger.warn("REsponses: {}", updateData);
      HashMap<String, String> response = new HashMap<>();
      userRepository.findByIdAndUpdateCompanyId(updateData.getUserId(), updateData.getCompanyId());
      response.put("message", "USER_LINKED");
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  public ResponseEntity<Object> deleteAll() {
    try {
      HashMap<String, String> response = new HashMap<>();
      userRepository.deleteAll();
      response.put("message", "All users have been deleted");
      return new ResponseEntity<>(response, HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

}