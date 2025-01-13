CREATE TABLE sessions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    session_name VARCHAR(100) NOT NULL,
    session_date DATE NOT NULL,
    session_time TIME NOT NULL,
    hall_name VARCHAR(50) NOT NULL,
    total_capacity INT NOT NULL,
    available_capacity INT NOT NULL,
    status VARCHAR(20) DEFAULT 'Scheduled',
    played BOOLEAN DEFAULT FALSE,
    ticket_price DECIMAL(10, 2) NOT NULL,
    movie_duration INT NOT NULL
);
