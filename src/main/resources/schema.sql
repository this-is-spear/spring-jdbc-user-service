create table users (
    seq bigint not null auto_increment,
    email varchar(50) not null,
    password varchar(80) not null,
    create_at datetime not null default current_timestamp(),
    primary key (seq),
    constraint unq_user_email unique (email)
);

