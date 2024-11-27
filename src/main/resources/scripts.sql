
CREATE DATABASE IF NOT EXISTS project;
USE project;

CREATE TABLE course (
                        cid INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description VARCHAR(255)
);

INSERT INTO course (name, description) VALUES
                                           ('Algorithms', 'Description'),
                                           ('Ml', 'Description'),



CREATE TABLE enrollment (
                            enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
                            student_id INT NOT NULL,
                            course_id INT NOT NULL,
                            grade VARCHAR(255),
                            FOREIGN KEY (student_id) REFERENCES student(sid),
                            FOREIGN KEY (course_id) REFERENCES course(cid)
);


CREATE TABLE faculty (
                         fid INT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL
);

INSERT INTO faculty (username, password) VALUES
                                             ('sylesh', 'password123'),
                                             ('phani', 'password456');

CREATE TABLE student (
                         sid INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         username VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL
);

INSERT INTO student (name, email, username, password) VALUES
                                                          ('Pavan', 'pavan@gmail.com', 'null', 'null'),
                                                          ('Irfan', 'irfan@gmail.com', 'null', 'null'),


CREATE TABLE teaching (
                          teaching_id INT AUTO_INCREMENT PRIMARY KEY,
                          faculty_id INT NOT NULL,
                          course_id INT NOT NULL,
                          FOREIGN KEY (faculty_id) REFERENCES faculty(fid),
                          FOREIGN KEY (course_id) REFERENCES course(cid)
);

INSERT INTO teaching (faculty_id, course_id) VALUES
                                                 (1, 1),
                                                 (2, 2);

INSERT INTO enrollment (student_id, course_id, grade) VALUES
                                                          (1, 1, 'A'),
                                                          (2, 1, 'B'),
                                                          (1, 2, 'A');
