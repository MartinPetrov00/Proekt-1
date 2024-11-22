CREATE TABLE car (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    make VARCHAR(255),
    model VARCHAR(255),
    price_per_day DOUBLE,
    location VARCHAR(255),
    active BOOLEAN DEFAULT TRUE
);

CREATE TABLE offer (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    phone VARCHAR(255),
    address VARCHAR(255),
    has_accidents BOOLEAN,
    car_id BIGINT,
    days_rented INT,
    status VARCHAR(255),
    FOREIGN KEY (car_id) REFERENCES car(id)
);
