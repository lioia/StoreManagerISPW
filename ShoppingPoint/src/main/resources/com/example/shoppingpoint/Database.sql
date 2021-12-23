SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `ShoppingPoint-DB` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;
USE `ShoppingPoint-DB`;

CREATE TABLE User (
    `Username` VARCHAR(16) PRIMARY KEY,
    `Email` VARCHAR(48) NOT NULL UNIQUE,
    `Password` VARCHAR(20) NOT NULL,
    `Type` ENUM("Client", "StoreOwner", "Supplier") NOT NULL
)
