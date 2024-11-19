CREATE TABLE users(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    register_date DATE NOT NULL,
    single_card UUID NOT NULL,
    status BOOLEAN DEFAULT true  
);

CREATE TABLE loans(
    id SERIAL PRIMARY KEY NOT NULL,
    start_date DATE NOT NULL,
    devolution_date DATE NOT NULL,
    status varchar(50) check (status in ('EM_ANDAMENTO','PENDENTE', 'CONCLUIDO')),
    id_user INTEGER REFERENCES users(id)
);

CREATE TABLE books(
    id SERIAL PRIMARY KEY NOT NULL,
    title VARCHAR(50) NOT NULL,
    author VARCHAR(50) NOT NULL,
    isbn VARCHAR(13) NOT NULL,
    status BOOLEAN DEFAULT true,  
    year_of_publication INTEGER NOT NULL
);

CREATE TABLE books_loans(
    id SERIAL PRIMARY KEY NOT NULL,
    loan_id INTEGER REFERENCES loans(id),
    book_id INTEGER REFERENCES books(id)
);

