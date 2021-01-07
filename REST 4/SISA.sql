create database sisa;
use sisa;

create table usuario(
matricula varchar(25) not null,
nombre varchar(45) not null,
apellidoP varchar(45) not null,
apellidoM varchar(45) not null,
fechaNac date not null,
correo varchar(30) not null,
telefono varchar(20) not null,
constraint matricula primary key (matricula)
);

select * from usuario;

insert into usuario (matricula, nombre, apellidoP, apellidoM, fechaNac, correo, telefono) 
values ("20193tn054","Diego","Velasco","Gandara",'2001-03-23',"20193tn054@utez.edu.mx","777-661-03-73");
insert into usuario (matricula, nombre, apellidoP, apellidoM, fechaNac, correo, telefono) 
values ("20193tn037","Sebastian","Sota","Garcia",'2001-06-19',"ssota2001@gmail.com","777-560-30-66");
insert into usuario (matricula, nombre, apellidoP, apellidoM, fechaNac, correo, telefono) 
values ("20193tn094","Francisco","Guerrero","Rivera",'2000-10-04',"20193tn094@utez.edu.mx","777-112-61-97");
insert into usuario (matricula, nombre, apellidoP, apellidoM, fechaNac, correo, telefono) 
values ("20193tn109","Jean","Hernandez","Gonzalez",'2001-11-30',"20193tn109@utez.edu.mx","777-534-92-93");

SET GLOBAL time_zone = '-3:00';
/*
Usuario:
matrícula varchar 25
nombre varchar 45
apellido P varchar 45
apellido M varchar 45
fecha de nacimiento date (aaaa-mm-dd)
correo varchar 30
teléfono varchar 20
primary key (matricula) 
*/
