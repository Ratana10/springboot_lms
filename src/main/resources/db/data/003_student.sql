INSERT INTO students(
    stu_firstname,
    stu_lastname,
    stu_gender,
    stu_phone,
    created_at,
    updated_at,
    created_by,
    updated_by,
    deleted
)
VALUES
    ('student-1', 'stu', 'MALE', '123456', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('student-2', 'stu', 'FEMALE', '123457', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('student-3', 'stu', 'FEMALE', '123458', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('student-4', 'stu', 'MALE', '123459', CURRENT_TIMESTAMP, null, 1, null, FALSE)
