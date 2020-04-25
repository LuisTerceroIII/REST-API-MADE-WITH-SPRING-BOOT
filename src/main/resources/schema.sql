create table Category (
"id" serial not null primary key,
"name" VARCHAR (30)
);
create table Product (
"id" serial not null primary key,
id_category serial not null,
brand varchar (30),
"name" varchar (30),
"unity" varchar(30),
quantity int,
foreign key (id_category) references Category("id")
);
create table MovementType (
"id" serial not null primary key,
"name" varchar (30)
);
create table Movement (
"id" serial not null primary key,
id_movement_type serial not null,
id_product serial  not null,
quantity int,
price_unit int,
"date" date,
total int,
foreign key (id_movement_type) references MovementType("id"),
foreign key (id_product) references product("id")
);









