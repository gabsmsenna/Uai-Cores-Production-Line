CREATE TABLE external_officer (
    externalofficer_id SERIAL PRIMARY KEY,
    externalofficer_name VARCHAR(255) NOT NULL UNIQUE,
    externalofficer_login VARCHAR(255) NOT NULL UNIQUE,
    externalofficer_password VARCHAR(255) NOT NULL
);

CREATE TABLE admin_user (
    adminuser_id SERIAL PRIMARY KEY,
    adminuser_name TEXT NOT NULL UNIQUE,
    adminuser_login TEXT NOT NULL UNIQUE,
    adminuser_password TEXT NOT NULL UNIQUE
);

CREATE TABLE client (
    client_id SERIAL PRIMARY KEY,
    client_name TEXT NOT NULL
);

CREATE TABLE orders (
    order_id SERIAL PRIMARY KEY,
    order_entry_date DATE NOT NULL,
    order_delivery_date DATE NOT NULL,
    order_status TEXT NOT NULL,
    client_id BIGINT NOT NULL,
    CONSTRAINT fk_client FOREIGN KEY (client_id) REFERENCES client(client_id)
);

CREATE TABLE task (
    task_id SERIAL PRIMARY KEY,
    task_amount INT NOT NULL CHECK ( task_amount >= 1 ),
    task_name TEXT NOT NULL,
    task_description TEXT NOT NULL,
    task_versecolor TEXT NOT NULL,
    task_material TEXT NOT NULL,
    order_id BIGINT NOT NULL,
    CONSTRAINT fk_order FOREIGN KEY (order_id) REFERENCES orders (order_id)
);

