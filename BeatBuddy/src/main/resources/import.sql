insert into users(id,email,name,surname,date_of_birth) values(nextval('users_seq'),'leo@prova.it','Leonardo','Fabbri','2002-11-25');
insert into credentials(id,username,password,role,user_id) values(nextval('credentials_seq'),'lf','$2a$12$1stsBZY328GxN2cciE9sHuWCQLzmYdZDLTOebs4atu6gAUtIFNKL6','DEFAULT',1);

--ALBUM--
insert into album(id, title, url_image) values(nextval('album_seq'), 'Happier than ever', '/images/a.jpg');
insert into album(id, title, url_image) values(nextval('album_seq'), 'The weekend', '/images/a2.jpg');
insert into album(id, title, url_image) values(nextval('album_seq'), 'Dua lipa', '/images/a3.jpg');

--ARTIST--
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'Billie Eilish', '/images/a.jpg');
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'The weekend', '/images/a2.jpg');
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'Dua lipa', '/images/a3.jpg');