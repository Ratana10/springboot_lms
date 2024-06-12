create table courses(
    cou_id  bigserial   primary KEY,
    cou_name varchar(20),
    cou_description varchar(255),
    cou_price decimal(10, 2),
    cou_image  varchar(255) NULL,
    cat_id  bigint
        constraint fk_course_category
        references categories,
    tea_id  bigint
        constraint fk_course_teacher
            references teachers
);

INSERT INTO courses(
    cou_name,
    cou_description,
    cou_price,
    cou_image,
    cat_id,
    tea_id
)
VALUES
    ('NextJs', 'null', 50.00, 'null', 1, 1),
    ('Agular', 'null', 100.00, 'null', 1, 1),
    ('Spring Boot', 'null', 12.00, 'null', 3, 1),
    ('Solving Problem', 'null', 25.00, 'null', 4, 1)
