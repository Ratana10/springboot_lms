create table teachers(
    tea_id  bigserial   primary KEY,
    tea_firstname varchar(10),
    tea_lastname varchar(20),
    gender varchar(20),
    salary DECIMAL(10, 2),
    hire_date DATE,
    stop_work BOOLEAN NOT NULL DEFAULT false
);

INSERT INTO teachers(
    tea_firstname,
    tea_lastname,
    gender,
    salary,
    hire_date,
    stop_work
)
VALUES
    ('Tola', 'Nu', 'MALE', 100.00, '2024-06-01', false),
    ('Seyha', 'Nu', 'FEMALE', 100.00, '2024-06-01', false),
    ('Kanha', 'Nu', 'FEMALE', 100.00, '2024-06-01', false),
    ('Somnang', 'Nu', 'MALE', 100.00, '2024-06-01', false)
