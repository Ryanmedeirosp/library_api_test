CREATE TABLE loan_book(
    id SERIAL PRIMARY KEY NOT NULL,
    id_loan INTEGER REFERENCES loans(id),
    id_book INTEGER REFERENCES books(id)
);