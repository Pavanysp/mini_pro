
CREATE DATABASE IF NOT EXISTS project;
USE project;

CREATE TABLE course (
                        cid INT AUTO_INCREMENT PRIMARY KEY,
                        name VARCHAR(255) NOT NULL,
                        description VARCHAR(255)
);




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



CREATE TABLE student (
                         sid INT AUTO_INCREMENT PRIMARY KEY,
                         name VARCHAR(50) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         username VARCHAR(255) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL
);




CREATE TABLE teaching (
                          teaching_id INT AUTO_INCREMENT PRIMARY KEY,
                          faculty_id INT NOT NULL,
                          course_id INT NOT NULL,
                          FOREIGN KEY (faculty_id) REFERENCES faculty(fid),
                          FOREIGN KEY (course_id) REFERENCES course(cid)
);

