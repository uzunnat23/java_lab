CREATE SCHEMA `appliances` ;

CREATE TABLE `appliances`.`categories` (
  `id_categories` INT NOT NULL AUTO_INCREMENT,
  `name_categories` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_categories`),
  UNIQUE INDEX `id_categories_UNIQUE` (`id_categories` ASC) VISIBLE);

ALTER TABLE `appliances`.`categories`
CHANGE COLUMN `id_categories` `id_category` INT NOT NULL AUTO_INCREMENT ,
CHANGE COLUMN `name_categories` `name_category` VARCHAR(45) NOT NULL ;

CREATE TABLE `appliances`.`manufacturers` (
  `id_manufacturer` INT NOT NULL AUTO_INCREMENT,
  `name_manufacturer` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_manufacturer`),
  UNIQUE INDEX `id_manufacturer_UNIQUE` (`id_manufacturer` ASC) VISIBLE);

CREATE TABLE `appliances`.`products` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `name_product` VARCHAR(45) NOT NULL,
  `id_manufacturer` INT NOT NULL,
  `id_category` INT NOT NULL,
  PRIMARY KEY (`id_product`),
  UNIQUE INDEX `id_product_UNIQUE` (`id_product` ASC) VISIBLE,
  INDEX `id_manufacturer_fk_idx` (`id_manufacturer` ASC) VISIBLE,
  INDEX `id_category_fk_idx` (`id_category` ASC) VISIBLE,
  CONSTRAINT `id_manufacturer_fk`
    FOREIGN KEY (`id_manufacturer`)
    REFERENCES `appliances`.`manufacturers` (`id_manufacturer`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `id_category_fk`
    FOREIGN KEY (`id_category`)
    REFERENCES `appliances`.`categories` (`id_category`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

INSERT INTO `appliances`.`categories` (`id_category`, `name_category`) VALUES ('1', 'Computer equipment');
INSERT INTO `appliances`.`categories` (`id_category`, `name_category`) VALUES ('2', 'Office and stationery');
INSERT INTO `appliances`.`categories` (`id_category`, `name_category`) VALUES ('3', 'Small household appliances');

INSERT INTO `appliances`.`manufacturers` (`id_manufacturer`, `name_manufacturer`) VALUES ('1', 'KitchenAid');
INSERT INTO `appliances`.`manufacturers` (`id_manufacturer`, `name_manufacturer`) VALUES ('2', 'HP');
INSERT INTO `appliances`.`manufacturers` (`id_manufacturer`, `name_manufacturer`) VALUES ('3', 'Asus');
INSERT INTO `appliances`.`manufacturers` (`id_manufacturer`, `name_manufacturer`) VALUES ('4', 'Dewal');

INSERT INTO `appliances`.`products` (`id_product`, `name_product`, `id_manufacturer`, `id_category`) VALUES ('1', 'Video card', '3', '1');
INSERT INTO `appliances`.`products` (`id_product`, `name_product`, `id_manufacturer`, `id_category`) VALUES ('2', 'Laptop', '2', '1');
INSERT INTO `appliances`.`products` (`id_product`, `name_product`, `id_manufacturer`, `id_category`) VALUES ('3', 'Hair dryer', '4', '3');
INSERT INTO `appliances`.`products` (`id_product`, `name_product`, `id_manufacturer`, `id_category`) VALUES ('4', 'Coffee Maker', '1', '3');
INSERT INTO `appliances`.`products` (`id_product`, `name_product`, `id_manufacturer`, `id_category`) VALUES ('5', 'Headphones', '3', '1');
INSERT INTO `appliances`.`products` (`id_product`, `name_product`, `id_manufacturer`, `id_category`) VALUES ('6', 'Hair straightener', '4', '3');

ALTER TABLE `appliances`.`products`
ADD COLUMN `price` INT NULL AFTER `name_product`,
CHANGE COLUMN `id_category` `id_category` INT NOT NULL AFTER `price`;

UPDATE `appliances`.`products` SET `price` = '5000' WHERE (`id_product` = '1');
UPDATE `appliances`.`products` SET `price` = '7000' WHERE (`id_product` = '2');
UPDATE `appliances`.`products` SET `price` = '500' WHERE (`id_product` = '3');
UPDATE `appliances`.`products` SET `price` = '1000' WHERE (`id_product` = '4');
UPDATE `appliances`.`products` SET `price` = '300' WHERE (`id_product` = '5');
UPDATE `appliances`.`products` SET `price` = '550' WHERE (`id_product` = '6');
