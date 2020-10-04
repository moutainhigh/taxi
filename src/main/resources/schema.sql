create table if not exists t_user
(
    id       bigint      not null auto_increment primary key,
    username varchar(32) not null,
    password varchar(16) not null,
    phone    varchar(16) not null
);

create table if not exists t_driver
(
    id bigint not null auto_increment primary key ,
    username varchar(32) not null,
    password varchar(16) not null,
    phone    varchar(16) not null,
    carId bigint
);

create table if not exists t_car
(
    id bigint not null auto_increment primary key ,
    plateNumber varchar(16) not null,
    model varchar(32) not null ,
    color varchar(8) not null
);

create table  if not exists t_reservation
(
    id bigint not null auto_increment primary key ,
    userId bigint not null ,
    start_lng double not null,
    start_lat double not null ,
    end_lng double not null,
    end_lat double not null ,
    startAt DATE not null ,
    num int not null ,
    isDispatched boolean not null
);

create table  if not exists t_dispatcher
(
    id bigint not null auto_increment primary key ,
    driverId bigint not null ,
    node1 bigint not null ,
    node2 bigint not null ,
    node3 bigint not null ,
    node4 bigint not null ,
    node5 bigint not null ,
    node6 bigint not null
);

create table  if not exists t_order
(
    id bigint not null auto_increment primary key ,
    userId bigint not null ,
    driverId bigint not null ,
    start_lng double not null,
    start_lat double not null ,
    end_lng double not null,
    end_lat double not null ,
    startAt DATE not null ,
    finishedAt DATE not null ,
    num int not null
);

alter table t_driver
    add foreign key (carId) references t_car(id);

alter table t_reservation
    add foreign key (userId) references t_user(id);

alter table t_dispatcher
    add foreign key (driverId) references t_driver(id);
alter table t_dispatcher
    add foreign key (node1) references t_reservation(id);
alter table t_dispatcher
    add foreign key (node2) references t_reservation(id);
alter table t_dispatcher
    add foreign key (node3) references t_reservation(id);
alter table t_dispatcher
    add foreign key (node4) references t_reservation(id);
alter table t_dispatcher
    add foreign key (node5) references t_reservation(id);
alter table t_dispatcher
    add foreign key (node6) references t_reservation(id);

alter table t_order
    add foreign key (userId) references t_user(id);
alter table t_order
    add foreign key (driverId) references t_driver(id);