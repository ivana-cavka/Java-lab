CREATE TABLE IF NOT EXISTS adress
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    street    VARCHAR(255) NULL,
    city      VARCHAR(255) NULL,
    postcode  VARCHAR(255) NULL,
    CONSTRAINT pk_adress PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS client
(
    id         BIGINT       NOT NULL AUTO_INCREMENT,
    first_name VARCHAR(255) NULL,
    last_name  VARCHAR(255) NULL,
    adress_id BIGINT       NULL,
    CONSTRAINT pk_client PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS device
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    name      VARCHAR(255) NULL,
    client_id BIGINT       NULL,
    CONSTRAINT pk_device PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS electricity_data
(
    id        BIGINT       NOT NULL AUTO_INCREMENT,
    date      VARCHAR(255) NULL,
    time      VARCHAR(255) NULL,
    value     DOUBLE       NULL,
    device_id BIGINT       NULL,
    CONSTRAINT pk_electricity_data PRIMARY KEY (id)
);

ALTER TABLE client
    ADD CONSTRAINT FK_CLIENT_ON_ADRESS FOREIGN KEY (adress_id) REFERENCES adress (id);

ALTER TABLE device
    ADD CONSTRAINT FK_DEVICE_ON_CLIENT FOREIGN KEY (client_id) REFERENCES client (id);

ALTER TABLE electricity_data
    ADD CONSTRAINT FK_ELECTRICITY_DATA_ON_DEVICE FOREIGN KEY (device_id) REFERENCES device (id);



