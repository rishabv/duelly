-- Align reviews table with BaseEntity audit/flag columns expected by JPA
-- Adds creation_date, last_modified_date, is_active, is_deleted

ALTER TABLE reviews
    ADD COLUMN creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    ADD COLUMN last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    ADD COLUMN is_active TINYINT(1) NOT NULL DEFAULT 1,
    ADD COLUMN is_deleted TINYINT(1) NOT NULL DEFAULT 0;

-- Optional: If you want to deprecate old columns, you can drop them in a follow-up migration
-- ALTER TABLE reviews DROP COLUMN created_at, DROP COLUMN updated_at;
