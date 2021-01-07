CREATE DATABASE utez;
USE utez;
CREATE TABLE puesto(
    id INT(11) PRIMARY KEY NOT NULL,
    puesto VARCHAR(30) NOT NULL,
    descripcion TEXT NOT NULL
);
CREATE TABLE personal(
    id INT(11) PRIMARY KEY NOT NULL,
    nombre VARCHAR(30) NOT NULL,
    apellidoP VARCHAR(30) NOT NULL,
    apellidoM VARCHAR(30) NOT NULL,
    sueldo DOUBLE NOT NULL,
    puesto INT(11) NOT NULL,
    fechaNac DATE NOT NULL,
    FOREIGN KEY (puesto) REFERENCES puesto(id)
);