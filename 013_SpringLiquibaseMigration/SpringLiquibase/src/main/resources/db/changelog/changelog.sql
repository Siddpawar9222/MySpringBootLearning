-- sql formatted liquibase

-- changeset siddhesh:002
ALTER TABLE spring_liquibase_student ADD COLUMN is_dance BOOLEAN DEFAULT TRUE;