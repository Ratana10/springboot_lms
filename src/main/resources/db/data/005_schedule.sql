create table schedules(
    sch_id  bigserial   primary KEY,
    day varchar(10),
    start_time TIME,
    end_time TIME,
    cou_id  bigint
        constraint fk_schedule_course
        references courses
);

INSERT INTO schedules(
    day,
    start_time,
    end_time,
    cou_id
)
VALUES
    ('MONDAY', '18:00:00', '20:00:00', 1),
    ('THURSDAY', '18:00:00', '20:00:00', 1),
    ('MONDAY', '18:00:00', '20:00:00', 3),
    ('TUESDAY', '10:00:00', '12:00:00', 3)
