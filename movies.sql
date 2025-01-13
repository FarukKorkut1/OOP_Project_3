CREATE TABLE movies (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(100),
    summary TEXT,
    poster_path VARCHAR(255),
    hall_a BOOLEAN DEFAULT FALSE,
    hall_b BOOLEAN DEFAULT FALSE
);

