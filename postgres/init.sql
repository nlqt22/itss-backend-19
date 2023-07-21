CREATE TABLE organization(
    id          SERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL
);

INSERT INTO organization(name) VALUES ('California Fitness GYM & Yoga Vietnam');

CREATE TABLE role(
    id          SMALLSERIAL PRIMARY KEY,
    name        VARCHAR NOT NULL
);

INSERT INTO role(name) VALUES ('ADMIN'), ('MEMBER'), ('SALE_STAFF'), ('PT_STAFF');

CREATE TABLE account(
    id          SERIAL PRIMARY KEY,
    email       VARCHAR,
    password    VARCHAR,
    is_active   BOOLEAN DEFAULT TRUE,
    role_id     SMALLINT,
    organization_id INT,
    FOREIGN KEY (role_id) REFERENCES role(id),
    FOREIGN KEY (organization_id) REFERENCES organization(id)
);

CREATE TABLE person(
    id              SERIAL PRIMARY KEY,
    full_name       VARCHAR NOT NULL,
    phone_number    CHAR(10) NOT NULL,
    identity_card   CHAR(12),
    date_of_birth   DATE NOT NULL,
    account_id      INT,
    FOREIGN KEY(account_id) REFERENCES account(id)
);

CREATE TABLE equipment(
    id              SERIAL PRIMARY KEY,
    name            VARCHAR,
    price           FLOAT,
    qty             INT,
    origin          VARCHAR,
    import_date     TIMESTAMP,
    latest_updated  TIMESTAMP,
    is_active       BOOLEAN,
    organization_id INT,
    FOREIGN KEY(organization_id) REFERENCES organization(id)
);

CREATE TABLE package(
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR,
    price               FLOAT,
    duration            SMALLINT,
    pt_svc              BOOLEAN,
    created_date        TIMESTAMP,
    latest_updated      TIMESTAMP,
    is_active           BOOLEAN,
    organization_id     INT,
    FOREIGN KEY(organization_id) REFERENCES organization(id)
);

CREATE TABLE payment_log(
    id                  SERIAL PRIMARY KEY,
    member_id           INT,
    sale_id             INT,
    name                VARCHAR,
    duration            SMALLINT,
    price               FLOAT,
    created_date        TIMESTAMP,
    expired_date        TIMESTAMP,
    organization_id     INT,
    is_active           BOOLEAN,
    FOREIGN KEY (member_id) REFERENCES person(id),
    FOREIGN KEY (sale_id) REFERENCES person(id),
    FOREIGN KEY (organization_id) REFERENCES organization(id)
);

CREATE TABLE token(
    id                  SERIAL PRIMARY KEY,
    token               VARCHAR UNIQUE,
    token_type          VARCHAR,
    revoked             BOOLEAN,
    expired             BOOLEAN,
    account_id          INT,
    FOREIGN KEY (account_id) REFERENCES account(id)
);