-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET UTF8MB4 ;
-- -----------------------------------------------------
-- Schema seraphia
-- -----------------------------------------------------
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`user`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(50) NOT NULL,
  `last_name` VARCHAR(100) NOT NULL,
  `email` VARCHAR(50) NOT NULL,
  `phone` VARCHAR(20) NOT NULL,
  `password` VARCHAR(30) NOT NULL,
  `role` ENUM('admin', 'client') NOT NULL,
  `data_register` DATETIME NULL,
  `street` VARCHAR(50) NULL,
  `num_int` VARCHAR(10) NULL,
  `num_ext` VARCHAR(10) NULL,
  `suburb` VARCHAR(100) NULL,
  `zip_code` VARCHAR(10) NULL,
  `city` VARCHAR(100) NULL,
  `state` VARCHAR(100) NULL,
  PRIMARY KEY (`id_user`),
  INDEX `email_UNIQUE` (`email` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`category`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`category` (
  `id_category` INT NOT NULL AUTO_INCREMENT,
  `category_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_category`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`color`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`color` (
  `id_color` INT NOT NULL AUTO_INCREMENT,
  `color_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_color`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`size`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`size` (
  `id_size` INT NOT NULL AUTO_INCREMENT,
  `size_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_size`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`product`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`product` (
  `id_product` INT NOT NULL AUTO_INCREMENT,
  `id_color` INT NOT NULL,
  `id_size` INT NOT NULL,
  `id_category` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `description` VARCHAR(500) NOT NULL,
  `price` DECIMAL(10,2) NOT NULL,
  `stock` TINYINT NOT NULL,
  `creation_date` DATE NOT NULL,
  `is_available` BIT(1) NOT NULL DEFAULT 1,
  PRIMARY KEY (`id_product`, `id_color`, `id_size`, `id_category`),
  INDEX `id_category_idx` (`id_category` ASC) VISIBLE,
  INDEX `id_color_idx` (`id_color` ASC) VISIBLE,
  INDEX `id_size_idx` (`id_size` ASC) VISIBLE,
  CONSTRAINT `fk_id_category`
    FOREIGN KEY (`id_category`)
    REFERENCES `mydb`.`category` (`id_category`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_color`
    FOREIGN KEY (`id_color`)
    REFERENCES `mydb`.`color` (`id_color`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_id_size`
    FOREIGN KEY (`id_size`)
    REFERENCES `mydb`.`size` (`id_size`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cart`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cart` (
  `id_cart` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  `date_creation` DATETIME NULL,
  `date_modification` DATETIME NULL,
  PRIMARY KEY (`id_cart`, `id_user`),
  INDEX `fk_cart_user1_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_cart_user`
    FOREIGN KEY (`id_user`)
    REFERENCES `mydb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`cartItem`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`cartItem` (
  `id_cartItem` INT NOT NULL AUTO_INCREMENT,
  `id_product` INT NOT NULL,
  `id_cart` INT NOT NULL,
  PRIMARY KEY (`id_cartItem`, `id_product`, `id_cart`),
  INDEX `id_product_idx` (`id_product` ASC) VISIBLE,
  INDEX `id_cart_idx` (`id_cart` ASC) VISIBLE,
  CONSTRAINT `fk_cartItem_cart`
    FOREIGN KEY (`id_cart`)
    REFERENCES `mydb`.`cart` (`id_cart`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_cartItem_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`image`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`image` (
  `id_image` INT NOT NULL AUTO_INCREMENT,
  `id_product` INT NOT NULL,
  `image_url` MEDIUMTEXT NOT NULL,
  `order` INT NOT NULL,
  PRIMARY KEY (`id_image`, `id_product`),
  INDEX `id_product_idx` (`id_product` ASC) VISIBLE,
  CONSTRAINT `fk_id_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`status`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`status` (
  `id_status` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `description` VARCHAR(100) NULL,
  PRIMARY KEY (`id_status`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`inventory`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`inventory` (
  `id_inventory` INT NOT NULL AUTO_INCREMENT,
  `id_product` INT NOT NULL,
  `id_status` INT NOT NULL,
  `serie` VARCHAR(45) NOT NULL,
  `income_date` VARCHAR(45) NOT NULL,
  `outcome_date` VARCHAR(45) NULL,
  PRIMARY KEY (`id_inventory`, `id_product`, `id_status`),
  INDEX `id_product_idx` (`id_product` ASC) VISIBLE,
  INDEX `id_status_idx` (`id_status` ASC) VISIBLE,
  CONSTRAINT `fk_inventory_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_inventory_status`
    FOREIGN KEY (`id_status`)
    REFERENCES `mydb`.`status` (`id_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`order` (
  `id_order` INT NOT NULL AUTO_INCREMENT,
  `id_user` INT NOT NULL,
  `net_sale` DECIMAL(10,2) NOT NULL,
  `order_date` DATE NOT NULL,
  PRIMARY KEY (`id_order`, `id_user`),
  INDEX `fk_order_iduser_idx` (`id_user` ASC) VISIBLE,
  CONSTRAINT `fk_order_iduser`
    FOREIGN KEY (`id_user`)
    REFERENCES `mydb`.`user` (`id_user`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`orderStatus`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`orderStatus` (
  `id_order_status` INT NOT NULL AUTO_INCREMENT,
  `status` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_order_status`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`orderDetail`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`orderDetail` (
  `id_order_detail` INT NOT NULL AUTO_INCREMENT,
  `id_product` INT NOT NULL,
  `id_order` INT NOT NULL,
  `id_order_status` INT NOT NULL,
  PRIMARY KEY (`id_order_detail`, `id_product`, `id_order`, `id_order_status`),
  INDEX `fk_orderDetail_product_idx` (`id_product` ASC) VISIBLE,
  INDEX `fk_orderDetaill_order_idx` (`id_order` ASC) VISIBLE,
  INDEX `fk_orderDetail_orderStatus1_idx` (`id_order_status` ASC) VISIBLE,
  CONSTRAINT `fk_orderDetail_product`
    FOREIGN KEY (`id_product`)
    REFERENCES `mydb`.`product` (`id_product`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderDetaill_order`
    FOREIGN KEY (`id_order`)
    REFERENCES `mydb`.`order` (`id_order`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_orderDetail_orderStatus`
    FOREIGN KEY (`id_order_status`)
    REFERENCES `mydb`.`orderStatus` (`id_order_status`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
