CREATE TABLE invoices (
    invoice_id INT AUTO_INCREMENT PRIMARY KEY,
    session_name VARCHAR(100) NOT NULL,
    customer_name VARCHAR(255) NOT NULL,
    selected_seats VARCHAR(255),
    products TEXT,
    total_price DECIMAL(10, 2) NOT NULL,
    purchase_date DATETIME DEFAULT CURRENT_TIMESTAMP
);
