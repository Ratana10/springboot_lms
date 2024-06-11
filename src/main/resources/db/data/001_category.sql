create table categories(
    cat_id  bigserial   primary KEY,
    cat_name varchar(30)
);

INSERT INTO categories(
    cat_name
)
VALUES
    ('Front End'),
    ('Back End'),
    ('Framework'),
    ('Algorithm')