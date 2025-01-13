CREATE TABLE products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL,
    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
);


INSERT INTO products (product_id, product_name, price, stock, created_at)
VALUES
(1, 'Coke', 3.00, 300, '2025-01-13 11:21:42'),
(2, 'Pepsi', 3.50, 120, '2025-01-13 11:21:42'),
(3, 'Water', 1.00, 100, '2025-01-13 11:21:42'),
(4, 'Oreo', 3.00, 49, '2025-01-13 11:21:42'),
(5, 'Digestive', 2.00, 50, '2025-01-13 11:21:42'),
(6, 'Bourbon', 2.50, 50, '2025-01-13 11:21:42'),
(7, 'Car Toy', 5.00, 29, '2025-01-13 11:21:42'),
(8, 'Doll', 7.00, 30, '2025-01-13 11:21:42'),
(9, 'Puzzle', 4.00, 30, '2025-01-13 11:21:42');
