CREATE TABLE IF NOT EXISTS _user
(
    id        BIGINT PRIMARY KEY AUTO_INCREMENT,
    email     VARCHAR(255) NOT NULL,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(255) NOT NULL,
    withdrawl BOOLEAN      NOT NULL
);

INSERT INTO _user (email, password, role, withdrawl)
VALUES ('admin@example.com', '$2a$10$OCu5kEROY5jut74myGqN/O56uTTZujMG6GxEj3keIzNHwPdunNaWu', 'USER', false);