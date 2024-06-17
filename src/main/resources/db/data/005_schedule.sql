INSERT INTO schedules(
    day,
    start_time,
    end_time,
    cou_id,
    created_at,
    updated_at,
    created_by,
    updated_by,
    deleted
)
VALUES
    ('MONDAY', '18:00:00', '20:00:00', 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('THURSDAY', '18:00:00', '20:00:00', 1, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('MONDAY', '18:00:00', '20:00:00', 3, CURRENT_TIMESTAMP, null, 1, null, FALSE),
    ('TUESDAY', '10:00:00', '12:00:00', 3, CURRENT_TIMESTAMP, null, 1, null, FALSE)
