SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
USE `ShoppingPoint-DB`;

INSERT INTO User (`Username`, `Email`, `Password`, `Type`)
VALUES ('client_test', 'client_test@example.com', 'password', 'CLIENT'),
       ('storeowner_test', 'storowner_test@example.com', 'password', 'STOREOWNER'),
       ('supplier_test', 'supplier_test@example.com', 'password', 'SUPPLIER');

INSERT INTO Store (`Name`, `Address`, `Type`, `PointsInEuro`, `StoreOwner`)
VALUES ('ClothesStore', 'Via Finta 1', 'CLOTHES', 5, 'storeowner_test');

INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Size`, `Material`, `Store`)
VALUES ('T-Shirt', 30, 20, 5, 'CLOTHES', 'NEW', 'M', 'Cotton', 'ClothesStore'),
       ('Jeans', 20, 15, 10, 'CLOTHES', 'NEW', '40', 'Jeans', 'ClothesStore');
