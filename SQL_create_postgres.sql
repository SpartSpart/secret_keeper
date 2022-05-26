create database SecretsDB;

create table Users
(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    user_login varchar (30) NOT NULL,
    user_password varchar(30) NOT NULL,
    user_email varchar(30) NOT NULL,
    CONSTRAINT  PK_ID_Users PRIMARY KEY (id)
);

create table Secrets
(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    "description" varchar (50),
    login varchar (30),
    "password" varchar (30),
    "user_id" bigint NOT NULL,
    CONSTRAINT  PK_ID_Data_Secrets PRIMARY KEY (id),
    CONSTRAINT FK_User_Secrets FOREIGN KEY ("user_id") REFERENCES Users (id)
);

create table Notes
(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    "note" varchar,
    "user_id" bigint NOT NULL,
    CONSTRAINT  PK_ID_Data_Note PRIMARY KEY (id),
    CONSTRAINT FK_User_Notes FOREIGN KEY ("user_id") REFERENCES Users (id)
);


create table Docs
(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    "document" varchar,
    "description" varchar (30),
    "user_id" bigint NOT NULL,
    CONSTRAINT  PK_ID_Data_Docs PRIMARY KEY (id),
    CONSTRAINT FK_User_Docs FOREIGN KEY ("user_id") REFERENCES Users (id)
);


create table Files
(
    id bigint GENERATED ALWAYS AS IDENTITY NOT NULL,
    "file_name" varchar (30),
    "doc_id" decimal,
    "file_path" varchar,
    CONSTRAINT  PK_ID_Data_File PRIMARY KEY (id)
);