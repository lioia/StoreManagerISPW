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
       ('Jeans', 20, 15, 66, 'CLOTHES', 'New', 'Prixy', '44', 'Jeans'),
       ('Cardigan', 70, 55, 24, 'CLOTHES', 'New', 'Prixy', 'L', 'Lana'),
       ('Maglione', 45, 33, 12, 'CLOTHES', 'New', 'Prixy', '44', 'Cotone'),
       ('Gilet', 18, 9, 37, 'CLOTHES', 'New', 'Prixy', 'M', 'Poliammide'),
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
        'Romanzo'),
        ('1984',12.35,8.50,21,'BOOK','New','Prefelibro','George Orwell','Nel superstato di Oceania, la società è controllata da un Partito che basa il suo potere sui principi del Socing, un socialismo estremo, il cui comandante supremo è il Grande Fratello, misterioso dittatore il cui viso compare ovunque nei teleschermi e nei manifesti di propaganda.'
        ,'Romanzo'),
        ('La fattoria degli animali',16.75,12.99,15,'BOOK','New','Prefelibro','George Orwell','Gli animali si stufano di essere sotto il controllo degli umani, così quando il Vecchio Maggiore (maiale anziano a cui tutti danno ascolto) racconta il sogno di libertà, gli animali si ribellano sotto la guida di maiali. I due personaggi principali sono Palla di neve e Napoleon.'
         ,'Romanzo'),
        ('L''Africano',19.20,16.50,24,'BOOK','Used','Prefelibro','Santiago Posteguillo','Roma, 235 a.C. Il senatore Publio Cornelio Scipione, avido lettore di tragedie greche, è pronto ad assistere a una delle prime rappresentazioni teatrali messe in scena nella capitale. Non sa che quel giorno si sta compiendo la Storia. Perché poco dopo l''inizio dello spettacolo, un servo arriva a chiamarlo. È nato suo figlio: si chiamerà come lui, Publio Cornelio Scipione, ma sarà ricordato nei secoli a venire con un altro nome: l''Africano. L''uomo destinato a salvare l''Impero, e impedire che la civiltà romana venga cancellata.',
            'Romanzo storico'),
        ('Invicta Legio',22.30,18.20,12,'BOOK','New','Prefelibro','Santiago Posteguillo','In un momento terribile per la Repubblica, spetta a Publio Cornelio Scipione, l''Africano, il compito di proteggere i territori romani dalla minaccia più grande: quella di Cartagine. Così Quinto Fabio Massimo pianifica, in realtà, di disfarsi dell''Africano.',
        'Romanzo storico'),
        ('Il gioco del trono',22.50,22.50,24,'BOOK','New','Prefelibro','George R. R. Martin','Al di là della Barriera, tre ranger in pattuglia dei Guardiani della notte hanno uno scontro con un Estraneo, una creatura fatta interamente di ghiaccio e pericolosissima, ritenuta leggendaria e mai esistita: uno degli uomini viene ucciso dal mostro, il secondo uomo viene ucciso dal commilitone tornato in vita come non-morto, il terzo uomo scappa e diviene un disertore.',
        'Fantasy'),
        ('Eragon',16.30,16.30,12,'BOOK','Used','Prefelibro','Christopher Paolini','Eragon è un ragazzo di diciassette anni che vive con lo zio Garrow e il cugino Roran nel piccolo villaggio di Carvahall, situato nella Valle Palancar tra i monti della Grande Dorsale in Alagaësia. È coraggioso e abile nel cacciare e proprio durante una battuta di caccia sulla Dorsale dal nulla vede comparire con un lampo una grossa pietra blu cobalto davanti a sé. Con il passare del tempo Eragon capisce che in realtà la pietra è un uovo di drago e quando questo si schiude chiama la dragonessa Saphira. Dopo l''uccisione dello zio Garrow, Eragon e Saphira decidono di inseguire i Ra''zac per vendicarne la morte. In questo viaggio sono accompagnati da Brom, cantastorie del villaggio, grande conoscente di draghi, spade e magia.',
        'Fantasy'),
        ('Frankenstein',12.30,8.20,12,'BOOK','Used','Prefelibro','Mary Shelley','Victor Frankenstein è un giovane scienziato ginevrino, che, spinto dall''ardore della ricerca scientifica, trova il modo di creare la vita. Costruisce così una creatura umana con pezzi di cadaveri, ma è atterrito dalla mostruosità della sua creazione. ... Qui sposa Elizabeth, che il mostro uccide la sera stessa delle nozze.',
        'Fiction gotica'),
        ('Le notti di Salem',10.20,10.20,17,'BOOK','New','Prefelibro','Stephen King','Ben Mears, uno scrittore di successo cresciuto nell''immaginaria cittadina di Jerusalem''s Lot, Maine (chiamata "il Lot" dagli abitanti), torna nella città natale 25 anni dopo. Giunto in città, diventa amico dell''insegnante di liceo Matt Burke e intraprende una relazione sentimentale con Susan Norton, una giovane laureata. Ben inizia così a scrivere un libro su ''Casa Marsten'', una magione abbandonata che gli causò molti incubi dopo una brutta avventura vissuta da bambino: infatti per far parte di un gruppo di ragazzi doveva eseguire una prova di coraggio, ovvero entrare in casa Marsten riportando un oggetto che testimoniasse il suo atto di coraggio, solo che durante questa bravata vide il signor Hubert Marsten, detto Hubie, impiccato che lo fissava con gli occhi sgranati. Per lui questo fu uno shock terribile.',
            'Horror');

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
        'Nintendo Switch'),
       ('The Legend of Zelda', 54.50, 54.50, 28, 'VIDEOGAME', 'USEDLIKENew', 'Super Joystick',
        'Entra in un mondo di avventure. Immergiti in un mondo di scoperte ed esplorazione.', 'Adventure',
        'Nintendo Switch'),
       ('Demon''s Souls', 69.98, 49.98, 34, 'VIDEOGAME', 'New', 'Super Joystick',
        'Per conquistare il potere, il 12° Re di Boletaria, Re Allant, adoperò le antiche Arti delle anime, risvegliando un demone risalente all''alba dei tempi: l''Antico.',
        'RPG', 'PS5');
# Game Console Product
INSERT INTO Product(`Name`, `Price`, `DiscountedPrice`, `Quantity`, `Type`, `Status`, `Store`, `ConsoleType`,
                    `DigitalOnly`)
VALUES ('PS5 - Digital Edition', 399.98, 399.98, 23, 'GAMECONSOLE', 'New', 'Super Joystick', 'PS5', TRUE),
       ('PS5', 499.98, 499.98, 1, 'GAMECONSOLE', 'New', 'Super Joystick', 'PS5', FALSE),
       ('Nintendo Switch OLED', 349.98, 349.98, 25, 'GAMECONSOLE', 'New', 'Super Joystick', 'Nintendo Switch', FALSE);
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