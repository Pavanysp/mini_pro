INSERT INTO course (name, description) VALUES
                                           ('Algorithms', 'Description'),
                                           ('Ml', 'Description');


INSERT INTO faculty (username, password) VALUES
                                             ('sylesh', 'password123'),
                                             ('phani', 'password456');

INSERT INTO student (name, email, username, password) VALUES
                                                          ('Pavan', 'pavan@gmail.com', 'null', 'null'),
                                                          ('Irfan', 'irfan@gmail.com', 'null', 'null');
INSERT INTO teaching (faculty_id, course_id) VALUES
                                                 (1, 1),
                                                 (2, 2);

INSERT INTO enrollment (student_id, course_id, grade) VALUES
                                                          (1, 1, 'A'),
                                                          (2, 1, 'B'),
                                                          (1, 2, 'A');
