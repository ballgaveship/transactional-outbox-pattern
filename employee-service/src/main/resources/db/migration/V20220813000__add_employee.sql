create table Employee
(
    "ID"            bigint       not null primary key auto_increment,
    "STATUS"        varchar(50)  not null,
    "USERNAME"      varchar(100) not null,
    "EMAIL"         varchar(100) not null,
    "FIRST_NAME"    varchar(100) null,
    "LAST_NAME"     varchar(100) null,
    "DISPLAY_NAME"  varchar(200) null,
    "DEPT"          varchar(100) null,
    "PHONE_NUMBER"  varchar(100) null,
    "CREATED_DATE"  datetime not null,
    "MODIFIED_DATE" datetime null
);