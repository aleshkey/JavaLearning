create table cars (
    condition smallint,
    mark varchar(255),
    model varchar(255),
    year_of_release integer,
    ad_id integer not null,
    owner_id integer,
    primary key (ad_id)
);


create table images (
    id serial not null,
    url varchar(255),
    car_id integer,
    primary key (id)
);

create table phones (
    id serial not null,
    number varchar(255),
    owner_id integer,
    primary key (id)
);
create table users (
    id serial not null,
    email varchar(255),
    name varchar(255),
    primary key (id)
);

create table ads(
    id serial not null,
    date_of_last_update varchar(30),
    date_of_creation varchar(30),
    primary key (id)
);

alter table
    if exists cars
        add constraint ad_id_fk foreign key (ad_id) references ads;

alter table
    if exists cars
        add constraint user_id_fk_for_cars foreign key (owner_id) references users;

alter table
    if exists images
        add constraint car_id_fk foreign key (car_id) references cars;

alter table
    if exists phones
        add constraint user_id_fk_for_phones foreign key (owner_id) references users;