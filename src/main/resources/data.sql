--CREATE SEQUENCE students_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    status BIT NOT NULL,
    age INT NOT NULL
);