-- Sample data to initialize schema
INSERT INTO customer(customer_name, email, phone_number, address, password, role)
VALUES ('Admin',
        'staff@hotelhub1.com',
        '0000000000',
        'HQ',
        '$2a$10$gQQ2lnLp7qpnFiLAe8oBDupCOYwsj.ptQylgBhUkzafwiKKK2njFO',
        'ROLE_STAFF');

INSERT INTO room_type(type_name, description)
VALUES ('Single','Single bed'),('Double','Double bed');

INSERT INTO room(room_number, status, price_per_night, room_type_id)
VALUES ('101','AVAILABLE',100.0,1),('102','AVAILABLE',150.0,2);

INSERT INTO package(package_name, food_included, number_of_days, max_people, facilities_included, package_price)
VALUES ('Standard', true, 2, 2, 'WiFi, Breakfast', 200.0);
