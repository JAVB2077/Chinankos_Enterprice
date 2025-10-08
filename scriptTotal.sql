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

-- ======================
-- TABLAS MAESTRAS
-- ======================

CREATE TABLE STATES (
    pk_id_state SERIAL PRIMARY KEY,
    name_state VARCHAR(100) NOT NULL,
    longitude DECIMAL(10,6),
    latitude DECIMAL(10,6)
);

CREATE TABLE TOWNS (
    pk_id_town SERIAL PRIMARY KEY,
    name_town VARCHAR(100) NOT NULL,
    longitude DECIMAL(10,6),
    latitude DECIMAL(10,6),
    fk_id_state INT REFERENCES STATES(pk_id_state) ON DELETE CASCADE
);

CREATE TABLE ROLES (
    pk_id_rol SERIAL PRIMARY KEY,
    name_rol VARCHAR(50) NOT NULL
);

CREATE TABLE USERS (
    pk_id_user SERIAL PRIMARY KEY,
    name_user VARCHAR(50) NOT NULL,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(200) NOT NULL,
    fk_id_town INT REFERENCES TOWNS(pk_id_town) ON DELETE SET NULL,
    fk_id_rol INT REFERENCES ROLES(pk_id_rol) ON DELETE SET NULL
);

-- ======================
-- PUNTOS DE INTERÉS
-- ======================

CREATE TABLE TYPE_OF_INTEREST_POINTS (
    pk_id_type_of_interest_point SERIAL PRIMARY KEY,
    name_type_of_interest_point VARCHAR(100) NOT NULL
);

CREATE TABLE INTEREST_POINTS (
    pk_id_interest_point SERIAL PRIMARY KEY,
    name_interest_point VARCHAR(100) NOT NULL,
    longitude DECIMAL(10,6),
    latitude DECIMAL(10,6),
    likes INT DEFAULT 0,
    dislikes INT DEFAULT 0,
    fk_type_of_interest_point INT REFERENCES TYPE_OF_INTEREST_POINTS(pk_id_type_of_interest_point) ON DELETE SET NULL,
    fk_id_town INT REFERENCES TOWNS(pk_id_town) ON DELETE CASCADE
);

CREATE TABLE TYPES_OF_OPINIONS (
    pk_id_type_of_opinion SERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE OPINIONS_INTEREST_POINTS (
    pk_id_opinion_interest_point SERIAL PRIMARY KEY,
    opinion TEXT NOT NULL,
    fk_interest_point INT REFERENCES INTEREST_POINTS(pk_id_interest_point) ON DELETE CASCADE,
    fk_type_of_opinion INT REFERENCES TYPES_OF_OPINIONS(pk_id_type_of_opinion) ON DELETE SET NULL,
    fk_id_user INT REFERENCES USERS(pk_id_user) ON DELETE CASCADE
);

-- ======================
-- PUNTOS SUGERIDOS
-- ======================

CREATE TABLE TYPE_OF_SUGGESTED_POINTS (
    pk_id_type_of_suggested_point SERIAL PRIMARY KEY,
    name_type_of_suggested_point VARCHAR(100) NOT NULL
);

CREATE TABLE STATES_SUGGESTED_POINT (
    pk_id_state_suggested_point SERIAL PRIMARY KEY,
    state VARCHAR(50) NOT NULL
);

CREATE TABLE SUGGESTED_POINT (
    pk_id_suggested_point SERIAL PRIMARY KEY,
    name_suggested_point VARCHAR(100) NOT NULL,
    longitude DECIMAL(10,6),
    latitude DECIMAL(10,6),
    likes INT DEFAULT 0,
    dislikes INT DEFAULT 0,
    fk_type_of_suggested_point INT REFERENCES TYPE_OF_SUGGESTED_POINTS(pk_id_type_of_suggested_point) ON DELETE SET NULL,
    fk_state_suggested_point INT REFERENCES STATES_SUGGESTED_POINT(pk_id_state_suggested_point) ON DELETE SET NULL,
    fk_id_town INT REFERENCES TOWNS(pk_id_town) ON DELETE CASCADE,
    fk_id_user INT REFERENCES USERS(pk_id_user) ON DELETE CASCADE
);

CREATE TABLE TYPES_OF_OPINIONS_SUGGESTED_POINTS (
    pk_id_type_of_opinion SERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE OPINIONS_SUGGESTED_POINTS (
    pk_id_opinion_suggested_point SERIAL PRIMARY KEY,
    opinion TEXT NOT NULL,
    fk_suggested_point INT REFERENCES SUGGESTED_POINT(pk_id_suggested_point) ON DELETE CASCADE,
    fk_type_of_opinion_suggested_point INT REFERENCES TYPES_OF_OPINIONS_SUGGESTED_POINTS(pk_id_type_of_opinion) ON DELETE SET NULL,
    fk_id_user INT REFERENCES USERS(pk_id_user) ON DELETE CASCADE
);

-- ======================
-- EVENTOS
-- ======================

CREATE TABLE TYPE_OF_EVENTS (
    pk_id_type_of_event SERIAL PRIMARY KEY,
    type VARCHAR(100) NOT NULL
);

CREATE TABLE STATE_OF_EVENTS (
    pk_id_state_of_event SERIAL PRIMARY KEY,
    state VARCHAR(50) NOT NULL
);

CREATE TABLE EVENTS (
    pk_id_event SERIAL PRIMARY KEY,
    name_event VARCHAR(100) NOT NULL,
    time_begin TIMESTAMP,
    time_end TIMESTAMP,
    longitude DECIMAL(10,6),
    latitude DECIMAL(10,6),
    price DECIMAL(10,2),
    fk_id_town INT REFERENCES TOWNS(pk_id_town) ON DELETE CASCADE,
    fk_id_type_of_event INT REFERENCES TYPE_OF_EVENTS(pk_id_type_of_event) ON DELETE SET NULL,
    fk_id_state_of_event INT REFERENCES STATE_OF_EVENTS(pk_id_state_of_event) ON DELETE SET NULL
);
