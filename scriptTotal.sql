-- Dia_0.1.2

-- Crear la base de datos
CREATE DATABASE chinankodb;

-- Crear usuario con contraseña
-- Cambia 'musica_user' y 'tu_contraseña_segura' por lo que necesites
CREATE USER chinanko_user WITH ENCRYPTED PASSWORD 'Chinanko123';

-- Otorgar privilegios de conexión y uso sobre la base de datos
GRANT CONNECT ON DATABASE chinankodb TO chinanko_user;


-- Dar permisos de uso sobre el esquema público
GRANT USAGE ON SCHEMA public TO chinanko_user;

-- Dar permisos sobre todas las tablas existentes
GRANT SELECT, INSERT, UPDATE, DELETE ON ALL TABLES IN SCHEMA public TO chinanko_user;

-- Dar permisos sobre las secuencias (necesarias por SERIAL)
GRANT USAGE, SELECT, UPDATE ON ALL SEQUENCES IN SCHEMA public TO chinanko_user;

-- Para que también tenga permisos sobre futuras tablas/secuencias
ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT SELECT, INSERT, UPDATE, DELETE ON TABLES TO chinanko_user;

ALTER DEFAULT PRIVILEGES IN SCHEMA public
GRANT USAGE, SELECT, UPDATE ON SEQUENCES TO chinanko_user;


-- =================================================================================
-- TABLAS MAESTRAS (Catálogos, Tipos, Ubicaciones, Usuarios y Productos)
-- =================================================================================

-- Tablas de Tipos y Roles
CREATE TABLE IF NOT EXISTS TYPES_OF_PRODUCT (
    pk_id_type_of_product SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TYPE_OF_INTEREST_POINTS (
    pk_id_type_of_interest_point SERIAL PRIMARY KEY,
    name_type_of_interest_point VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TYPES_OF_OPINIONS (
    pk_id_type_of_opinion SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ROLES (
    pk_id_rol SERIAL PRIMARY KEY,
    name_rol VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TYPE_OF_EVENTS (
    pk_id_type_of_event SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS STATE_OF_EVENTS (
    pk_id_state_of_event SERIAL PRIMARY KEY,
    state VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TYPES_OF_OPINIONS_SUGGESTED_POINTS (
    pk_id_type_of_opinion_suggested_point SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TYPE_OF_SUGGESTED_POINTS (
    pk_id_type_of_suggested_point SERIAL PRIMARY KEY,
    name_type_of_suggested_point VARCHAR(100) NOT NULL UNIQUE
);
---
--## Tablas de Ubicación Geográfica
---
CREATE TABLE IF NOT EXISTS STATES (
    pk_id_state SERIAL PRIMARY KEY,
    name_state VARCHAR(100) NOT NULL UNIQUE,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);

CREATE TABLE IF NOT EXISTS TOWNS (
    pk_id_town SERIAL PRIMARY KEY,
    name_town VARCHAR(100) NOT NULL,
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    fk_id_state INTEGER NOT NULL,
    FOREIGN KEY (fk_id_state) REFERENCES STATES (pk_id_state) ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE (name_town, fk_id_state) -- Un pueblo puede tener el mismo nombre en diferentes estados
);
---
--## Tablas de Usuarios
---
CREATE TABLE IF NOT EXISTS USERS (
    pk_id_user SERIAL PRIMARY KEY,
	name_user VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS PROFILES_USERS (
	pk_id_profile_user SERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    born_date DATE,
	fk_id_town INTEGER,
    fk_id_rol INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL UNIQUE, -- Relación 1:1 con USERS
    FOREIGN KEY (fk_id_town) REFERENCES TOWNS (pk_id_town) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_rol) REFERENCES ROLES (pk_id_rol) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE RESTRICT ON UPDATE CASCADE
);
---
--## Tablas de Productos y Catálogos
---
CREATE TABLE IF NOT EXISTS CATALOGS (
    pk_id_catalog SERIAL PRIMARY KEY,
    name_catalog VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
    pk_id_product SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    stock INTEGER NOT NULL DEFAULT 0,
    fk_id_catalog INTEGER,
    fk_id_type_of_product INTEGER NOT NULL,
    FOREIGN KEY (fk_id_catalog) REFERENCES CATALOGS (pk_id_catalog) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_product) REFERENCES TYPES_OF_PRODUCT (pk_id_type_of_product) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS PICTURES_PRODUCTS (
    pk_id_picture_product SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    fk_id_product INTEGER NOT NULL,
    FOREIGN KEY (fk_id_product) REFERENCES PRODUCTS (pk_id_product) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS SALES (
    pk_id_sale SERIAL PRIMARY KEY,
    sale_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    fk_id_user INTEGER NOT NULL, -- Usuario que realiza la compra
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS SALES_PRODUCTS (
    pk_id_sale_product SERIAL PRIMARY KEY,
    unit_price DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    fk_id_product INTEGER NOT NULL,
    fk_id_sale INTEGER NOT NULL,
    FOREIGN KEY (fk_id_product) REFERENCES PRODUCTS (pk_id_product) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_sale) REFERENCES SALES (pk_id_sale) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_product, fk_id_sale) -- Evita duplicar el mismo producto en la misma venta
);

---
--## PUNTOS DE INTERÉS
---
CREATE TABLE IF NOT EXISTS INTEREST_POINTS (
    pk_id_interest_point SERIAL PRIMARY KEY,
    name_interest_point VARCHAR(255) NOT NULL,
    description TEXT,
    fk_id_town INTEGER NOT NULL,
    fk_id_type_of_interest_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_town) REFERENCES TOWNS (pk_id_town) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_interest_point) REFERENCES TYPE_OF_INTEREST_POINTS (pk_id_type_of_interest_point) ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE (name_interest_point, fk_id_town) -- Un punto de interés puede tener el mismo nombre en diferentes pueblos
);

CREATE TABLE IF NOT EXISTS IMAGES_INTEREST_POINT (
    pk_id_image_interest_point SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    fk_id_interest_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS OPINIONS_INTEREST_POINTS (
    pk_id_opinion_interest_point SERIAL PRIMARY KEY,
    opinion TEXT,
    fk_id_interest_point INTEGER NOT NULL,
    fk_id_type_of_opinion INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL,
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_opinion) REFERENCES TYPES_OF_OPINIONS (pk_id_type_of_opinion) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_interest_point, fk_id_user) -- Un usuario solo puede dejar una opinión de un tipo específico por punto de interés
);

CREATE TABLE IF NOT EXISTS AVERAGE_INTEREST_POINTS (
    pk_id_average_interest_point SERIAL PRIMARY KEY,
    total INTEGER NOT NULL DEFAULT 0,
    average DECIMAL(2,1) NOT NULL DEFAULT 0.0,
    fk_id_interest_point INTEGER NOT NULL UNIQUE, -- Relación 1:1 con INTEREST_POINTS
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ADDRESS_INTEREST_POINT (
    pk_id_address_interest_point SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    exterior_number VARCHAR(50),
    interior_number VARCHAR(50),
    neighborhood VARCHAR(100),
    postal_code VARCHAR(10),
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    fk_id_interest_point INTEGER NOT NULL UNIQUE, -- Relación 1:1 con INTEREST_POINTS
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE
);

---
--## PUNTOS SUGERIDOS
---
CREATE TABLE IF NOT EXISTS SUGGESTED_POINT (
    pk_id_suggested_point SERIAL PRIMARY KEY,
    name_suggested_point VARCHAR(255) NOT NULL,
    description TEXT,
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    likes INTEGER DEFAULT 0,
    dislikes INTEGER DEFAULT 0,
    fk_id_type_of_suggested_point INTEGER NOT NULL,
    fk_id_state INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL, -- Usuario que sugiere el punto
    FOREIGN KEY (fk_id_type_of_suggested_point) REFERENCES TYPE_OF_SUGGESTED_POINTS (pk_id_type_of_suggested_point) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_state) REFERENCES STATES (pk_id_state) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS IMAGES_SUGGESTED_POINT (
    pk_id_image_suggested_point SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    fk_id_suggested_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS OPINIONS_SUGGESTED_POINTS (
    pk_id_opinion_suggested_point SERIAL PRIMARY KEY,
    opinion TEXT,
    fk_id_suggested_point INTEGER NOT NULL,
    fk_id_type_of_opinion_suggested_point INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL,
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_opinion_suggested_point) REFERENCES TYPES_OF_OPINIONS_SUGGESTED_POINTS (pk_id_type_of_opinion_suggested_point) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_suggested_point, fk_id_user) -- Un usuario solo puede dejar una opinión de un tipo específico por punto sugerido
);

CREATE TABLE IF NOT EXISTS STATES_SUGGESTED_POINT (
    pk_id_state_suggested_point SERIAL PRIMARY KEY,
    fk_id_state INTEGER NOT NULL,
    fk_id_suggested_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_state) REFERENCES STATES (pk_id_state) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_state, fk_id_suggested_point) -- Evita duplicados en la relación
);

CREATE TABLE IF NOT EXISTS AVERAGE_SUGGESTED_POINT (
    pk_id_average_classification SERIAL PRIMARY KEY,
    total INTEGER NOT NULL DEFAULT 0,
    average DECIMAL(2,1) NOT NULL DEFAULT 0.0,
    likes INTEGER DEFAULT 0,
    fk_id_suggested_point INTEGER NOT NULL UNIQUE, -- Relación 1:1 con SUGGESTED_POINT
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE
);

---
--## EVENTOS
---
CREATE TABLE IF NOT EXISTS EVENTS (
    pk_id_event SERIAL PRIMARY KEY,
    name_event VARCHAR(255) NOT NULL,
    time_end TIMESTAMP,
    longitude DECIMAL(5,10),
    latitude DECIMAL(5,10),
    price DECIMAL(10,2),
    fk_id_town INTEGER NOT NULL,
    fk_id_type_of_event INTEGER NOT NULL,
    fk_id_state_of_event INTEGER NOT NULL,
    FOREIGN KEY (fk_id_town) REFERENCES TOWNS (pk_id_town) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_event) REFERENCES TYPE_OF_EVENTS (pk_id_type_of_event) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_state_of_event) REFERENCES STATE_OF_EVENTS (pk_id_state_of_event) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ADDRESS_EVENTS (
    pk_id_address_event SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    exterior_number VARCHAR(20),
    interior_number VARCHAR(20),
    neighborhood VARCHAR(100),
    postal_code VARCHAR(10),
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    fk_id_event INTEGER NOT NULL UNIQUE, -- Relación 1:1 con EVENTS
    FOREIGN KEY (fk_id_event) REFERENCES EVENTS (pk_id_event) ON DELETE CASCADE ON UPDATE CASCADE
);

---
--## NOTIFICACIONES
---

CREATE TABLE IF NOT EXISTS TYPES_OF_NOTIFICATIONS (
    pk_id_type_of_notification SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS NOTIFICATIONS (
    pk_id_notification SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    state BOOLEAN NOT NULL DEFAULT FALSE, -- Estado de la notificación (ej. no leída/leída)
    fk_id_user_creator INTEGER, -- El usuario que originó la acción que causa la notificación (ej. un "me gusta")
    fk_id_type_of_notification INTEGER NOT NULL,
    FOREIGN KEY (fk_id_user_creator) REFERENCES USERS (pk_id_user) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_notification) REFERENCES TYPES_OF_NOTIFICATIONS (pk_id_type_of_notification) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS NOTIFICATIONS_PROFILE_USERS (
    pk_id_notification_profile_user SERIAL PRIMARY KEY,
    state BOOLEAN NOT NULL DEFAULT FALSE, -- Estado de la notificación para el usuario (ej. no leída/leída)
    fk_id_notification INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL, -- El usuario que recibe la notificación
    FOREIGN KEY (fk_id_notification) REFERENCES NOTIFICATIONS (pk_id_notification) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_notification, fk_id_user) -- Una notificación a un usuario es única
);

CREATE TABLE IF NOT EXISTS TYPE_OF_EVENTS (
    pk_id_type_of_event SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS STATE_OF_EVENTS (
    pk_id_state_of_event SERIAL PRIMARY KEY,
    state VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TYPES_OF_OPINIONS_SUGGESTED_POINTS (
    pk_id_type_of_opinion_suggested_point SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS TYPE_OF_SUGGESTED_POINTS (
    pk_id_type_of_suggested_point SERIAL PRIMARY KEY,
    name_type_of_suggested_point VARCHAR(100) NOT NULL UNIQUE
);
---
## Tablas de Ubicación Geográfica
---
CREATE TABLE IF NOT EXISTS STATES (
    pk_id_state SERIAL PRIMARY KEY,
    name_state VARCHAR(100) NOT NULL UNIQUE,
    latitude DECIMAL(9,6),
    longitude DECIMAL(9,6)
);

CREATE TABLE IF NOT EXISTS TOWNS (
    pk_id_town SERIAL PRIMARY KEY,
    name_town VARCHAR(100) NOT NULL,
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    fk_id_state INTEGER NOT NULL,
    FOREIGN KEY (fk_id_state) REFERENCES STATES (pk_id_state) ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE (name_town, fk_id_state) -- Un pueblo puede tener el mismo nombre en diferentes estados
);
---
## Tablas de Usuarios
---
CREATE TABLE IF NOT EXISTS USERS (
    pk_id_user SERIAL PRIMARY KEY,
	name_user VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS PROFILES_USERS (
	pk_id_profile_user SERIAL PRIMARY KEY,
	first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    born_date DATE,
	fk_id_town INTEGER,
    fk_id_rol INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL UNIQUE, -- Relación 1:1 con USERS
    FOREIGN KEY (fk_id_town) REFERENCES TOWNS (pk_id_town) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_rol) REFERENCES ROLES (pk_id_rol) ON DELETE RESTRICT ON UPDATE CASCADE,
	FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE RESTRICT ON UPDATE CASCADE
);
---
## Tablas de Productos y Catálogos
---
CREATE TABLE IF NOT EXISTS CATALOGS (
    pk_id_catalog SERIAL PRIMARY KEY,
    name_catalog VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);

CREATE TABLE IF NOT EXISTS PRODUCTS (
    pk_id_product SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    price DECIMAL(10,2) NOT NULL,
    description TEXT,
    stock INTEGER NOT NULL DEFAULT 0,
    fk_id_catalog INTEGER,
    fk_id_type_of_product INTEGER NOT NULL,
    FOREIGN KEY (fk_id_catalog) REFERENCES CATALOGS (pk_id_catalog) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_product) REFERENCES TYPES_OF_PRODUCT (pk_id_type_of_product) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS PICTURES_PRODUCTS (
    pk_id_picture_product SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    fk_id_product INTEGER NOT NULL,
    FOREIGN KEY (fk_id_product) REFERENCES PRODUCTS (pk_id_product) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS SALES (
    pk_id_sale SERIAL PRIMARY KEY,
    sale_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    total DECIMAL(10,2) NOT NULL,
    fk_id_user INTEGER NOT NULL, -- Usuario que realiza la compra
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS SALES_PRODUCTS (
    pk_id_sale_product SERIAL PRIMARY KEY,
    unit_price DECIMAL(10,2) NOT NULL,
    quantity INTEGER NOT NULL,
    fk_id_product INTEGER NOT NULL,
    fk_id_sale INTEGER NOT NULL,
    FOREIGN KEY (fk_id_product) REFERENCES PRODUCTS (pk_id_product) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_sale) REFERENCES SALES (pk_id_sale) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_product, fk_id_sale) -- Evita duplicar el mismo producto en la misma venta
);

---
## PUNTOS DE INTERÉS
---
CREATE TABLE IF NOT EXISTS INTEREST_POINTS (
    pk_id_interest_point SERIAL PRIMARY KEY,
    name_interest_point VARCHAR(255) NOT NULL,
    description TEXT,
    fk_id_town INTEGER NOT NULL,
    fk_id_type_of_interest_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_town) REFERENCES TOWNS (pk_id_town) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_interest_point) REFERENCES TYPE_OF_INTEREST_POINTS (pk_id_type_of_interest_point) ON DELETE RESTRICT ON UPDATE CASCADE,
    UNIQUE (name_interest_point, fk_id_town) -- Un punto de interés puede tener el mismo nombre en diferentes pueblos
);

CREATE TABLE IF NOT EXISTS IMAGES_INTEREST_POINT (
    pk_id_image_interest_point SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    fk_id_interest_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS OPINIONS_INTEREST_POINTS (
    pk_id_opinion_interest_point SERIAL PRIMARY KEY,
    opinion TEXT,
    fk_id_interest_point INTEGER NOT NULL,
    fk_id_type_of_opinion INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL,
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_opinion) REFERENCES TYPES_OF_OPINIONS (pk_id_type_of_opinion) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_interest_point, fk_id_user) -- Un usuario solo puede dejar una opinión de un tipo específico por punto de interés
);

CREATE TABLE IF NOT EXISTS AVERAGE_INTEREST_POINTS (
    pk_id_average_interest_point SERIAL PRIMARY KEY,
    total INTEGER NOT NULL DEFAULT 0,
    average DECIMAL(2,1) NOT NULL DEFAULT 0.0,
    fk_id_interest_point INTEGER NOT NULL UNIQUE, -- Relación 1:1 con INTEREST_POINTS
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ADDRESS_INTEREST_POINT (
    pk_id_address_interest_point SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    exterior_number VARCHAR(20),
    interior_number VARCHAR(20),
    neighborhood VARCHAR(100),
    postal_code VARCHAR(10),
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    fk_id_interest_point INTEGER NOT NULL UNIQUE, -- Relación 1:1 con INTEREST_POINTS
    FOREIGN KEY (fk_id_interest_point) REFERENCES INTEREST_POINTS (pk_id_interest_point) ON DELETE CASCADE ON UPDATE CASCADE
);

---
## PUNTOS SUGERIDOS
---
CREATE TABLE IF NOT EXISTS SUGGESTED_POINT (
    pk_id_suggested_point SERIAL PRIMARY KEY,
    name_suggested_point VARCHAR(255) NOT NULL,
    description TEXT,
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    likes INTEGER DEFAULT 0,
    dislikes INTEGER DEFAULT 0,
    fk_id_type_of_suggested_point INTEGER NOT NULL,
    fk_id_state INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL, -- Usuario que sugiere el punto
    FOREIGN KEY (fk_id_type_of_suggested_point) REFERENCES TYPE_OF_SUGGESTED_POINTS (pk_id_type_of_suggested_point) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_state) REFERENCES STATES (pk_id_state) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS IMAGES_SUGGESTED_POINT (
    pk_id_image_suggested_point SERIAL PRIMARY KEY,
    url VARCHAR(255) NOT NULL,
    fk_id_suggested_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS OPINIONS_SUGGESTED_POINTS (
    pk_id_opinion_suggested_point SERIAL PRIMARY KEY,
    opinion TEXT,
    fk_id_suggested_point INTEGER NOT NULL,
    fk_id_type_of_opinion_suggested_point INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL,
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_opinion_suggested_point) REFERENCES TYPES_OF_OPINIONS_SUGGESTED_POINTS (pk_id_type_of_opinion_suggested_point) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_suggested_point, fk_id_user) -- Un usuario solo puede dejar una opinión de un tipo específico por punto sugerido
);

CREATE TABLE IF NOT EXISTS STATES_SUGGESTED_POINT (
    pk_id_state_suggested_point SERIAL PRIMARY KEY,
    fk_id_state INTEGER NOT NULL,
    fk_id_suggested_point INTEGER NOT NULL,
    FOREIGN KEY (fk_id_state) REFERENCES STATES (pk_id_state) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_state, fk_id_suggested_point) -- Evita duplicados en la relación
);

CREATE TABLE IF NOT EXISTS AVERAGE_SUGGESTED_POINT (
    pk_id_average_classification SERIAL PRIMARY KEY,
    total INTEGER NOT NULL DEFAULT 0,
    average DECIMAL(2,1) NOT NULL DEFAULT 0.0,
    likes INTEGER DEFAULT 0,
    fk_id_suggested_point INTEGER NOT NULL UNIQUE, -- Relación 1:1 con SUGGESTED_POINT
    FOREIGN KEY (fk_id_suggested_point) REFERENCES SUGGESTED_POINT (pk_id_suggested_point) ON DELETE CASCADE ON UPDATE CASCADE
);

---
## EVENTOS
---
CREATE TABLE IF NOT EXISTS EVENTS (
    pk_id_event SERIAL PRIMARY KEY,
    name_event VARCHAR(255) NOT NULL,
    time_end TIMESTAMP,
    longitude DECIMAL(5,10),
    latitude DECIMAL(5,10),
    price DECIMAL(10,2),
    fk_id_town INTEGER NOT NULL,
    fk_id_type_of_event INTEGER NOT NULL,
    fk_id_state_of_event INTEGER NOT NULL,
    FOREIGN KEY (fk_id_town) REFERENCES TOWNS (pk_id_town) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_event) REFERENCES TYPE_OF_EVENTS (pk_id_type_of_event) ON DELETE RESTRICT ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_state_of_event) REFERENCES STATE_OF_EVENTS (pk_id_state_of_event) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS ADDRESS_EVENTS (
    pk_id_address_event SERIAL PRIMARY KEY,
    street VARCHAR(255) NOT NULL,
    exterior_number VARCHAR(20),
    interior_number VARCHAR(20),
    neighborhood VARCHAR(100),
    postal_code VARCHAR(10),
    latitude DECIMAL(5,10),
    longitude DECIMAL(5,10),
    fk_id_event INTEGER NOT NULL UNIQUE, -- Relación 1:1 con EVENTS
    FOREIGN KEY (fk_id_event) REFERENCES EVENTS (pk_id_event) ON DELETE CASCADE ON UPDATE CASCADE
);

---
## NOTIFICACIONES
---

CREATE TABLE IF NOT EXISTS TYPES_OF_NOTIFICATIONS (
    pk_id_type_of_notification SERIAL PRIMARY KEY,
    type VARCHAR(50) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS NOTIFICATIONS (
    pk_id_notification SERIAL PRIMARY KEY,
    description TEXT NOT NULL,
    state BOOLEAN NOT NULL DEFAULT FALSE, -- Estado de la notificación (ej. no leída/leída)
    fk_id_user_creator INTEGER, -- El usuario que originó la acción que causa la notificación (ej. un "me gusta")
    fk_id_type_of_notification INTEGER NOT NULL,
    FOREIGN KEY (fk_id_user_creator) REFERENCES USERS (pk_id_user) ON DELETE SET NULL ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_type_of_notification) REFERENCES TYPES_OF_NOTIFICATIONS (pk_id_type_of_notification) ON DELETE RESTRICT ON UPDATE CASCADE
);

CREATE TABLE IF NOT EXISTS NOTIFICATIONS_PROFILE_USERS (
    pk_id_notification_profile_user SERIAL PRIMARY KEY,
    state BOOLEAN NOT NULL DEFAULT FALSE, -- Estado de la notificación para el usuario (ej. no leída/leída)
    fk_id_notification INTEGER NOT NULL,
    fk_id_user INTEGER NOT NULL, -- El usuario que recibe la notificación
    FOREIGN KEY (fk_id_notification) REFERENCES NOTIFICATIONS (pk_id_notification) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (fk_id_user) REFERENCES USERS (pk_id_user) ON DELETE CASCADE ON UPDATE CASCADE,
    UNIQUE (fk_id_notification, fk_id_user) -- Una notificación a un usuario es única
);
