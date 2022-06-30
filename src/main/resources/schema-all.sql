/*DROP TABLE people IF EXISTS;

CREATE TABLE people  (
    person_id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    first_name VARCHAR(20),
    last_name VARCHAR(20)
);*/

DROP TABLE IF EXISTS `products`;

CREATE TABLE `products`  (
                         id int(11) NOT NULL PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(1000),
                         price DOUBLE(16,2),
                         image VARCHAR (100)
);
