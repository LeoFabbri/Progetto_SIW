insert into users(id,email,name,surname,date_of_birth) values(nextval('users_seq'),'leo@prova.it','Leonardo','Fabbri','2002-11-25');
insert into credentials(id,username,password,role,user_id) values(nextval('credentials_seq'),'lf','$2a$12$1stsBZY328GxN2cciE9sHuWCQLzmYdZDLTOebs4atu6gAUtIFNKL6','DEFAULT',1);

--ALBUM--
insert into album(id, title, url_image) values(nextval('album_seq'), 'Happier than ever', '/images/a.jpg');
insert into album(id, title, url_image) values(nextval('album_seq'), 'StarBoy', '/images/a2.jpg');
insert into album(id, title, url_image) values(nextval('album_seq'), 'Dua lipa', '/images/a3.jpg');

insert into album(id, title, url_image) values(nextval('album_seq'), 'Happier than ever', '/images/a.jpg');
insert into album(id, title, url_image) values(nextval('album_seq'), 'The weekend', '/images/a2.jpg');
insert into album(id, title, url_image) values(nextval('album_seq'), 'Dua lipa', '/images/a3.jpg');

--ARTIST--
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'Billie Eilish', '/images/a.jpg');
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'The weekend', '/images/a2.jpg');
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'Dua lipa', '/images/a3.jpg');

insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'Billie Eilish', '/images/a.jpg');
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'The weekend', '/images/a2.jpg');
insert into artist(id, stage_name, url_image) values(nextval('artist_seq'), 'Dua lipa', '/images/a3.jpg');

-- SONGS --
INSERT INTO song(id, title, pubblication_date, album_id, duration, number_of_plays, audio_url) VALUES(nextval('song_seq'), 'Cazzo', '2002-02-02', 1, 150, 150, '/audio/1.mp3');
INSERT INTO song(id, title, pubblication_date, album_id, duration, number_of_plays) VALUES(nextval('song_seq'), 'Figa', '2002-02-02', 1, 140, 150);
INSERT INTO song(id, title, pubblication_date, album_id, duration, number_of_plays) VALUES(nextval('song_seq'), 'Culo', '2002-02-02', 1, 130, 150);
INSERT INTO song(id, title, pubblication_date, album_id, duration, number_of_plays) VALUES(nextval('song_seq'), 'Cacca', '2002-02-02', 1, 120, 150);
INSERT INTO song(id, title, pubblication_date, album_id, duration, number_of_plays) VALUES(nextval('song_seq'), 'Piscia', '2002-02-02', 1, 110, 150);
INSERT INTO song(id, title, pubblication_date, album_id, duration, number_of_plays) VALUES(nextval('song_seq'), 'Borra', '2002-02-02', 1, 100, 150);

