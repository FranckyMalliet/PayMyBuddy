CREATE TABLE user (
                email VARCHAR(100) NOT NULL,
                id_user INT NOT NULL,
                first_name VARCHAR(100) NOT NULL,
                last_name VARCHAR(100) NOT NULL,
                account DOUBLE PRECISION NOT NULL,
                password VARCHAR(100) NOT NULL,
                enabled BOOLEAN NOT NULL,
                role VARCHAR(100) NOT NULL,
                PRIMARY KEY (email)
);


CREATE TABLE correspondence (
                id_correspondence INT AUTO_INCREMENT NOT NULL,
                email VARCHAR(100) NOT NULL,
                user_email_correspondence VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_correspondence)
);


CREATE TABLE transfer (
                id_transfer INT AUTO_INCREMENT NOT NULL,
                id_correspondence INT NOT NULL,
                description VARCHAR(1000),
                amount DOUBLE PRECISION NOT NULL,
                transaction_fee DOUBLE PRECISION NOT NULL,
                PRIMARY KEY (id_transfer)
);


ALTER TABLE correspondence ADD CONSTRAINT user_correspondence_fk
FOREIGN KEY (email)
REFERENCES user (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE correspondence ADD CONSTRAINT user_correspondence_fk1
FOREIGN KEY (user_email_correspondence)
REFERENCES user (email)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transfer ADD CONSTRAINT correspondence_transfer_fk
FOREIGN KEY (id_correspondence)
REFERENCES correspondence (id_correspondence)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
