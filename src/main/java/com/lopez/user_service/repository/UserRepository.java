package com.lopez.user_service.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.Update;

import com.lopez.user_service.model.User;

public interface UserRepository extends MongoRepository<User, String> {

  public Optional<User> findByUserName(String userName);

  @Query("{ 'id' : ?0 }")
  @Update("{ '$set' : {'companyId': ?1} }")
  Long findByIdAndUpdateCompanyId(String userId, String companyId);

}