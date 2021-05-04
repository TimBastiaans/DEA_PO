/*==============================================================*/
/* DBMS name:      Microsoft SQL Server 2016                    */
/* Created on:     24-Mar-20 18:26:24                           */
/* Database:       SpotitubeTest                                */
/*==============================================================*/
CREATE SCHEMA IF NOT EXISTS SpotitubeTest;

USE SpotitubeTest;

/*==============================================================*/
/* Table: OWNER                                                 */
/*==============================================================*/
CREATE TABLE user (
                    user varchar(255) NOT NULL,
                    password varchar(255) NOT NULL,
                    token varchar(14) NOT NULL PRIMARY KEY,
                    firstname varchar(255) NOT NULL,
                    lastname varchar(255) NOT NULL
);

/*==============================================================*/
/* Table: TRACK                                                 */
/*==============================================================*/
CREATE TABLE playlists (
                         id int(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,
                         name varchar(255) NOT NULL,
                         owner_token varchar(14) NOT NULL,
                         length int(11) NOT NULL,
                         CONSTRAINT fk_owner_token FOREIGN KEY (owner_token) REFERENCES user (token)  ON UPDATE CASCADE ON DELETE RESTRICT
);

/*==============================================================*/
/* Table: SONG                                                  */
/*==============================================================*/
CREATE TABLE tracks (
                      id int(11) AUTO_INCREMENT PRIMARY KEY NOT NULL,
                      title varchar(255) NOT NULL,
                      performer varchar(255) NOT NULL,
                      duration int(11) NOT NULL,
                      album varchar(255) NOT NULL,
                      playcount int(11) DEFAULT NULL,
                      publicationDate date DEFAULT NULL,
                      description varchar(255) DEFAULT NULL,
                      offlineAvailable boolean NOT NULL
);

/*==============================================================*/
/* Table: VIDEO                                                 */
/*==============================================================*/
CREATE TABLE tracksInPlaylists(
                                playlistId int(11) NOT NULL,
                                trackId int(11) NOT NULL,
                                PRIMARY KEY (playlistId,trackId),
                                CONSTRAINT fk_playlistId FOREIGN KEY (playlistId) REFERENCES playlists (id) ON UPDATE CASCADE ON DELETE CASCADE,
                                CONSTRAINT fk_trackId FOREIGN KEY (trackId) REFERENCES tracks (id)  ON UPDATE CASCADE ON DELETE CASCADE
);


/*------------------------------------------------------------------ */

INSERT INTO user (user, password,  token, firstname, lastname) VALUES
('meron', 'meron', '0890-bva2-he7d', 'Meron', 'Brouwer'),
('dennis', 'dennis', '0891-bva2-he7d', 'Dennis', 'Breuker'),
('uwe', 'uwe',  '0892-bva2-he7d', 'Uwe', 'van Heesch');

INSERT INTO playlists (name, owner_token, length) VALUES
('Death metal', '0890-bva2-he7d', 0),
('pop', '0890-bva2-he7d', 0);

INSERT INTO tracks (title, performer, duration, album, playcount, publicationDate, description, offlineAvailable) VALUES
('Ocean and a rock', 'Lisa Hannigan',  337, 'Sea sew',  0,null,null, false),
('So Long, Marianne', 'Leonard Cohen',  546, 'Songs of Leonard Cohen',  0,null,null, false),
('One', 'Metallica',  423, 'Sea sew',  37, '2001-11-01','Long version', true);

INSERT INTO tracksInPlaylists (playlistId, trackId) VALUES
(1, 1),
(1, 2),
(2, 1),
(2, 3);