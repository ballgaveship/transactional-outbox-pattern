create table Outbox
(
    "ID"             bigint       not null primary key auto_increment,
    "AGGREGATE_TYPE" varchar(50)  not null,
    "AGGREGATE_ID"   varchar(100) not null,
    "EVENT_TYPE"     varchar(100) not null,
    "PAYLOAD"        json not null
);

CREATE SEQUENCE outbox_seq START WITH 1 INCREMENT BY 50;