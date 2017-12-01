DROP SCHEMA IF EXISTS dropwizard;

CREATE SCHEMA dropwizard;

USE dropwizard;

CREATE TABLE example (

  ID         BIGINT       NOT NULL PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255) NOT NULL

);