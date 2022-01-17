![UML DIAGRAM](https://github.com/FranckyMalliet/PayMyBuddy/tree/main/src/main/resources/UML_Diagram.jpg?raw=true)

![MPD](https://github.com/FranckyMalliet/PayMyBuddy/tree/main/src/main/resources/MPD.jpg?raw=true)

CREATE TABLE user (
                id_user INT AUTO_INCREMENT NOT NULL,
                email VARCHAR(100) NOT NULL,
                first_name VARCHAR(100) NOT NULL,
                last_name VARCHAR(100) NOT NULL,
                account DOUBLE PRECISION NOT NULL,
                password VARCHAR(100) NOT NULL,
                enabled BOOLEAN NOT NULL,
                role VARCHAR(100) NOT NULL,
                PRIMARY KEY (id_user)
);

CREATE TABLE correspondence (
                id_correspondence INT AUTO_INCREMENT NOT NULL,
                id_user INT NOT NULL,
                PRIMARY KEY (id_correspondence, id_user)
);


CREATE TABLE transfer (
                id_transfer INT AUTO_INCREMENT NOT NULL,
                id_user INT NOT NULL,
                id_correspondence INT NOT NULL,
                description VARCHAR(1000),
                amount DOUBLE PRECISION NOT NULL,
                transaction_fee DOUBLE PRECISION NOT NULL,
                PRIMARY KEY (id_transfer, id_user)
);

ALTER TABLE user
ADD CONSTRAINT UC_user UNIQUE (email);

ALTER TABLE correspondence ADD CONSTRAINT user_correspondence_fk
FOREIGN KEY (id_user)
REFERENCES user (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE correspondence ADD CONSTRAINT user_correspondence_fk1
FOREIGN KEY (id_user)
REFERENCES user (id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION;

ALTER TABLE transfer ADD CONSTRAINT correspondence_transfer_fk
FOREIGN KEY (id_correspondence, id_user)
REFERENCES correspondence (id_correspondence, id_user)
ON DELETE NO ACTION
ON UPDATE NO ACTION;
