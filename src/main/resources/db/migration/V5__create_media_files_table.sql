-- Create media_files table to store uploaded media and associations
CREATE TABLE media_files (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    review_id BIGINT NULL,

    -- File metadata
    file_name VARCHAR(255) NOT NULL,
    url VARCHAR(1024) NOT NULL,
    file_type VARCHAR(100) NOT NULL,
    size_type BIGINT DEFAULT 0,

    -- Audit
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,

    -- Flags
    is_active TINYINT(1) NOT NULL DEFAULT 1,
    is_deleted TINYINT(1) NOT NULL DEFAULT 0,

    -- Foreign keys
    CONSTRAINT fk_media_review FOREIGN KEY (review_id) REFERENCES reviews(id) ON DELETE CASCADE
);

-- Helpful indexes
CREATE INDEX idx_media_review_id ON media_files(review_id);
CREATE INDEX idx_media_creation_date ON media_files(creation_date);