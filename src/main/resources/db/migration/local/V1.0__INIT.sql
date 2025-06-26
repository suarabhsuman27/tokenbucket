create table tenant(
	id uuid not null,
	name varchar(50) not null,
	primary key(id)
);

create table app_user(
	id uuid not null,
	username varchar(20) not null,
	name varchar(50) not null,
	tenant_id uuid not null,
	primary key(id)
);

create table employee
(
   id uuid not null,
   emp_id varchar (50) not null,
   name varchar (100) not null,
   address varchar (200) not null,
   salary integer not null,
   tenant_id uuid,
   primary key (id)
);

alter table if exists tenant 
    add constraint UK_5e8a2465r40f9t47856b006ad unique (name);
    
alter table if exists app_user 
    add constraint UK_5e8a2465r40f9t47856b006ef unique (username),
    add constraint FK_5e4r1467r40f2t76546b001ab foreign key (tenant_id) references tenant(id);
    
alter table if exists employee 
    add constraint UK_5e8a2465r40f9t47856b006gh unique (emp_id),
    add constraint FK_5e4r1467r40f2t76546b001ef foreign key (tenant_id) references tenant(id);