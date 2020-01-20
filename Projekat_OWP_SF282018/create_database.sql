CREATE TABLE IF NOT EXISTS korisnik (
ID INTEGER NOT NULL PRIMARY KEY, 
kor_Ime VARCHAR(30) UNIQUE,
lozinka VARCHAR(30) UNIQUE,
datum_Reg DATETIME NOT NULL,
uloga VARCHAR(20) NOT NULL);


CREATE TABLE IF NOT EXISTS karta (
ID INTEGER  PRIMARY KEY,
projekcijaID VARCHAR(30),
sediste INTEGER NOT NULL,
datum_Prod DATETIME NOT NULL,
korID INTEGER NOT NULL,
FOREIGN KEY (korID)
    references korisnik (ID),
FOREIGN KEY(projekcijaID)
    references projekcija (ID) 
);


CREATE TABLE IF NOT EXISTS projekcija (
ID INTEGER PRIMARY KEY,
filmID INTEGER NOT NULL,
tip_pro VARCHAR(5) NOT NULL,
sala INTEGER NOT NULL,
dat_prikaz DATETIME NOT NULL,
cena_karte MONEY NOT NULL CHECK(cena_karte > 0),
adminID INTEGER NOT NULL,
FOREIGN KEY (filmID)
    references film(ID),
FOREIGN KEY (adminID)
    references korisnik(ID)
);


CREATE TABLE IF NOT EXISTS film (
ID INTEGER PRIMARY KEY,
naziv VARCHAR(40) NOT NULL,
reziser VARCHAR(30) NOT NULL,
trajanje VARCHAR(15) NOT NULL,
distributer VARCHAR(40) NOT NULL,
zemlja_por VARCHAR(30) NOT NULL,
god_proiz INTEGER(30) NOT NULL
);


create table student (
    br_ind      CHAR(6)	Primary Key,
    prezime     char(20)    not null,
    ime         char(20)    not null,
    godstud     char(3)     not null);
    
    
    
    
    
INSERT INTO korisnik VALUES (1,'korisnickoIme1', 'lozinka1', datetime('now'), 'Administrator');
INSERT INTO korisnik VALUES (2,'korisnickoIme2' , 'lozinka2' , datetime('now'), 'Korisnik');

INSERT INTO film VALUES (1, 'Gospodar prstenova', 'Piter Jackson', '3h 50min', 'New Line Cinema', 'Ujedinjeno Kraljevstvo', 2001);
INSERT INTO film VALUES (2, '3 metra iznad neba', 'Fernando Gonzales Morina', '2h', 'Warner Bros', 'Spanija', 2010);