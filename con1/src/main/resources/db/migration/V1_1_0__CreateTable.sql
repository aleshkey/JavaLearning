CREATE TABLE people(
  id int primary key generated always as identity,
  name varchar(30),
  value int
);

create table card (
  owner int references people(id),
  number varchar
);