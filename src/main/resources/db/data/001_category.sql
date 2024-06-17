INSERT INTO categories(
                       cat_name,
                       created_at,
                       updated_at,
                       created_by,
                       updated_by,
                       deleted
)
VALUES
    ('Front End', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Back End', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Framework', CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('Algorithm', CURRENT_TIMESTAMP, null, 1, null, FALSE)