create table User_Auth
(
 id decimal IDENTITY(1,1) NOT NULL,  
 user_login varchar (30) NOT NULL,  
 user_password varchar(30) NOT NULL,  
 CONSTRAINT  PK_ID_User PRIMARY KEY (id)
 );

create table Secret_Data
(
 s_id decimal IDENTITY(1,1) NOT NULL,  
 s_description varchar (50),
 s_login varchar (30),
 s_password varchar (30),  
 s_user_id decimal NOT NULL,  
 CONSTRAINT  PK_ID_Data PRIMARY KEY (s_id),
 CONSTRAINT FK_User FOREIGN KEY (s_user_id) REFERENCES User_Auth (id)
);