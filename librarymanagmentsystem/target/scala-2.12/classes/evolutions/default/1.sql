# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table author (
  name                          varchar(255) not null,
  no_of_books                   varchar(255),
  constraint pk_author primary key (name)
);

create table book (
  isbn                          varchar(255) not null,
  title                         varchar(255),
  sector                        varchar(255),
  publication_date_day          integer,
  current_reader_name           varchar(255),
  publisher                     varchar(255),
  pages                         integer not null,
  constraint uq_book_publication_date_day unique (publication_date_day),
  constraint pk_book primary key (isbn)
);

create table book_author (
  book_isbn                     varchar(255) not null,
  author_name                   varchar(255) not null,
  constraint pk_book_author primary key (book_isbn,author_name)
);

create table date_time (
  day                           integer auto_increment not null,
  month                         integer not null,
  year                          integer not null,
  constraint pk_date_time primary key (day)
);

create table reader (
  name                          varchar(255) not null,
  id                            varchar(255),
  mobile_number                 varchar(255),
  email                         varchar(255),
  constraint pk_reader primary key (name)
);

alter table book add constraint fk_book_publication_date_day foreign key (publication_date_day) references date_time (day) on delete restrict on update restrict;

alter table book add constraint fk_book_current_reader_name foreign key (current_reader_name) references reader (name) on delete restrict on update restrict;
create index ix_book_current_reader_name on book (current_reader_name);

alter table book_author add constraint fk_book_author_book foreign key (book_isbn) references book (isbn) on delete restrict on update restrict;
create index ix_book_author_book on book_author (book_isbn);

alter table book_author add constraint fk_book_author_author foreign key (author_name) references author (name) on delete restrict on update restrict;
create index ix_book_author_author on book_author (author_name);


# --- !Downs

alter table book drop foreign key fk_book_publication_date_day;

alter table book drop foreign key fk_book_current_reader_name;
drop index ix_book_current_reader_name on book;

alter table book_author drop foreign key fk_book_author_book;
drop index ix_book_author_book on book_author;

alter table book_author drop foreign key fk_book_author_author;
drop index ix_book_author_author on book_author;

drop table if exists author;

drop table if exists book;

drop table if exists book_author;

drop table if exists date_time;

drop table if exists reader;

