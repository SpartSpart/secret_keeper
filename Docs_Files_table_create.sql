create table Docs
(
 id decimal IDENTITY(1,1) NOT NULL,  
 document varchar (100),
 description varchar (200),
 "user_id" decimal NOT NULL,  
 CONSTRAINT  PK_ID_Docs PRIMARY KEY (id),
 CONSTRAINT FK_Doc_User FOREIGN KEY ("user_id") REFERENCES "Users" (id)
);
create table Files
(
 id decimal IDENTITY(1,1) NOT NULL,
 "file" varbinary (MAX),
 doc_id decimal NOT NULL,
 CONSTRAINT  PK_ID_Files PRIMARY KEY (id),
 CONSTRAINT FK_Doc FOREIGN KEY ("doc_id") REFERENCES "Docs" (id)
);