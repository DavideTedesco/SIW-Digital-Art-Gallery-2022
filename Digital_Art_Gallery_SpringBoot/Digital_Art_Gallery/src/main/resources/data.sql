/*=========DIGITAL ART GALLERY==========*/
/*============ENTITY TABLES=============*/

/*Users*/
insert into users(id, nome, cognome ) values('1000','admin','admin');
insert into users(id, nome, cognome ) values('1001','user','user');

/*Credentials (le password sono: "password". Esse sono realizzate tramite il metodo encode della classe BCryptPasswordEncoder)*/
insert into credentials(id, password, role, username, user_id ) values('2000', '$2a$10$H90gDy3sDjHh64GqNIimseg0wifDVWpLpDednMiqWAUSae5SLwvzO' ,'ADMIN','admin', '1000');
insert into credentials(id, password, role, username, user_id ) values('2001', '$2a$10$H90gDy3sDjHh64GqNIimseg0wifDVWpLpDednMiqWAUSae5SLwvzO' ,'USER','user', '1001');

/*Autore
insert into autore(id, cognome, data_di_nascita, luogo_di_nascita, nome) values('100', 'Van Gogh', '2020-12-15', 'Toploinia', 'Vincent');*/

/*Collezione
insert into collezione(id, descrizione, nome) values('300', 'Piccola collezione', 'Grand Collection');*/

/*Opera
insert into opera(id, anno_di_realizzazione, descrizione, immagine, nome, autore_id,collezione_id) values('200', '2020-12-15','Una bella opera', 'https://www.analisidellopera.it/wp-content/uploads/2018/01/Van_Gogh_Girasoli_Amsterdam.jpg', 'I girasoli', '100','300');*/


/*============JOIN TABLES=============*/

/*autore_opere
insert into autore_opere(autore_id, opere_id) values('100', '200');*/

/*collezione_opere
insert into collezione_opere(collezione_id, opere_id) values('300', '200');*/

/*opera_autori
insert into opera_autori(opera_id, autori_id) values('200', '100');*/
