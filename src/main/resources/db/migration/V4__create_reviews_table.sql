-- Create reviews table with proper constraints
CREATE TABLE reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    challenge_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    text VARCHAR(1000) NOT NULL,
    rating INT NOT NULL CHECK (rating >= 1 AND rating <= 5),
    helpful_score INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    
    -- Foreign key constraints
    CONSTRAINT fk_reviews_challenge FOREIGN KEY (challenge_id) REFERENCES challenges(id) ON DELETE CASCADE,
    CONSTRAINT fk_reviews_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    
    -- Unique constraint to ensure one review per user per challenge
    CONSTRAINT uk_reviews_user_challenge UNIQUE (user_id, challenge_id)
);

-- Create index for better query performance
CREATE INDEX idx_reviews_challenge_id ON reviews(challenge_id);
CREATE INDEX idx_reviews_user_id ON reviews(user_id);
CREATE INDEX idx_reviews_created_at ON reviews(created_at);
