-- Insert a staff user
INSERT INTO customer(customer_name, email, phone_number, address, password, role)
VALUES (
           'Admin',
           'staff@hotelhub1.com',
           '0000000000',
           'HQ',
           '$2a$10$gQQ2lnLp7qpnFiLAe8oBDupCOYwsj.ptQylgBhUkzafwiKKK2njFO', -- BCrypt hashed password
           'ROLE_STAFF'
       );

-- Insert room types with price_per_night
INSERT INTO room_type(type_name, description, price_per_night)
VALUES
    ('Single', 'Single bed', 80.00),
    ('Double', 'Double bed', 120.00);

-- Insert rooms
INSERT INTO room(room_number, status, price_per_night, room_type_id)
VALUES
    ('101', 'AVAILABLE', 100.00, 1),
    ('102', 'AVAILABLE', 150.00, 2);

-- Insert packages
INSERT INTO package(package_name, food_included, number_of_days, max_people, facilities_included, package_price)
VALUES
    ('Standard', true, 2, 2, 'WiFi, Breakfast', 200.00);
