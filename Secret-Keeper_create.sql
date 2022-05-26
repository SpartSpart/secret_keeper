drop database SecretsDB;


create database SecretsDB;

Begin

create table Users
(
 id decimal IDENTITY(1,1) NOT NULL,  
 user_login varchar (30) NOT NULL,  
 user_password varchar(30) NOT NULL,  
 user_email varchar(30) NOT NULL,
 CONSTRAINT  PK_ID_Users PRIMARY KEY (id)
 );

create table Secrets
(
 id decimal IDENTITY(1,1) NOT NULL,  
 "description" varchar (50),
 login varchar (30),
 "password" varchar (30),  
 "user_id" decimal NOT NULL,  
 CONSTRAINT  PK_ID_Data_Secrets PRIMARY KEY (id),
 CONSTRAINT FK_User_Secrets FOREIGN KEY ("user_id") REFERENCES "Users" (id)
);

create table Notes
(
 id decimal IDENTITY(1,1) NOT NULL,  
 "note" varchar,
 "user_id" decimal NOT NULL,  
 CONSTRAINT  PK_ID_Data_Note PRIMARY KEY (id),
 CONSTRAINT FK_User_Notes FOREIGN KEY ("user_id") REFERENCES "Users" (id)
);


create table Docs
(
 id decimal IDENTITY(1,1) NOT NULL,  
 "document" varchar,
 "description" varchar (30),
 "user_id" decimal NOT NULL,  
 CONSTRAINT  PK_ID_Data_Docs PRIMARY KEY (id),
 CONSTRAINT FK_User_Docs FOREIGN KEY ("user_id") REFERENCES "Users" (id)
);


create table Files
(
 id decimal IDENTITY(1,1) NOT NULL,  
 "file_name" varchar (30),
 "doc_id" decimal, 
 "file_path" varchar,
 CONSTRAINT  PK_ID_Data_File PRIMARY KEY (id)
);
end;

