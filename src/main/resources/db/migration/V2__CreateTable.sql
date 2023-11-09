CREATE TABLE IF NOT EXISTS cybriabank.customer (
    name VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    social_security_number VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    postal_code VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    country VARCHAR(255) NOT NULL,
    PRIMARY KEY (social_security_number)
);

grant SELECT, INSERT, UPDATE, DELETE on cybriabank.customer to ${appUser};

