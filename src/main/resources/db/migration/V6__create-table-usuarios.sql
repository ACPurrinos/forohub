CREATE TABLE usuarios(
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(90) NOT NULL,
    email  VARCHAR(40) NOT NULL UNIQUE,
    password VARCHAR(300) NOT NULL
)ENGINE=InnoDB