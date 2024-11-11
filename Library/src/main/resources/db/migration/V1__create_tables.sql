CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    register_date DATE NOT NULL,
    single_card INTEGER NOT NULL,
    status VARCHAR(50) NOT NULL
);

CREATE TABLE loans(
    id SERIAL PRIMARY KEY NOT NULL,
    start_date DATE NOT NULL,
    devolution_date DATE NOT NULL,
    status VARCHAR(50) NOT NULL,
    id_user INTEGER REFERENCES users(id)
);

CREATE TABLE books(
    id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    isbn VARCHAR(13) NOT NULL,
    year_of_publication INTEGER NOT NULL
);

CREATE TABLE loan_book(
    id SERIAL PRIMARY KEY NOT NULL,
    id_loan INTEGER REFERENCES loans(id),
    id_book INTEGER REFERENCES books(id)
);