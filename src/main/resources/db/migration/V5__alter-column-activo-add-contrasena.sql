ALTER TABLE usuarios MODIFY estado tinyint;
ALTER TABLE usuarios ADD COLUMN contrasena varchar(300) not null;
