-- Create the database
CREATE DATABASE project;

USE project;

CREATE TABLE faculty (
                         fid INT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(50) NOT NULL,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE course (
                        cid INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(100) NOT NULL,
                        description TEXT
);

CREATE TABLE student (
                         sid INT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(50) NOT NULL,
                         password VARCHAR(255) NOT NULL
);

CREATE TABLE enrollment (
                            enrollment_id INT AUTO_INCREMENT PRIMARY KEY,
                            student_id INT,
                            course_id INT,
                            FOREIGN KEY (student_id) REFERENCES student(sid) ON DELETE CASCADE,
                            FOREIGN KEY (course_id) REFERENCES course(cid) ON DELETE CASCADE
);

CREATE TABLE teaching (
                          teaching_id INT AUTO_INCREMENT PRIMARY KEY,
                          faculty_id INT,
                          course_id INT,
                          FOREIGN KEY (faculty_id) REFERENCES faculty(fid) ON DELETE CASCADE,
                          FOREIGN KEY (course_id) REFERENCES course(cid) ON DELETE CASCADE
);

INSERT INTO faculty (username, password) VALUES ('faculty1', 'password1');
INSERT INTO faculty (username, password) VALUES ('faculty2', 'password2');

INSERT INTO course (name, description) VALUES ('Course 1', 'Description for Course 1');
INSERT INTO course (name, description) VALUES ('Course 2', 'Description for Course 2');


INSERT INTO student (username, password) VALUES ('student1', 'password1');
INSERT INTO student (username, password) VALUES ('student2', 'password2');

INSERT INTO teaching (faculty_id, course_id) VALUES (1, 1);
INSERT INTO teaching (faculty_id, course_id) VALUES (1, 2);
INSERT INTO teaching (faculty_id, course_id) VALUES (2, 1);


INSERT INTO enrollment (student_id, course_id) VALUES (1, 1);
INSERT INTO enrollment (student_id, course_id) VALUES (1, 2);
INSERT INTO enrollment (student_id, course_id) VALUES (2, 1);
