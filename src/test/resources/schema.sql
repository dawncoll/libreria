DROP TABLE IF EXISTS libro;
DROP TABLE IF EXISTS autor;

CREATE TABLE autor (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    primer_apellido VARCHAR(100),
    segundo_apellido VARCHAR(100),
    descripcion VARCHAR(255)
);

CREATE TABLE libro (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(200) NOT NULL,
    primera_edicion DATE,
    editor VARCHAR(100),
    descripcion VARCHAR(255),
    isbn VARCHAR(20),
    autor_id BIGINT,
    FOREIGN KEY (autor_id) REFERENCES autor(id)
);
