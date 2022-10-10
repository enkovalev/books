create table book
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    title       varchar(150) not null,
    author      varchar(150) not null,
    description varchar(150)
);

insert into book (title, author, description) VALUES ('Crime and Punishment', 'F. Dostoevsky', null);
insert into book (title, author, description) VALUES ('Anna Karenina', 'L. Tolstoy', null);
insert into book (title, author, description) VALUES ('The Brothers Karamazov', 'F. Dostoevsky', null);
insert into book (title, author, description) VALUES ('War and Peace', 'L. Tolstoy', null);
insert into book (title, author, description) VALUES ('Dead Souls', 'N. Gogol', null);