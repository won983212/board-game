create table players
(
    id        bigint auto_increment,
    name      varchar(64) null,
    password  varchar(255) null,
    user_role varchar(16) null,
    constraint players_pk
        primary key (id)
);

create unique index players_name_uindex
    on players (name);