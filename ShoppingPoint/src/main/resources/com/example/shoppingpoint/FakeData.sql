SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
USE `ShoppingPoint-DB`;

INSERT INTO User (`Username`, `Email`, `Password`, `Type`)
VALUES ('client', 'client_test@example.com', 'password', 'CLIENT'),
       ('storeowner_1', 'storowner_1@example.com', 'password', 'STOREOWNER'),
       ('storeowner_2', 'storowner_2@example.com', 'password', 'STOREOWNER'),
       ('storeowner_3', 'storowner_3@example.com', 'password', 'STOREOWNER'),
       ('storeowner_4', 'storowner_4@example.com', 'password', 'STOREOWNER'),
       ('supplier', 'supplier_test@example.com', 'password', 'SUPPLIER');

INSERT INTO Store (`Name`, `Address`, `Type`, `PointsInEuro`, `StoreOwner`)
VALUES ('Clothes Store', 'Via Finta 1', 'CLOTHES', 5, 'storeowner_1'),
       ('Book Store', 'Via Finta 2', 'BOOKS', 10, 'storeowner_2'),
       ('Game Store', 'Via Finta 3', 'VIDEOGAMES', 10, 'storeowner_3'),
       ('Electronics Store', 'Via Finta 4', 'ELECTRONICS', 5, 'storeowner_4');

# Clothes Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Size`, `Material`)
VALUES ('T-Shirt', 30, 20, 5, 'CLOTHES', 'NEW', 'Clothes Store', 'M', 'Cotton'),
       ('Jeans', 20, 15, 10, 'CLOTHES', 'NEW', 'Clothes Store', '44', 'Jeans');

# Shoes Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Size`, `Material`,
                    `ShoesType`)
VALUES ('Nike Air Force', 70, 50, 2, 'SHOES', 'NEW', 'Clothes Store', '42', 'Leather', 'Sneaker');

# Book Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Author`, `Plot`,
                    `Genre`)
VALUES ('Il pendolo di Foucault', 16, 15.20, 10, 'BOOK', 'NEW', 'Book Store', 'Umberto Eco',
        'Questo romanzo si svolge dall''inizio degli anni sessanta al 1984 tra una casa editrice milanese e un museo parigino dove è esposto il pendolo di Foucault. Si svolge dal 1943 al 1945 in un paesino tra Langhe e Monferrato. Si svolge tra il 1344 e il 2000 lungo il percorso del piano dei Templari e dei Rosa-Croce per la conquista del mondo. Si svolge interamente la notte del 23 giugno 1984, prima in piedi nella garitta del periscopio, poi in piedi nella garitta della statua della Libertà al Conservatoire des Arts et Métiers di Parigi. Si svolge la notte tra il 26 e il 27 giugno dello stesso anno nella stessa casa di campagna che Jacopo Belbo, il protagonista, ha ereditato da suo zio Carlo, mentre Pim rievoca le sequenze temporali di cui si è detto sopra. In sintesi: tre redattori editoriali, a Milano, dopo avere frequentato troppo a lungo autori "a proprie spese" che si dilettano di scienze occulte, società segrete e complotti cosmici, decidono di inventare, senza alcun senso di responsabilità, un Piano. Ma qualcuno li prende sul serio.',
        'Romanzo');

# Comics Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Author`, `Artist`,
                    `Plot`, `Genre`, `VolumeNumber`)
VALUES ('Dragon Ball', 6.90, 6.40, 20, 'COMICS', 'NEW', 'Book Store', 'Akira Toriyama', 'Akira Toriyama',
        'Il primo volume vede Goku agli esordi della sua storia, dall’incontro con Bulma al principio dell’avventurosa ricerca delle leggendarie Sfere del Drago...',
        'Battle Shonen', 1),
       ('Dragon Ball', 6.90, 6.40, 15, 'COMICS', 'NEW', 'Book Store', 'Akira Toriyama', 'Akira Toriyama',
        'Goku, Bulma e Oolong proseguono la loro ricerca delle Sfere del Drago, incontrando amici e rivali sul proprio cammino. Chi sarà il temibile capo della Banda del Coniglio? Riusciranno i nostri amici a batterlo? ',
        'Battle Shonen', 2);

# Video Game Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Plot`, `Genre`,
                    `ConsoleType`)
VALUES ('The Legend of Zelda: Breath of The Wild', 59.98, 59.98, 5, 'VIDEOGAME', 'NEW', 'Game Store',
        'Entra in un mondo di avventure. Immergiti in un mondo di scoperte ed esplorazione.', 'Adventure',
        'NINTENDOSWITCH'),
       ('The Legend of Zelda: Breath of The Wild', 54.50, 54.50, 2, 'VIDEOGAME', 'USEDLIKENEW', 'Game Store',
        'Entra in un mondo di avventure. Immergiti in un mondo di scoperte ed esplorazione.', 'Adventure',
        'NINTENDOSWITCH'),
       ('Demon\'s Souls', 69.98, 49.98, 3, 'VIDEOGAME', 'NEW', 'Game Store',
        'Per conquistare il potere, il 12° Re di Boletaria, Re Allant, adoperò le antiche Arti delle anime, risvegliando un demone risalente all''alba dei tempi: l''Antico.',
        'RPG', 'PS5');
# Game Console Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `ConsoleType`,
                    `DigitalOnly`)
VALUES ('PS5 - Digital Edition', 399.98, 399.98, 1, 'GAMECONSOLE', 'NEW', 'Game Store', 'PS5', TRUE),
       ('PS5', 499.98, 499.98, 1, 'GAMECONSOLE', 'NEW', 'Game Store', 'PS5', FALSE),
       ('Nintendo Switch OLED', 349.98, 349.98, 25, 'GAMECONSOLE', 'NEW', 'Game Store', 'NINTENDOSWITCH', FALSE);
# Computer Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `ComputerType`, `Ram`,
                    `SSD`, `BatterySize`, `CPU`, `GPU`, `Brand`, `DisplaySize`)
VALUES ('Vivobook S15', 899.98, 799.98, 5, 'COMPUTER', 'NEW', 'Electronics Store', 'LAPTOP', 16, 512, 2560,
        'AMD Ryzen 7 4700u', 'Integrated AMD', 'ASUS', 15.6),
       ('Surface Book Pro7', 1399, 1199, 10, 'COMPUTER', 'NEW', 'Electronics Store', 'TWOINONE', 8, 256, 2560,
        'Intel Core i5-1035G4', 'INTEL Iris Plus Graphics', 'Microsoft', 12.3);

# Home Appliance Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `EnergyClass`, `Specs`)
VALUES ('Lavatrice LG F4WV310S6E', 1249, 473, 10, 'HOMEAPPLIANCES', 'NEW', 'Electronics Store', 'B',
        'Carico: 10.5kg. Velocità: 1360 giri/min');
