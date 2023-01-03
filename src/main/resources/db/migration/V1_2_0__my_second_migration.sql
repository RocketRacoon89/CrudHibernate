ALTER TABLE IF EXISTS "DB_Crud_Hiberante"."Developers" DROP CONSTRAINT IF EXISTS "Developers_pkey";

ALTER TABLE IF EXISTS "DB_Crud_Hiberante"."Developers"
    ADD CONSTRAINT "Developers_pk" PRIMARY KEY (id);