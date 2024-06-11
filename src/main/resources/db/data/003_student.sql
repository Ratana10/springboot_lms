create table students(
    stu_id  bigserial   primary KEY,
    stu_firstname varchar(10),
    stu_lastname varchar(20),
    stu_gender varchar(20),
    stu_phone   varchar(11) NULL
);

INSERT INTO students(
    stu_firstname,
    stu_lastname,
    stu_gender,
    stu_phone
)
VALUES
    ('student-1', 'stu', 'MALE', '123456'),
    ('student-2', 'stu', 'FEMALE', '123457'),
    ('student-3', 'stu', 'FEMALE', '123458'),
    ('student-4', 'stu', 'MALE', '123459')
