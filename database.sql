DROP DATABASE IF EXISTS forum;

CREATE DATABASE forum CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;

USE forum;

CREATE TABLE role(
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE user(
    id INT NOT NULL AUTO_INCREMENT,
    login VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    registration_date DATE NOT NULL,
    last_connection TIMESTAMP NOT NULL,
    is_online BOOLEAN NOT NULL,
    role_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (role_id) REFERENCES role (id)
);

CREATE TABLE thread(
    id INT NOT NULL AUTO_INCREMENT,
    title VARCHAR(255) NOT NULL,
    publication_date TIMESTAMP NOT NULL,
    last_update TIMESTAMP NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

CREATE TABLE tag(
    id INT NOT NULL AUTO_INCREMENT,
    label VARCHAR(255) NOT NULL UNIQUE,
    PRIMARY KEY (id)
);

CREATE TABLE thread_tag(
    thread_id INT NOT NULL,
    tag_id INT NOT NULL,
    PRIMARY KEY (thread_id, tag_id),
    FOREIGN KEY (thread_id) REFERENCES thread (id) ON DELETE CASCADE,
    FOREIGN KEY (tag_id) REFERENCES tag (id) ON DELETE CASCADE
);

CREATE TABLE post(
    id INT NOT NULL AUTO_INCREMENT,
    content VARCHAR(255) NOT NULL,
    publication_date TIMESTAMP NOT NULL,
    last_update TIMESTAMP NOT NULL,
    thread_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (thread_id) REFERENCES thread (id) ON DELETE CASCADE,
    FOREIGN KEY (user_id) REFERENCES user (id) ON DELETE CASCADE
);

INSERT INTO role (name)
VALUES ('USER');

INSERT INTO user (login, email, password, registration_date, last_connection, is_online, role_id)
VALUES ('Ryu', 'ryu@gmail.com', 'test', CURDATE(), NOW()-SECOND(17), false, 1);

INSERT INTO user (login, email, password, registration_date, last_connection, is_online, role_id)
VALUES ('Ken', 'ken@gmail.com', 'test', CURDATE(), NOW()-SECOND(17), false, 1);

INSERT INTO user (login, email, password, registration_date, last_connection, is_online, role_id)
VALUES ('Chun-Li', 'chun-li@gmail.com', 'test', CURDATE(), NOW()-SECOND(17), false, 1);

INSERT INTO user (login, email, password, registration_date, last_connection, is_online, role_id)
VALUES ('Guile', 'guile@gmail.com', 'test', CURDATE(), NOW()-SECOND(17), false, 1);

INSERT INTO user (login, email, password, registration_date, last_connection, is_online, role_id)
VALUES ('Cammy', 'cammy@gmail.com', 'test', CURDATE(), NOW()-SECOND(17), false, 1);

INSERT INTO user (login, email, password, registration_date, last_connection, is_online, role_id)
VALUES ('Dhaslim', 'dhaslim@gmail.com', 'test', CURDATE(), NOW()-SECOND(17), false, 1);

INSERT INTO user (login, email, password, registration_date, last_connection, is_online, role_id)
VALUES ('Honda', 'honda@gmail.com', 'test', CURDATE(), NOW()-SECOND(17), false, 1);

INSERT INTO thread (title, publication_date, last_update, user_id)
VALUES ('Comment lancer un Hadoken', NOW()-SECOND(16), NOW()-SECOND(16), 1);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Voulez-vous un tutoriel pour apprendre à faire un Hadoken ?",  NOW()-SECOND(16), NOW()-SECOND(16), 1, 1);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Non",  NOW()-SECOND(15), NOW()-SECOND(15), 1, 2);

INSERT INTO thread (title, publication_date, last_update, user_id)
VALUES ('Tutoriel Hadoken pour les nuls', NOW()-SECOND(14), NOW()-SECOND(14), 2);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Hadoken",  NOW()-SECOND(14), NOW()-SECOND(14), 2, 2);

INSERT INTO thread (title, publication_date, last_update, user_id)
VALUES ('Tuto Kikoken', NOW()-SECOND(13), NOW()-SECOND(13), 3);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Qui aimerait apprendre ?",  NOW()-SECOND(13), NOW()-SECOND(13), 3, 3);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Moi",  NOW()-SECOND(12), NOW()-SECOND(12), 3, 7);

INSERT INTO thread (title, publication_date, last_update, user_id)
VALUES ('Yoga', NOW()-SECOND(11), NOW()-SECOND(11), 6);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Yoga",  NOW()-SECOND(11), NOW()-SECOND(11), 4, 6);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Yoga",  NOW()-SECOND(10), NOW()-SECOND(10), 4, 2);

INSERT INTO thread (title, publication_date, last_update, user_id)
VALUES ('Suis-je plus fort que Ryu ?', NOW()-SECOND(9), NOW()-SECOND(9), 2);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("À votre avis ?",  NOW()-SECOND(9), NOW()-SECOND(9), 5, 2);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Non",  NOW()-SECOND(8), NOW()-SECOND(8), 5, 7);

INSERT INTO thread (title, publication_date, last_update, user_id)
VALUES ('Honda doit-il faire un régime ?', NOW()-SECOND(7), NOW()-SECOND(7), 2);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("À votre avis ?",  NOW()-SECOND(7), NOW()-SECOND(7), 6, 2);

INSERT INTO thread (title, publication_date, last_update, user_id)
VALUES ('Organisation tournoi', NOW()-SECOND(6), NOW()-SECOND(6), 1);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Voudriez-vous y participer ?",  NOW()-SECOND(6), NOW()-SECOND(6), 7, 1);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Bien sûr!",  NOW()-SECOND(5), NOW(), 7, 2);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Partant",  NOW()-SECOND(4), NOW()-SECOND(4), 7, 7);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Partante",  NOW()-SECOND(3), NOW()-SECOND(3), 7, 3);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Yoga",  NOW()-SECOND(2), NOW()-SECOND(2), 7, 6);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Ok",  NOW()-SECOND(1), NOW()-SECOND(1), 7, 4);

INSERT INTO post (content, publication_date, last_update, thread_id, user_id)
VALUES ("Ready",  NOW(), NOW(), 7, 5);

INSERT INTO tag (label)
VALUES ('Tutoriel');

INSERT INTO tag (label)
VALUES ('Hadoken');

INSERT INTO tag (label)
VALUES ('Kikoken');

INSERT INTO tag (label)
VALUES ('Yoga');

INSERT INTO tag (label)
VALUES ('Question');

INSERT INTO tag (label)
VALUES ('Honda');

INSERT INTO tag (label)
VALUES ('Tournoi');

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (1, 1);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (1, 2);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (2, 1);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (2, 2);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (3, 1);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (3, 3);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (4, 4);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (5, 5);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (6, 5);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (6, 6);

INSERT INTO thread_tag (thread_id, tag_id)
VALUES (7, 7);