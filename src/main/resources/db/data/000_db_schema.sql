create table categories
(
    cat_id  bigserial   primary KEY,
    cat_name varchar(30) NOT NULL,
    cat_description varchar(30) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table teachers
(
    tea_id  bigserial   primary KEY,
    tea_code varchar(7) NULL,
    tea_firstname varchar(10),
    tea_lastname varchar(20),
    gender varchar(20),
    salary DECIMAL(10, 2),
    hire_date DATE,
    stop_work BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table students
(
    stu_id  bigserial   primary KEY,
    stu_firstname varchar(10),
    stu_lastname varchar(20),
    stu_gender varchar(20),
    stu_phone   varchar(11) NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table courses
(
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
    references teachers,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);

create table schedules
(
    sch_id  bigserial   primary KEY,
    day varchar(10),
    start_time TIME,
    end_time TIME,
    cou_id  bigint
    constraint fk_schedule_course
    references courses,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    created_by BIGINT NULL,
    updated_by BIGINT,
    deleted BOOLEAN DEFAULT FALSE
);
