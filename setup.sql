-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2019-09-16 21:21:09.558

-- tables
-- Table: attention_point
CREATE TABLE attention_point (
    code varchar(5)  NOT NULL,
    service_start timestamp  NOT NULL,
    service_end timestamp  NOT NULL,
    queue_name varchar(20)  NOT NULL,
    employee_username varchar(20)  NOT NULL,
    CONSTRAINT attention_point_pk PRIMARY KEY (code)
);

-- Table: employee
CREATE TABLE employee (
    username varchar(20)  NOT NULL,
    password varchar(20)  NOT NULL,
    name varchar(50)  NOT NULL,
    email varchar(100)  NOT NULL,
    is_admin boolean  NOT NULL,
    CONSTRAINT employee_pk PRIMARY KEY (username)
);

-- Table: queue
CREATE TABLE queue (
    name varchar(20)  NOT NULL,
    CONSTRAINT queue_pk PRIMARY KEY (name)
);

-- Table: turn
CREATE TABLE turn (
    code varchar(5)  NOT NULL,
    requested_date_time timestamp  NOT NULL,
    attended_date_time timestamp,
    queue_name varchar(20)  NOT NULL,
    attention_point_code varchar(5),
    CONSTRAINT turn_pk PRIMARY KEY (code)
);

-- foreign keys
-- Reference: attention_point_employee (table: attention_point)
ALTER TABLE attention_point ADD CONSTRAINT attention_point_employee
    FOREIGN KEY (employee_username)
    REFERENCES employee (username)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: attention_point_queue (table: attention_point)
ALTER TABLE attention_point ADD CONSTRAINT attention_point_queue
    FOREIGN KEY (queue_name)
    REFERENCES queue (name)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: turn_attention_point (table: turn)
ALTER TABLE turn ADD CONSTRAINT turn_attention_point
    FOREIGN KEY (attention_point_code)
    REFERENCES attention_point (code)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- Reference: turn_queue (table: turn)
ALTER TABLE turn ADD CONSTRAINT turn_queue
    FOREIGN KEY (queue_name)
    REFERENCES queue (name)  
    NOT DEFERRABLE 
    INITIALLY IMMEDIATE
;

-- End of file.

