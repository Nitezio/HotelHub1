-- =====================
-- 1) STAFF ACCOUNTS
-- =====================
INSERT INTO staff (name, email, password)
VALUES
    ('Admin', 'staff@hotelhub1.com',  '$2a$10$gQQ2lnLp7qpnFiLAe8oBDupCOYwsj.ptQylgBhUkzafwiKKK2njFO'),
    ('Aminah', 'aminah.staff@hotelhub1.com', '$2a$10$gQQ2lnLp7qpnFiLAe8oBDupCOYwsj.ptQylgBhUkzafwiKKK2njFO');

-- =====================
-- 2) CUSTOMER ACCOUNTS
-- =====================
INSERT INTO customer (
    customer_name,
    email,
    phone_number,
    address,
    password,
    role
)
VALUES
    ('Zack',  'zakariaali0408@gmail.com', '0000000000',   'HQ',             '$2a$10$gQQ2lnLp7qpnFiLAe8oBDupCOYwsj.ptQylgBhUkzafwiKKK2njFO', 'ROLE_CUSTOMER'),
    ('Sarah', 'sarah.lim@gmail.com',       '0123456789',   'Kuala Lumpur',   '$2a$10$gQQ2lnLp7qpnFiLAe8oBDupCOYwsj.ptQylgBhUkzafwiKKK2njFO', 'ROLE_CUSTOMER'),
    ('Farid', 'farid.nasir@yahoo.com',     '0198765432',   'Johor Bahru',    '$2a$10$gQQ2lnLp7qpnFiLAe8oBDupCOYwsj.ptQylgBhUkzafwiKKK2njFO', 'ROLE_CUSTOMER');

-- =====================
-- 3) ROOM TYPES
-- =====================
INSERT INTO room_type (type_name, description, price_per_night)
VALUES
    ('Single', 'Single bed',                            80.00),
    ('Double', 'Double bed',                           120.00),
    ('Deluxe', 'Deluxe King-size bed with view',       180.00),
    ('Suite',  'Suite with private balcony',           250.00);

-- =====================
-- 4) ROOMS
-- =====================
INSERT INTO room (room_number, status, price_per_night, room_type_id)
VALUES
    ('101', 'AVAILABLE',    100.00, 1),
    ('102', 'AVAILABLE',    150.00, 2),
    ('201', 'OCCUPIED',     180.00, 3),
    ('202', 'MAINTENANCE',  250.00, 4),
    ('301', 'AVAILABLE',    220.00, 3),
    ('302', 'AVAILABLE',    270.00, 4);

-- =====================
-- 5) PACKAGES
-- =====================
INSERT INTO package (
    package_name,
    food_included,
    number_of_days,
    max_people,
    facilities_included,
    package_price
)
VALUES
    ('Standard',  TRUE,  2, 2, 'WiFi, Breakfast',               200.00),
    ('Family',    TRUE,  3, 4, 'WiFi, Breakfast, Swimming Pool',350.00),
    ('Honeymoon', TRUE,  3, 2, 'WiFi, Dinner, Spa',             400.00),
    ('Business',  FALSE, 1, 1, 'WiFi, Workspace',               150.00);
