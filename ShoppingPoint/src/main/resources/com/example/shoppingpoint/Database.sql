SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

CREATE DATABASE IF NOT EXISTS `ShoppingPoint-DB` DEFAULT CHARACTER SET UTF8MB4;
USE `ShoppingPoint-DB`;

CREATE TABLE User (
    Username VARCHAR(16) PRIMARY KEY,
    Email VARCHAR(48) NOT NULL UNIQUE,
    Password VARCHAR(20) NOT NULL,
    Type ENUM("CLIENT", "STOREOWNER", "SUPPLIER") NOT NULL
);

CREATE TABLE Store (
    Name VARCHAR(50) PRIMARY KEY,
    Address VARCHAR(25) NOT NULL,
    Type ENUM("CLOTHES", "BOOKS", "VIDEOGAMES", "ELECTRONICS") NOT NULL,
    PointsInEuro INT,
    EuroInPoints INT,

    StoreOwner VARCHAR(16) NOT NULL,
    FOREIGN KEY (StoreOwner) REFERENCES User(Username) ON DELETE CASCADE
);

CREATE TABLE Product (
    ProductId INT PRIMARY KEY AUTO_INCREMENT,
    Name VARCHAR(50) NOT NULL,
    Price FLOAT NOT NULL,
    DiscountedPrice FLOAT NOT NULL,
    Quantity INT NOT NULL,
    Type ENUM("CLOTHES", "SHOES", "BOOK", "COMICS", "VIDEOGAME", "GAMECONSOLE", "COMPUTER", "HOMEAPPLIANCES") NOT NULL,
    Status ENUM("NEW", "USED", "USEDLIKENEW", "REGENERATED") NOT NULL,
    Image BLOB,
    Size VARCHAR(16),
    Material VARCHAR(16),
    ShoesType VARCHAR(16),
    Author VARCHAR(25),
    Artist VARCHAR(25),
    Plot TEXT,
    Genre VARCHAR(16),
    VolumeNumber INT,
    ConsoleType ENUM("PS5", "PS4", "XBOXSERIESX", "XBOXSERIESS", "XBOXONE", "NINTENDOSWITCH"),
    DigitalOnly TINYINT(1), # TODO change in BIT
    ComputerType ENUM("LAPTOP", "DESKTOP", "TWOINONE", "TOUCHSCREEN"),
    Ram INT,
    SSD INT,
    CPU VARCHAR(25),
    GPU VARCHAR(25),
    BatterySize INT,
    DisplaySize FLOAT,
    Brand VARCHAR(16),
    EnergyClass VARCHAR(4),
    Specs TEXT,

    Store VARCHAR(50) NOT NULL,
    FOREIGN KEY (Store) REFERENCES Store(Name) ON DELETE CASCADE
);

CREATE TABLE SoldProduct (
    SoldProductId INT PRIMARY KEY AUTO_INCREMENT,
    Quantity INT NOT NULL,
    Date DATE NOT NULL,

    ProductId INT NOT NULL,
    FOREIGN KEY (ProductId) REFERENCES Product(ProductId) ON DELETE CASCADE,

    Client VARCHAR(16) NOT NULL,
    FOREIGN KEY (Client) REFERENCES User(Username)
);

CREATE TABLE LoyaltyCard (
    Client VARCHAR(16) NOT NULL,
    Store VARCHAR(16) NOT NULL,
    Points INTEGER NOT NULL,

    FOREIGN KEY (Client) REFERENCES User(Username),
    FOREIGN KEY (Store) REFERENCES Store(Name),
    PRIMARY KEY (Client, Store)
);

CREATE TABLE Review (
    ReviewId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ProductId INT NOT NULL,
    Client VARCHAR(16) NOT NULL,
    Value FLOAT,

    FOREIGN KEY (ProductId) REFERENCES Product(ProductId),
    FOREIGN KEY (Client) REFERENCES User(Username)
);

CREATE TABLE Request (
    RequestId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    ProductId INT NOT NULL,
    MaxPrice FLOAT NOT NULL,
    Quantity INT NOT NULL,
    Accepted BIT NOT NULL,

    FOREIGN KEY (ProductId) REFERENCES Product(ProductId)
);

CREATE TABLE Offer (
    OfferId INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    Supplier VARCHAR(16) NOT NULL,
    RequestId INT NOT NULL,
    OfferPrice FLOAT NOT NULL,
    Accepted BIT NOT NULL,

    FOREIGN KEY (RequestId) REFERENCES Request(RequestId),
    FOREIGN KEY (Supplier) REFERENCES User(Username)
)
