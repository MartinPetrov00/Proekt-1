CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    brand VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    price_per_day DOUBLE NOT NULL,
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE offer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255) NOT NULL,
    model VARCHAR(255) NOT NULL,
    days_rented INT NOT NULL,
    has_accidents BOOLEAN DEFAULT FALSE,
    status VARCHAR(50) NOT NULL,
    total_price DOUBLE
);
