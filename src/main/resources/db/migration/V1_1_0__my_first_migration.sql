CREATE TABLE "DB_Crud_Hiberante"."Specialties"
(
    id serial NOT NULL,
    specialty character varying(15),
    status character varying(10),
    PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "DB_Crud_Hiberante"."Specialties"
    OWNER to "Mike";

GRANT ALL ON TABLE "DB_Crud_Hiberante"."Specialties" TO "Mike";

CREATE TABLE "DB_Crud_Hiberante"."Skills"
(
    id serial NOT NULL,
    skill character varying(15),
    status character varying(10),
    PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "DB_Crud_Hiberante"."Skills"
    OWNER to "Mike";

GRANT ALL ON TABLE "DB_Crud_Hiberante"."Skills" TO "Mike";

CREATE TABLE "DB_Crud_Hiberante"."Developers"
(
    id serial NOT NULL,
    first_name character varying(15),
    last_name character varying(15),
    id_specialty integer,
    status character varying(10),
    PRIMARY KEY (id_specialty),
    CONSTRAINT id UNIQUE (id)
            INCLUDE(id),
    CONSTRAINT id_specialty FOREIGN KEY (id_specialty)
        REFERENCES "DB_Crud_Hiberante"."Specialties" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "DB_Crud_Hiberante"."Developers"
    OWNER to "Mike";

GRANT ALL ON TABLE "DB_Crud_Hiberante"."Developers" TO "Mike";

CREATE TABLE "DB_Crud_Hiberante"."developer_skills"
(
    id_developer integer,
    id_skill integer,
    CONSTRAINT id_developer FOREIGN KEY (id_developer)
        REFERENCES "DB_Crud_Hiberante"."Developers" (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
        NOT VALID
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS "DB_Crud_Hiberante"."developer_skills"
    OWNER to "Mike";

GRANT ALL ON TABLE "DB_Crud_Hiberante"."developer_skills" TO "Mike";