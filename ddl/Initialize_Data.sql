CREATE TABLE CUSTOMER_ORDERS
(
  ID              BIGINT NOT NULL AUTO_INCREMENT, 
  TIME_ORDER_PLACED TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  CUSTOMER_ID           BIGINT NOT NULL,               
  RECIPE_ID           BIGINT NOT NULL,                
  TIME_ORDER_UPDATED  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,     
  ORDER_STATUS           VARCHAR(50),                
  PRIMARY KEY     (id)                                  
);

CREATE TABLE RECIPES
(
  ID              BIGINT NOT NULL AUTO_INCREMENT,        
  RECIPE_ID           BIGINT NOT NULL,                          
  RECIPE_NAME            VARCHAR(50),      
  INGREDIANT           VARCHAR(100),      
  MEASUREMENT           VARCHAR(20),                
  PRIMARY KEY     (id)                                  
);

CREATE TABLE CUSTOMERS
(
  ID              BIGINT NOT NULL AUTO_INCREMENT,                       
  CUSTOMER_NAME   VARCHAR(250),             
  PRIMARY KEY     (id)                                  
);