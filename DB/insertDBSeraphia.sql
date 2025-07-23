use mydb;
-- Tabla category
INSERT INTO category (category_name)
VALUES 
('Blusa'),
('Camiseta'),
('Pantalon'),
('Top'),
('Vestido');

-- Tabla color
INSERT INTO color (color_name)
VALUES 
('Amarillo'),
('Azul'),
('Beige'),
('Blanco'),
('Gris');

-- Tabla size
INSERT INTO size (size_name)
VALUES 
('Chica'), 
('Mediana'), 
('Grande'),
('Extra Chica'),
('Extra Grande');

-- Tabla user
INSERT INTO user 
(first_name, last_name, email, phone, password, role, data_register, street, num_int, num_ext, suburb, zip_code, city, state)
VALUES
('Ana', 'González', 'ana@mail.com', '5551234567', 'pass123', 'client', NOW(), 'Calle 1', '1', '101', 'Centro', '01000', 'CDMX', 'CDMX'),
('Luis', 'Ramírez', 'luis@mail.com', '5552345678', 'pass123', 'admin', NOW(), 'Calle 2', NULL, '102', 'Sur', '02000', 'CDMX', 'CDMX'),
('Clara', 'Soto', 'clara@mail.com', '5553456789', 'pass123', 'client', NOW(), 'Calle 3', '3', '103', 'Norte', '03000', 'CDMX', 'CDMX'),
('Miguel', 'Torres', 'miguel@mail.com', '5554567890', 'pass123', 'client', NOW(), 'Calle 4', NULL, '104', 'Este', '04000', 'CDMX', 'CDMX'),
('Sofía', 'Martínez', 'sofia@mail.com', '5555678901', 'pass123', 'admin', NOW(), 'Calle 5', '5', '105', 'Oeste', '05000', 'CDMX', 'CDMX');

-- Tabla product
INSERT INTO product 
(id_color, id_size, id_category, name, description, price, stock, creation_date, is_available)
VALUES 
(1, 2, 1, 'Blusa Amarilla con Bordado Floral', 'Lorem ipsum dolor sit amet.', 400.00, 1, CURDATE(), 1),
(2, 2, 1, 'Blusa Azul con Bordado Huipil', 'Lorem ipsum dolor sit amet.', 450.00, 1, CURDATE(), 1),
(3, 2, 1, 'Blusa Beige Estilo Bohemio', 'Lorem ipsum dolor sit amet.', 400.00, 1, CURDATE(), 1),
(4, 2, 1, 'Blusa Blanca con Encaje', 'Lorem ipsum dolor sit amet.', 450.00, 1, CURDATE(), 1),
(5, 2, 1, 'Blusa Gris con Manga Corta', 'Lorem ipsum dolor sit amet.', 400.00, 1, CURDATE(), 1);

-- Tabla image
INSERT INTO image (id_product, image_url, `order`)
VALUES
(1, 'https://ejemplo.com/img1.jpg', 1),
(2, 'https://ejemplo.com/img2.jpg', 2),
(3, 'https://ejemplo.com/img3.jpg', 3),
(4, 'https://ejemplo.com/img4.jpg', 4),
(5, 'https://ejemplo.com/img5.jpg', 5);

-- Tabla cart
INSERT INTO cart (id_user, date_creation, date_modification)
VALUES
(1, NOW(), NOW()),
(2, NOW(), NOW()),
(3, NOW(), NOW()),
(4, NOW(), NOW()),
(5, NOW(), NOW());

-- Tabla cartItem
INSERT INTO cartItem (id_product, id_cart)
VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5);

-- Tabla status
INSERT INTO status (name, description)
VALUES
('Disponible', 'Producto en almacén'),
('Reservado', 'Producto apartado'),
('Vendido', 'Producto vendido'),
('Devuelto', 'Producto devuelto'),
('Dañado', 'Producto con defecto');

-- Tabla inventory
INSERT INTO inventory (id_product, id_status, serie, income_date, outcome_date)
VALUES
(1, 1, 'ABC123', '2025-07-01', NULL),
(2, 2, 'DEF456', '2025-07-02', NULL),
(3, 3, 'GHI789', '2025-07-03', '2025-07-10'),
(4, 4, 'JKL012', '2025-07-04', '2025-07-12'),
(5, 5, 'MNO345', '2025-07-05', NULL);

-- Tabla order
INSERT INTO `order` (id_user, net_sale, order_date)
VALUES
(1, 400.00, '2025-07-10'),
(2, 450.00, '2025-07-11'),
(3, 400.00, '2025-07-12'),
(4, 450.00, '2025-07-13'),
(5, 400.00, '2025-07-14');

-- Tabla orderStatus
INSERT INTO orderStatus (status)
VALUES
('Pendiente'),
('Pagado'),
('Enviado'),
('Entregado'),
('Cancelado');

-- Tabla orderDetail
INSERT INTO orderDetail (id_product, id_order, id_order_status)
VALUES
(1, 1, 2),
(2, 2, 3),
(3, 3, 4),
(4, 4, 1),
(5, 5, 5);
