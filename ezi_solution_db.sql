DROP DATABASE IF EXISTS ezi_solution_db;
CREATE DATABASE ezi_solution_db;
USE ezi_solution_db;

CREATE TABLE Users (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(55) NOT NULL,
                       username VARCHAR(55) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL,
                       remember_token VARCHAR(255),
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Roles (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(55) NOT NULL UNIQUE,
                       code varchar(55) NOT NULL,
                       priority INT NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE RoleUserMapping (
                                 role_id BIGINT NOT NULL,
                                 user_id BIGINT NOT NULL,
                                 FOREIGN KEY (role_id) REFERENCES Users(id),
                                 FOREIGN KEY (user_id) REFERENCES Roles(id)
);

CREATE TABLE Banks (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       name VARCHAR(255) NOT NULL UNIQUE,
                       code VARCHAR(100) NOT NULL,
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Provinces (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(100) NOT NULL UNIQUE,
                           code VARCHAR(100) NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Districts (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           province_id BIGINT NOT NULL,
                           name VARCHAR(100) NOT NULL UNIQUE,
                           code VARCHAR(100) NOT NULL,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (province_id) REFERENCES Provinces(id)
);

CREATE TABLE Wards (
                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                       province_id bigint,
                       district_id bigint,
                       name VARCHAR(100),
                       code VARCHAR(100),
                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                       FOREIGN KEY (province_id) REFERENCES Provinces(id),
                       FOREIGN KEY (district_id) REFERENCES Districts(id)
);

CREATE TABLE Customers (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(100),
                           year_of_birth INT,
                           month_of_birth INT,
                           day_of_birth INT,
                           dob DATE,
                           address TEXT,
                           province_id BIGINT,
                           district_id BIGINT,
                           ward_id BIGINT,
                           created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                           updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                           FOREIGN KEY (province_id) REFERENCES Provinces(id),
                           FOREIGN KEY (district_id) REFERENCES Districts(id),
                           FOREIGN KEY (ward_id) REFERENCES Wards(id)

);


CREATE TABLE PhoneNumbers (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              phone VARCHAR(10) NOT NULL,
                              has_zalo boolean DEFAULT 0,
                              customer_id BIGINT,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

CREATE TABLE Emails (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        email VARCHAR(100),
                        customer_id BIGINT,
                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                        FOREIGN KEY (customer_id) REFERENCES Customers(id)
);



CREATE TABLE CustomerFbUsers (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                 customer_id BIGINT,
                                 ref_id varchar(255),
                                 expired_at DATETIME,
                                 created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                 updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                 FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

CREATE TABLE CustomerGoogleUsers (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     customer_id BIGINT,
                                     ref_id varchar(255),
                                     expired_at DATETIME,
                                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                     FOREIGN KEY (customer_id) REFERENCES Customers(id)
);


CREATE TABLE BankAccounts (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              bank_number VARCHAR(55) NOT NULL,
                              host_bank VARCHAR(255) NOT NULL,
                              code VARCHAR(100),
                              balance DOUBLE DEFAULT 0,
                              bank_id BIGINT NOT NULL,
                              customer_id BIGINT,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (bank_id) REFERENCES Banks(id),
                              FOREIGN KEY (customer_id) REFERENCES Customers(id)
);

CREATE TABLE CustomerProperties (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    name VARCHAR(100) NOT NULl,
                                    type INT NOT NULL,
                                    has_required BOOLEAN DEFAULT 0,
                                    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE CustomerInfo (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              customer_id bigint NOT NULL,
                              property_id bigint NOT NULL,
                              value TEXT,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE Projects (
                          id BIGINT PRIMARY KEY AUTO_INCREMENT,
                          user_id BIGINT,
                          name VARCHAR(255) NOT NULL,
                          code VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                          updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                          FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE ProjectTypes (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              name varchar(55),
                              code varchar(55),
                              unit varchar(55),
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);


CREATE TABLE ProjectSellingSettings (
                                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                        project_id BIGINT NOT NULL,
                                        type_id BIGINT NOT NULL,
                                        image VARCHAR(255) NOT NULL,
                                        selling_status TINYINT DEFAULT 0,
                                        priority INT,
                                        lock_duration INT,
                                        created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                        updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                        FOREIGN KEY (project_id) REFERENCES Projects(id),
                                        FOREIGN KEY (type_id) REFERENCES ProjectTypes(id)
);


CREATE TABLE ProjectBanners (
                                id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                project_id BIGINT,
                                url VARCHAR(255),
                                alt VARCHAR(100),
                                priority INT,
                                created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                FOREIGN KEY (project_id) REFERENCES Projects(id)
);

CREATE TABLE ProjectInvestors (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  project_id BIGINT,
                                  name VARCHAR(255),
                                  description TEXT,
                                  created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                  FOREIGN KEY (project_id) REFERENCES Projects(id)
);


CREATE TABLE ProjectPayees (
                               id BIGINT PRIMARY KEY AUTO_INCREMENT,
                               project_id BIGINT,
                               qr_img TEXT,
                               bank_number VARCHAR(55) NOT NULL,
                               bank_name VARCHAR(255) NOT NULL,
                               host_bank VARCHAR(255) NOT NULL,
                               created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                               updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               FOREIGN KEY (project_id) REFERENCES Projects(id)
);

CREATE TABLE ProjectAreas (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              name VARCHAR(255),
                              code VARCHAR(255),
                              priority INT,
                              project_id BIGINT NOT NULL,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (project_id) REFERENCES Projects(id)
);

CREATE TABLE ProjectSlots (
                              id BIGINT PRIMARY KEY AUTO_INCREMENT,
                              project_id BIGINT NOT NULL,
                              area_id BIGINT NOT NULL,
                              name VARCHAR(100) NOT NULL,
                              image VARCHAR(255),
                              code VARCHAR(100),
                              priority INT,
                              status TINYINT DEFAULT 0,
                              created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                              updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                              FOREIGN KEY (project_id) REFERENCES Projects(id),
                              FOREIGN KEY (area_id) REFERENCES ProjectAreas(id)
);

CREATE TABLE ProjectSlotProperties (
                                       id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                       slot_id BIGINT,
                                       name VARCHAR(255),
                                       value TEXT,
                                       priority INT,
                                       created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                       updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                                       FOREIGN KEY (slot_id) REFERENCES ProjectSlots(id)
);

CREATE TABLE SlotImages (
                            id BIGINT PRIMARY KEY AUTO_INCREMENT,
                            url VARCHAR(255) NOT NULL,
                            description VARCHAR(255),
                            image_id VARCHAR(255),
                            slot_id BIGINT,
                            FOREIGN KEY (slot_id) REFERENCES ProjectSlots(id)
);

CREATE TABLE ProjectTransactions (
                                     id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                     project_id BIGINT,
                                     slot_id BIGINT,
                                     customer_id BIGINT,
                                     code VARCHAR(30) UNIQUE,
                                     value TEXT,
                                     status TINYINT DEFAULT 0,
                                     FOREIGN KEY (project_id) REFERENCES Projects(id),
                                     FOREIGN KEY (slot_id) REFERENCES ProjectSlots(id),
                                     FOREIGN KEY (customer_id) REFERENCES Customers(id),
                                     created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                     updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
