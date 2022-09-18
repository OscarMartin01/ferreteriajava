#create schema ferreteria;
use ferreteria;

create table objetos(
codigo int primary key not null,
nombre varchar(30) not null,
peso int not null,
categoria enum("Cerrajería","Cintas","Herramientas","Herrajes","Ropa y Accesorios de Seguridad","Señalizaciones","Fijación y Tornillería","Pinturas y Complementos","Mobiliario Jardín / Playa / Camping / Piscinas","Material Eléctrico"),
precio double(9,2) not null,
cantidad int not null
);