create table Users
(
 id decimal IDENTITY(1,1) NOT NULL,  
 user_login varchar (30) NOT NULL,  
 user_password varchar(30) NOT NULL,
 user_email varchar(50) NOT NULL,    
 CONSTRAINT  PK_ID_User PRIMARY KEY (id)
 );

create table Type
(
id decimal IDENTITY(1,1) NOT NULL,
 "type" varchar (30) NOT NULL
 CONSTRAINT  PK_ID_Type PRIMARY KEY (id)
)

create table Appointment
(
 id decimal IDENTITY(1,1) NOT NULL,
 "appointment" varchar (30) NOT NULL
 CONSTRAINT  PK_ID_Appointment PRIMARY KEY (id)
)

create table Drugs
(
 id decimal IDENTITY(1,1) NOT NULL,  
 "name" varchar (100) NOT NULL,
 "type_id" decimal NOT NULL,
 "count" varchar (10) NOT NULL,
 "appointment_id" decimal NOT NULL,  
 "date" Date NOT NULL,  
 "user_id" decimal NOT NULL,

 CONSTRAINT  PK_ID_Data PRIMARY KEY (id),
 CONSTRAINT FK_User FOREIGN KEY ("user_id") REFERENCES "Users" (id),
 CONSTRAINT FK_Types FOREIGN KEY ("type_id") REFERENCES "Type" (id),
 CONSTRAINT FK_Appointment FOREIGN KEY ("appointment_id") REFERENCES "Appointment" (id)
);