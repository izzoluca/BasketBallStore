DROP DATABASE IF EXISTS  basket;
CREATE DATABASE basket;
USE basket;



CREATE TABLE admin(
  NomeUtente varchar(20) NOT NULL,
  Pass varchar(15) NOT NULL,
  PRIMARY KEY (NomeUtente)
);


CREATE TABLE Prodotti (
  Id int(11) NOT NULL,
  immagine varchar(1000) NOT NULL,
  Descrizione varchar(500) NOT NULL,
  Prezzo double DEFAULT NULL,
  Taglia varchar(6) NOT NULL,
  Disponibilità int(11) NOT NULL,
  PRIMARY KEY (Id)
);

CREATE TABLE cliente (
  NomeUtenteCliente varchar(15) NOT NULL,
  PassCliente varchar(15) NOT NULL,
  Nome varchar(20) NOT NULL,
  Cognome varchar(15) NOT NULL,
  Email varchar(30) NOT NULL,
  PRIMARY KEY (NomeUtenteCliente)
);

CREATE TABLE gestisce (
  UserAdmin varchar(20) NOT NULL,
  IdProdotti int(11) DEFAULT NULL,
  KEY UserAdmin (UserAdmin),
  KEY IdProdotti (IdProdotti),
  FOREIGN KEY (UserAdmin) REFERENCES admin (NomeUtente),
  FOREIGN KEY (IdProdotti) REFERENCES prodotti (Id) ON UPDATE CASCADE
);


CREATE TABLE sceglie (
  UserCliente varchar(15) NOT NULL,
  IdProdottiUno int(11) DEFAULT NULL,
  
  FOREIGN KEY (UserCliente) REFERENCES cliente (NomeUtenteCliente),
  FOREIGN KEY (IdProdottiUno) REFERENCES Prodotti (Id) ON UPDATE CASCADE
);





