INSERT INTO car (brand, model, location, price_per_day, active)
VALUES ('Toyota', 'Corolla', 'Sofia', 50.0, true),
       ('Honda', 'Civic', 'Plovdiv', 60.0, true),
       ('BMW', 'X5', 'Varna', 120.0, true),
       ('Audi', 'A4', 'Burgas', 90.0, true);

INSERT INTO offer (customer_name, model, days_rented, has_accidents, status, total_price)
VALUES ('John Doe', 'Toyota Corolla', 5, false, 'CREATED', 250.0);
