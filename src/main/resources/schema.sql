CREATE TABLE IF NOT EXISTS customer
(
    customer_id VARCHAR(255) PRIMARY KEY,
    first_name  VARCHAR(255) NOT NULL,
    last_name   VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS transaction
(
    transaction_id      VARCHAR(255) PRIMARY KEY,
    customer_id         VARCHAR(255) NOT NULL,
    customer_first_name VARCHAR(255) NOT NULL,
    customer_last_name  VARCHAR(255) NOT NULL,
    amount              INT          NOT NULL,
    point               INT          NOT NULL,
    transaction_time    DATETIME     NOT NULL
);

INSERT INTO customer(customer_id, first_name, last_name)
VALUES ('fa710852-d7c5-465f-81c2-1473b6a3bd9e', 'Alex', 'Booker'),
       ('d835232f-05a1-4350-bdf8-e3fc2b937ae3', 'Ellie', 'Chavez');

INSERT INTO transaction(transaction_id, customer_id, customer_first_name, customer_last_name, amount, point,
                        transaction_time)
VALUES ('6ea2c539-758c-47e6-8b67-485105b79e1f', 'fa710852-d7c5-465f-81c2-1473b6a3bd9e', 'Alex', 'Booker', 120, 90,
        '2022-04-22T00:00:00.000+00:00'),
       ('b70090e6-b9d9-4341-90cf-00ef666d49c5', 'd835232f-05a1-4350-bdf8-e3fc2b937ae3', 'Ellie', 'Chavez', 100, 50,
        '2022-04-23T00:00:00.000+00:00');