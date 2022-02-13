SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";
USE `ShoppingPoint-DB`;

INSERT INTO User (`Username`, `Email`, `Password`, `Type`)
VALUES ('Andrea96', 'andre@gmail.com', 'password', 'CLIENT'),
       ('FrancescaRoma','franci@gmail.com', 'password', 'CLIENT'),
       ('Alessandro12','cole@hotmail.com', 'password', 'CLIENT'),
       ('NataleL','lello23@gmail.com', 'password', 'CLIENT'),
       ('Livio34','livio123@hotmail.com', 'password', 'CLIENT'),
       ('Gianni65','gianetta@gmail.com', 'password', 'CLIENT'),
       ('Ercole','ercole54@outlook.com', 'password', 'CLIENT'),
       ('Tulio','marcotul45@gmail.com', 'password', 'CLIENT'),
       ('Stefano31', 'stefanocante@gmail.com', 'password', 'CLIENT'),
       ('Barby21','barbara21@gmail.com', 'password', 'CLIENT'),
       ('BarbaraD','barbaradomi@hotmail.com', 'password', 'CLIENT'),
       ('Angela43','angelaV@gmail.com', 'password', 'CLIENT'),
       ('Antonio541','tony12@hotmail.com', 'password', 'CLIENT'),
       ('Giovanni74','giova97@gmail.com', 'password', 'CLIENT'),
       ('Giorgio24','giorgetto21@outlook.com', 'password', 'CLIENT'),
       ('Dario12','dario12@gmail.com', 'password', 'CLIENT'),
       ('FrancescoN', 'france@live.com', 'password', 'STOREOWNER'),
       ('Carmine64', 'rossiC64@gmail.com', 'password', 'STOREOWNER'),
       ('Fonty23', 'fontana58@live.com', 'password', 'STOREOWNER'),
       ('AlessioB54', 'alessio2000@gmail.com', 'password', 'STOREOWNER'),
       ('Giuseppe32', 'giuse43@live.com', 'password', 'STOREOWNER'),
       ('AlessandroL', 'ale3021@hotmail.com', 'password', 'STOREOWNER'),
       ('Lomba31', 'lomba65@live.com', 'password', 'STOREOWNER'),
       ('Valerio98', 'vale@outlook.com', 'password', 'STOREOWNER'),
       ('Cory', 'cory28@live.com', 'password', 'STOREOWNER'),
       ('BucciM41', 'bucci@gmail.com', 'password', 'STOREOWNER'),
       ('Fabiola74', 'faby43@live.com', 'password', 'STOREOWNER'),
       ('Vito', 'vittorio23@hotmail.com', 'password', 'STOREOWNER'),
       ('Valeria43', 'vale74@gmail.com', 'password', 'STOREOWNER'),
       ('Paolo54', 'palo63@gmail.com', 'password', 'STOREOWNER'),
       ('Polizzi23', 'poli123@outlook.com', 'password', 'SUPPLIER'),
       ('Visoni1876', 'visoni1876@gmail.com', 'password', 'SUPPLIER'),
       ('Barbara32', 'barbararossi@live.com', 'password', 'SUPPLIER'),
       ('AndreaF21', 'andrwe@outlook.com', 'password', 'SUPPLIER'),
       ('Franco72', 'franconeri@gmail.com', 'password', 'SUPPLIER'),
       ('Luca256', 'luca@live.com', 'password', 'SUPPLIER');

INSERT INTO Store (`Name`, `Address`, `Type`, `PointsInEuro`, `EuroInPoints`, `StoreOwner`)
VALUES ('Urbevest', 'Via Roma 1', 'CLOTHES', 10, 4, 'FrancescoN'),
       ('Il Paragrafo', 'Via Europa 2', 'BOOKS', 10, 4, 'Carmine64'),
       ('Easter Egg Stream', 'Via Tuscolana 3', 'VIDEOGAMES', 10, 2, 'Fonty23'),
       ('Sprint Wear', 'Via della Posta 11', 'CLOTHES', 10, 4, 'AlessioB54'),
       ('Comic World', 'Via del Comune 25', 'BOOKS', 10, 4, 'Giuseppe32'),
       ('Game Home', 'Via del Porticato 30', 'VIDEOGAMES', 10, 2, 'AlessandroL'),
       ('Uomo Tessile', 'Via Dei Ciliegi 24', 'CLOTHES', 10, 4, 'Lomba31'),
       ('Prefelibro', 'Via del Municipio 21', 'BOOKS', 10, 4, 'Valerio98'),
       ('Super Joystick', 'Via Adda 31', 'VIDEOGAMES', 10, 2, 'Cory'),
       ('WOWPC', 'Via Bazzani 43', 'ELECTRONICS', 10, 4, 'BucciM41'),
       ('Agilis Electro', 'Via Cavour 21', 'ELECTRONICS', 10, 4, 'Fabiola74'),
       ('La Bottega Elettronica', 'Via Enrico Fermi 32', 'ELECTRONICS', 10, 2, 'Vito');

INSERT INTO Store (`Name`, `Address`, `Type`, `StoreOwner`)
VALUES ('La Dedica', 'Via Garda 4', 'BOOKS', 'Valeria43'),
       ('Prixy', 'Via Giotto 16', 'CLOTHES', 'Paolo54');

# Clothes Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Size`, `Material`)
VALUES ('T-Shirt', 30, 20, 53, 'CLOTHES', 'New', 'Prixy', 'M', 'Cotton'),
       ('Jeans', 20, 15, 66, 'CLOTHES', 'New', 'Prixy', '44', 'Jeans');

# Shoes Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Size`, `Material`,
                    `ShoesType`)
VALUES ('Nike Air Force', 70, 50, 24, 'SHOES', 'New', 'Prixy', '42', 'Leather', 'Sneaker');

# Book Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Author`, `Plot`,
                    `Genre`)
VALUES ('Il pendolo di Foucault', 16, 15.20, 10, 'BOOK', 'New', 'Prefelibro', 'Umberto Eco',
        'Questo romanzo si svolge dall''inizio degli anni sessanta al 1984 tra una casa editrice milanese e un museo parigino dove è esposto il pendolo di Foucault. Si svolge dal 1943 al 1945 in un paesino tra Langhe e Monferrato. Si svolge tra il 1344 e il 2000 lungo il percorso del piano dei Templari e dei Rosa-Croce per la conquista del mondo. Si svolge interamente la notte del 23 giugno 1984, prima in piedi nella garitta del periscopio, poi in piedi nella garitta della statua della Libertà al Conservatoire des Arts et Métiers di Parigi. Si svolge la notte tra il 26 e il 27 giugno dello stesso anno nella stessa casa di campagna che Jacopo Belbo, il protagonista, ha ereditato da suo zio Carlo, mentre Pim rievoca le sequenze temporali di cui si è detto sopra. In sintesi: tre redattori editoriali, a Milano, dopo avere frequentato troppo a lungo autori "a proprie spese" che si dilettano di scienze occulte, società segrete e complotti cosmici, decidono di inventare, senza alcun senso di responsabilità, un Piano. Ma qualcuno li prende sul serio.',
        'Romanzo');

# Comics Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Author`, `Artist`,
                    `Plot`, `Genre`, `VolumeNumber`)
VALUES ('Dragon Ball', 6.90, 6.40, 20, 'COMICS', 'New', 'Prefelibro', 'Akira Toriyama', 'Akira Toriyama',
        'Il primo volume vede Goku agli esordi della sua storia, dall’incontro con Bulma al principio dell’avventurosa ricerca delle leggendarie Sfere del Drago...',
        'Battle Shonen', 1),
       ('Dragon Ball', 6.90, 6.40, 15, 'COMICS', 'New', 'Prefelibro', 'Akira Toriyama', 'Akira Toriyama',
        'Goku, Bulma e Oolong proseguono la loro ricerca delle Sfere del Drago, incontrando amici e rivali sul proprio cammino. Chi sarà il temibile capo della Banda del Coniglio? Riusciranno i nostri amici a batterlo? ',
        'Battle Shonen', 2);

# Video Game Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `Plot`, `Genre`,
                    `ConsoleType`)
VALUES ('The Legend of Zelda', 59.98, 59.98, 23, 'VIDEOGAME', 'New', 'Super Joystick',
        'Entra in un mondo di avventure. Immergiti in un mondo di scoperte ed esplorazione.', 'Adventure',
        'NINTENDOSWITCH'),
       ('The Legend of Zelda', 54.50, 54.50, 28, 'VIDEOGAME', 'USEDLIKENew', 'Super Joystick',
        'Entra in un mondo di avventure. Immergiti in un mondo di scoperte ed esplorazione.', 'Adventure',
        'NINTENDOSWITCH'),
       ('Demon''s Souls', 69.98, 49.98, 34, 'VIDEOGAME', 'New', 'Super Joystick',
        'Per conquistare il potere, il 12° Re di Boletaria, Re Allant, adoperò le antiche Arti delle anime, risvegliando un demone risalente all''alba dei tempi: l''Antico.',
        'RPG', 'PS5');
# Game Console Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `ConsoleType`,
                    `DigitalOnly`)
VALUES ('PS5 - Digital Edition', 399.98, 399.98, 23, 'GAMECONSOLE', 'New', 'Super Joystick', 'PS5', TRUE),
       ('PS5', 499.98, 499.98, 1, 'GAMECONSOLE', 'New', 'Game Store', 'PS5', FALSE),
       ('Nintendo Switch OLED', 349.98, 349.98, 25, 'GAMECONSOLE', 'New', 'Super Joystick', 'NINTENDOSWITCH', FALSE);
# Computer Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `ComputerType`, `Ram`,
                    `SSD`, `BatterySize`, `CPU`, `GPU`, `Brand`, `DisplaySize`)
VALUES ('Vivobook S15', 899.98, 799.98, 15, 'COMPUTER', 'New', 'Agilis Electro', 'LAPTOP', 16, 512, 2560,
        'AMD Ryzen 7 4700u', 'Integrated AMD', 'ASUS', 15.6),
       ('Surface Book Pro7', 1399, 1199, 10, 'COMPUTER', 'New', 'Agilis Electro', 'TWOINONE', 8, 256, 2560,
        'Intel Core i5-1035G4', 'INTEL Iris Plus Graphics', 'Microsoft', 12.3);

# Home Appliance Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `EnergyClass`, `Specs`)
VALUES ('Lavatrice LG F4WV310S6E', 1249, 473, 10, 'HOMEAPPLIANCES', 'New', 'Agilis Electro', 'B',
        'Carico: 10.5kg. Velocità: 1360 giri/min');

INSERT INTO Loyaltycard(`Client`,`Store`,`Points`)
VALUES ('FrancescaRoma','Prefelibro','54'),
       ('Alessandro12','Prefelibro','36'),
       ('NataleL','Prefelibro','31'),
       ('Livio34','Prefelibro','75'),
       ('Gianni65','Prefelibro','32'),
       ('Ercole','Prefelibro','129'),
       ('Tulio','Prefelibro','230'),
       ('Stefano31', 'Prefelibro','13'),
       ('Barby21','Prefelibro','260'),
       ('BarbaraD','Prefelibro','158'),
       ('Angela43','Prefelibro','34'),
       ('Antonio541','Prefelibro','76'),
       ('Giovanni74','Prefelibro','21'),
       ('Giorgio24','Prefelibro','58'),
       ('Dario12','Prefelibro','22'),
       ('FrancescaRoma','Agilis Electro','54'),
       ('Alessandro12','Agilis Electro','36'),
       ('NataleL','Agilis Electro','31'),
       ('Livio34','Agilis Electro','75'),
       ('Gianni65','Agilis Electro','32'),
       ('Ercole','Agilis Electro','129'),
       ('Tulio','Agilis Electro','230'),
       ('Stefano31', 'Agilis Electro','13'),
       ('Barby21','Agilis Electro','260'),
       ('BarbaraD','Agilis Electro','158'),
       ('Angela43','Agilis Electro','34'),
       ('Antonio541','Agilis Electro','76'),
       ('Giovanni74','Agilis Electro','21'),
       ('Giorgio24','Agilis Electro','58'),
       ('Dario12','Agilis Electro','22');