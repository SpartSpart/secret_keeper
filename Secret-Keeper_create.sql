create table Users
(
 id decimal IDENTITY(1,1) NOT NULL,  
 user_login varchar (30) NOT NULL,  
 user_password varchar(30) NOT NULL,  
 CONSTRAINT  PK_ID_User PRIMARY KEY (id)
 );

create table Secrets
(
 id decimal IDENTITY(1,1) NOT NULL,  
 "description" varchar (50),
 login varchar (30),
 "password" varchar (30),  
 "user_id" decimal NOT NULL,  
 CONSTRAINT  PK_ID_Data PRIMARY KEY (id),
 CONSTRAINT FK_User FOREIGN KEY ("user_id") REFERENCES "User" (id)
);