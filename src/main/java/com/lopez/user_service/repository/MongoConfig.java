package com.lopez.user_service.repository;

import org.springframework.data.mongodb.config.AbstractMongoClientConfiguration;

public class MongoConfig extends AbstractMongoClientConfiguration {

  @Override
  protected boolean autoIndexCreation() {
    return true;
  }

  @Override
  protected String getDatabaseName() {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'getDatabaseName'");
  }

}
