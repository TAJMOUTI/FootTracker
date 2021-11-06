CREATE TABLE Team (
   idTeam INT PRIMARY KEY,
   name VARCHAR (30) NOT NULL,
);

CREATE TABLE Game (
    idGame INT PRIMARY KEY,
    idTeam1 INT,
    idTeam2 INT,
    dateGame DATETIME,
    localisation VARCHAR (50),
    FOREIGN KEY (idTeam1) REFERENCES Team(idTeam),
    FOREIGN KEY (idTeam2) REFERENCES Team(idTeam),
);

CREATE TABLE Statistics (
    idStats INT PRIMARY KEY,
    idGame INT,
    idTeam INT,
    nbButs INT,
    cartonsRouges INT,
    cartonsJaunes INT,
    penaltys INT,
    coupsFrancs INT,
    horsjeu INT,

    FOREIGN KEY (idGame) REFERENCES Game(idGame)
    FOREIGN KEY (idTeam) REFERENCES Team(idTeam)
);