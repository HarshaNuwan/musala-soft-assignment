-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema gateways_assignment_schema
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema gateways_assignment_schema
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `gateways_assignment_schema` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `gateways_assignment_schema` ;

-- -----------------------------------------------------
-- Table `gateways_assignment_schema`.`device`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gateways_assignment_schema`.`device` (
  `uid` VARCHAR(255) NOT NULL,
  `vendor` VARCHAR(100) NOT NULL,
  `date` DATE NULL DEFAULT NULL,
  `status` TINYINT NULL DEFAULT NULL,
  PRIMARY KEY (`uid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gateways_assignment_schema`.`gateway`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gateways_assignment_schema`.`gateway` (
  `serial_number` VARCHAR(10) NOT NULL,
  `human_readable_name` VARCHAR(100) NULL DEFAULT NULL,
  `ipv4_address` VARCHAR(15) NULL DEFAULT NULL,
  PRIMARY KEY (`serial_number`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `gateways_assignment_schema`.`gateway_device_asoc`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `gateways_assignment_schema`.`gateway_device_asoc` (
  `gateway_device_asoc_id` INT NOT NULL AUTO_INCREMENT,
  `serial_number` VARCHAR(10) NULL DEFAULT NULL,
  `uid` VARCHAR(255) NULL DEFAULT NULL,
  PRIMARY KEY (`gateway_device_asoc_id`),
  INDEX `uid_idx` (`uid` ASC) VISIBLE,
  INDEX `serial_number_idx` (`serial_number` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
