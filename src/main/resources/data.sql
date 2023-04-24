--CREATE SEQUENCE students_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE students (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    firstname VARCHAR(255) NOT NULL,
    lastname VARCHAR(255) NOT NULL,
    status BIT NOT NULL,
    age INT NOT NULL
);

--INSERT INTO students (id, firstname, lastname, status, age)
--VALUES (NEXT VALUE FOR students_seq, 'John', 'Doe', 1, 30);
--
--INSERT INTO students (id, firstname, lastname, status, age)
--VALUES (NEXT VALUE FOR students_seq, 'Jane', 'Smith', 1, 25);
--
--INSERT INTO students (id, firstname, lastname, status, age)
--VALUES (NEXT VALUE FOR students_seq, 'José', 'Lopez', 1, 30);
--
--INSERT INTO students (id, firstname, lastname, status, age)
--VALUES (NEXT VALUE FOR students_seq, 'Diana', 'Díaz', 1, 25);
--
--INSERT INTO students (id, firstname, lastname, status, age)
--VALUES (NEXT VALUE FOR students_seq, 'Carlos', 'Quispe', 1, 30);
--
--INSERT INTO students (id, firstname, lastname, status, age)
--VALUES (NEXT VALUE FOR students_seq, 'Eduardo', 'Gónzales', 1, 25);