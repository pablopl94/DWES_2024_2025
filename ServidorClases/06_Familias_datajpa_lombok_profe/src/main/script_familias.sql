create database familias_bbdd;
use familias_bbdd;

create table familias
(id_familia int not null auto_increment primary key,
nombre varchar(45) not null
);

insert into familias values
(1,'Ropa'), (2,'Bebidas'),(3,'Ferretería');
create table Productos
( id_producto dec(13) not null primary key,
descripcion varchar(100),
precio double,
stock int,
fecha_alta date,
id_familia int not null,
foreign key(id_familia) references familias(id_familia)
);

insert into productos values
(1001, 'Camisa verde', 23,50,'2022-07-07',1),
(1002, 'Camisa azul', 34,100,'2022-07-07',1),
(2001, 'Mirinda de Naranja', 1,456,'1976-07-07',2),
(2002, 'Fanta de limon', 2,678,'2024-09-07',2);

create table usuarios
(username varchar(45) not null primary key,
password varchar(45) not null,
nombre varchar(45),
apellidos varchar(45),
email varchar(45) not null,
created_at date,
enabled int
);
insert into usuarios values
('tomas', 'tomasin', 'Tomas', 'Escudero', 'tomas@fp.es', '2022-07-07',1),
('eva', 'evita', 'Eva', 'Goma', 'eva@fp.es', '2022-07-07',1),
('sara', 'sarita', 'Sara', 'Varas', 'sara@fp.es', '2022-07-07',1),
('ramon', 'ramoncin', 'Ramon', 'Gutierrez', 'ramon@fp.es', '2022-07-07',1);












