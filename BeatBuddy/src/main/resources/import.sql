insert into users(id,email,name,surname,date_of_birth) values(nextval('users_seq'),'leo@prova.it','Leonardo','Fabbri','2002-11-25');
insert into credentials(id,username,password,role,user_id) values(nextval('credentials_seq'),'lf','$2a$12$1stsBZY328GxN2cciE9sHuWCQLzmYdZDLTOebs4atu6gAUtIFNKL6','DEFAULT',1);
insert into artist(id,stage_name,real_name,data_di_nascita,biography) values(nextval('artist_seq'),'Renato Tre','Renato Fiacchini','1950-09-30','er goat');
insert into artist(id,stage_name,real_name,data_di_nascita,biography) values(nextval('artist_seq'),'Lazza','Jacopo Lazzarini','1994-08-22','zzala');
insert into artist(id,stage_name,real_name,data_di_nascita,biography) values(nextval('artist_seq'),'Post Malone','Austin Richard Post','1995-07-04','posty');
insert into credentials(id,username,password,role,artist_id) values(nextval('credentials_seq'),'rt','$2a$12$l7qQAec52Uay4JsLIlGsxeptCkczRWmKCN/aOePRbOptDTM7fwaY6','ARTIST',1);
