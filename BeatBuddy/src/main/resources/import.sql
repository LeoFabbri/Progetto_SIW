insert into users(id,email,name,surname,date_of_birth) values(nextval('users_seq'),'leo@prova.it','Leonardo','Fabbri','2002-11-25');
insert into credentials(id,username,password,role,user_id) values(nextval('credentials_seq'),'lf','$2a$12$1stsBZY328GxN2cciE9sHuWCQLzmYdZDLTOebs4atu6gAUtIFNKL6','DEFAULT',1);
insert into artist(id,stage_name) values(nextval('artist_seq'),'Pippo');
insert into credentials(id,username,password,role,artist_id) values(nextval('credentials_seq'),'PippoArtist','$2a$12$1stsBZY328GxN2cciE9sHuWCQLzmYdZDLTOebs4atu6gAUtIFNKL6','ARTIST',1);

insert into song(id,title,duration,pubblication_date,number_of_plays,image_path) values(nextval('song_seq'),'Zitti e buoni',180,'2021-11-25',1500000,'/images/zittiEBuoni.jpeg');
insert into song(id,title,duration,pubblication_date,number_of_plays,image_path) values(nextval('song_seq'),'Beggin',240,'2017-10-20',200000,'/images/beggin.jpeg');
insert into song(id,title,duration,pubblication_date,number_of_plays,image_path) values(nextval('song_seq'),'Coraline',210,'2021-03-11',220000,'/images/Coraline.jpeg');