-- liquibase formatted sql

-- changeset Danya:1750521776669-1
CREATE SEQUENCE IF NOT EXISTS class_type_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-2
CREATE SEQUENCE IF NOT EXISTS course_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-3
CREATE SEQUENCE IF NOT EXISTS group_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-4
CREATE SEQUENCE IF NOT EXISTS role_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-5
CREATE SEQUENCE IF NOT EXISTS schedule_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-6
CREATE SEQUENCE IF NOT EXISTS student_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-7
CREATE SEQUENCE IF NOT EXISTS teacher_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-8
CREATE SEQUENCE IF NOT EXISTS user_id_seq START WITH 1 INCREMENT BY 1;

-- changeset Danya:1750521776669-9
CREATE TABLE class_types
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_class_types PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-10
CREATE TABLE courses
(
    id            BIGINT NOT NULL,
    name          VARCHAR(255),
    lecture_count INTEGER,
    seminar_count INTEGER,
    lab_count     INTEGER,
    CONSTRAINT pk_courses PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-11
CREATE TABLE groups
(
    id                 BIGINT NOT NULL,
    name               VARCHAR(255),
    year_created       INTEGER,
    count_students     INTEGER,
    max_count_students INTEGER,
    CONSTRAINT pk_groups PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-12
CREATE TABLE roles
(
    id   BIGINT NOT NULL,
    name VARCHAR(255),
    CONSTRAINT pk_roles PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-13
CREATE TABLE schedule
(
    id            BIGINT NOT NULL,
    date          date,
    start_time    time WITHOUT TIME ZONE,
    end_time      time WITHOUT TIME ZONE,
    group_id      BIGINT,
    course_id     BIGINT,
    teacher_id    BIGINT,
    class_type_id BIGINT,
    CONSTRAINT pk_schedule PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-14
CREATE TABLE students
(
    id          BIGINT NOT NULL,
    last_name   VARCHAR(255),
    name        VARCHAR(255),
    middle_name VARCHAR(255),
    email       VARCHAR(255),
    phone       VARCHAR(255),
    group_id    BIGINT,
    CONSTRAINT pk_students PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-15
CREATE TABLE teachers
(
    id          BIGINT NOT NULL,
    last_name   VARCHAR(255),
    name        VARCHAR(255),
    middle_name VARCHAR(255),
    email       VARCHAR(255),
    phone       VARCHAR(255),
    CONSTRAINT pk_teachers PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-16
CREATE TABLE users
(
    id         BIGINT NOT NULL,
    username   VARCHAR(255),
    password   VARCHAR(255),
    role_id    BIGINT,
    student_id BIGINT,
    teacher_id BIGINT,
    CONSTRAINT pk_users PRIMARY KEY (id)
);

-- changeset Danya:1750521776669-17
ALTER TABLE users
    ADD CONSTRAINT uc_users_student UNIQUE (student_id);

-- changeset Danya:1750521776669-18
ALTER TABLE users
    ADD CONSTRAINT uc_users_teacher UNIQUE (teacher_id);

-- changeset Danya:1750521776669-19
ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_CLASS_TYPE FOREIGN KEY (class_type_id) REFERENCES class_types (id);

-- changeset Danya:1750521776669-20
ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_COURSE FOREIGN KEY (course_id) REFERENCES courses (id);

-- changeset Danya:1750521776669-21
ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

-- changeset Danya:1750521776669-22
ALTER TABLE schedule
    ADD CONSTRAINT FK_SCHEDULE_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teachers (id);

-- changeset Danya:1750521776669-23
ALTER TABLE students
    ADD CONSTRAINT FK_STUDENTS_ON_GROUP FOREIGN KEY (group_id) REFERENCES groups (id);

-- changeset Danya:1750521776669-24
ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_ROLE FOREIGN KEY (role_id) REFERENCES roles (id);

-- changeset Danya:1750521776669-25
ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_STUDENT FOREIGN KEY (student_id) REFERENCES students (id);

-- changeset Danya:1750521776669-26
ALTER TABLE users
    ADD CONSTRAINT FK_USERS_ON_TEACHER FOREIGN KEY (teacher_id) REFERENCES teachers (id);

