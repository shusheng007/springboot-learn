-- "jpa-learn".account definition

-- Drop table

-- DROP TABLE "jpa-learn".account;

CREATE TABLE "jpa-learn".account
(
    id         serial4               NOT NULL,
    user_name  varchar(64) NULL,
    "password" varchar(64) NULL,
    "version"  int8        DEFAULT 0 NOT NULL,
    create_at  timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    update_at  timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    create_by  varchar(64) NULL,
    update_by  varchar(64) NULL,
    CONSTRAINT account_pk PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE "jpa-learn".account OWNER TO postgres;
GRANT
ALL
ON TABLE "jpa-learn".account TO postgres;


-- "jpa-learn".school definition

-- Drop table

-- DROP TABLE "jpa-learn".school;

CREATE TABLE "jpa-learn".school
(
    id        serial4 NOT NULL, -- primary key
    "name"    varchar(256) NULL,
    create_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    update_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT school_pk PRIMARY KEY (id)
);

-- Column comments

COMMENT
ON COLUMN "jpa-learn".school.id IS 'primary key';

-- Permissions

ALTER TABLE "jpa-learn".school OWNER TO postgres;
GRANT
ALL
ON TABLE "jpa-learn".school TO postgres;


-- "jpa-learn".student definition

-- Drop table

-- DROP TABLE "jpa-learn".student;

CREATE TABLE "jpa-learn".student
(
    id         serial4 NOT NULL,
    stu_name   varchar(64) NULL,
    account_id int4 NULL,
    school_id  int4 NULL,
    create_at  timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    update_at  timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    stu_number varchar(64) NULL,
    age        int2        DEFAULT 0 NULL,
    CONSTRAINT student_pk PRIMARY KEY (id),
    CONSTRAINT student_unique UNIQUE (stu_number)
);

-- Permissions

ALTER TABLE "jpa-learn".student OWNER TO postgres;
GRANT
ALL
ON TABLE "jpa-learn".student TO postgres;


-- "jpa-learn".student_teacher_relation definition

-- Drop table

-- DROP TABLE "jpa-learn".student_teacher_relation;

CREATE TABLE "jpa-learn".student_teacher_relation
(
    id         serial4 NOT NULL,
    student_id int4    NOT NULL,
    teacher_id int4    NOT NULL,
    create_at  timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    update_at  timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    CONSTRAINT student_teacher_relation_pk PRIMARY KEY (id)
);

-- Permissions

ALTER TABLE "jpa-learn".student_teacher_relation OWNER TO postgres;
GRANT
ALL
ON TABLE "jpa-learn".student_teacher_relation TO postgres;


-- "jpa-learn".teacher definition

-- Drop table

-- DROP TABLE "jpa-learn".teacher;

CREATE TABLE "jpa-learn".teacher
(
    id        serial4 NOT NULL,
    "name"    varchar(64) NULL,
    create_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    update_at timestamptz DEFAULT CURRENT_TIMESTAMP NULL,
    "number"  varchar(64) NULL,
    CONSTRAINT teacher_pk PRIMARY KEY (id),
    CONSTRAINT teacher_unique UNIQUE (number)
);

-- Permissions

ALTER TABLE "jpa-learn".teacher OWNER TO postgres;
GRANT
ALL
ON TABLE "jpa-learn".teacher TO postgres;