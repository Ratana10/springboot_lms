INSERT INTO courses(
    cou_name,
    cou_description,
    cou_price,
    cou_image,
    cat_id,
    tea_id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    deleted
)
VALUES
    ('NextJs', 'null', 50.00, 'null', 1, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Agular', 'null', 100.00, 'null', 1, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Spring Boot', 'null', 12.00, 'null', 3, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Solving Problem', 'null', 25.00, 'null', 4, 1, CURRENT_TIMESTAMP, null, 1, null, FALSE)
