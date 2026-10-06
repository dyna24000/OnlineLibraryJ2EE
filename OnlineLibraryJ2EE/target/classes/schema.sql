CREATE DATABASE IF NOT EXISTS online_library;
USE online_library;

CREATE TABLE IF NOT EXISTS users (
    user_id INT PRIMARY KEY AUTO_INCREMENT,
    email VARCHAR(120) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);

CREATE TABLE IF NOT EXISTS categories (
    category_id INT PRIMARY KEY AUTO_INCREMENT,
    category_name VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS books (
    book_id INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    author VARCHAR(150) NOT NULL,
    quantity INT NOT NULL DEFAULT 0,
    category_id INT,
    CONSTRAINT fk_book_category FOREIGN KEY (category_id)
        REFERENCES categories(category_id) ON DELETE SET NULL
);

CREATE TABLE IF NOT EXISTS borrow_requests (
    request_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    book_id INT NOT NULL,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    request_date DATE NOT NULL,
    due_date DATE NULL,
    CONSTRAINT fk_request_user FOREIGN KEY (user_id) REFERENCES users(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_request_book FOREIGN KEY (book_id) REFERENCES books(book_id) ON DELETE CASCADE
);

INSERT IGNORE INTO categories(category_id, category_name) VALUES
(1,'Programming'),(2,'Database'),(3,'Web Development'),(4,'Computer Science');

INSERT IGNORE INTO users(user_id,email,password,role) VALUES
(1,'admin@library.com','240be518fabd2724ddb6f04eeb1da5967448d7e831c08c8fa822809f74c720a9','ADMIN'),
(2,'student@library.com','703b0a3d6ad75b649a28adde7d83c6251da457549263bc7ff45ec709b0a8448b','STUDENT');

INSERT IGNORE INTO books(book_id,title,author,quantity,category_id) VALUES
(1,'Java Programming','Herbert Schildt',5,1),
(2,'Database System Concepts','Silberschatz',3,2),
(3,'Head First HTML and CSS','Eric Freeman',4,3),
(4,'Computer Science: An Overview','J. Glenn Brookshear',2,4);
