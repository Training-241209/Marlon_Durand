drop table if exists users;
drop table if exists roles;
drop table if exists reimbursement;
create table user (
    usersId int primary key auto_increment,
    username varchar(255) not null unique,
    password varchar(255),
    role int
);
create table reimbursement (
    id int primary key auto_increment,
    amount float,
    description varchar(255),
    status varchar not null,
    foreign key (users) references users(usersId)
);

create table roles (
    role_id int primary key auto_increment,
    role int,
    foreign key (users) references users(usersId)
);

-- Starting test values with ids of 9999 to avoid test issues
insert into users values (9999, 'testuser1', 'password', 1);
insert into users values (9998, 'testuser2', 'password', 2);
insert into users values (9997, 'testuser3', 'password', 1);
insert into users values (9996, 'testuser4', 'password', 1);

insert into reimbursement values (9999, 51415153,'test message 1','Pending',9999);
insert into reimbursement values (9997, 51515125,'test message 2','Approved',9997);
insert into reimbursement values (9996, 9125251996,'test message 3','Denied',9996);

insert into users values (9999,1, 9999);
insert into users values (9998,2, 9998);
insert into users values (9997,1, 9997);
insert into users values (9996,1, 9996);