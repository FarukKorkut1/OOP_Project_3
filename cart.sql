CREATE TABLE cart (
    cart_id INT AUTO_INCREMENT PRIMARY KEY,
    session_name VARCHAR(255) NOT NULL, 
    customer_name VARCHAR(255) NOT NULL,
    selected_seats TEXT,
    products TEXT, 
    total_price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
