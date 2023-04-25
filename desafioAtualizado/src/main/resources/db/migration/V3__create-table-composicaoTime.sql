CREATE TABLE ComposicaoTime (
                                Id BIGINT NOT NULL AUTO_INCREMENT,
                                Id_Time BIGINT NOT NULL,
                                Id_Integrante BIGINT NOT NULL,
                                PRIMARY KEY (Id),
                                FOREIGN KEY (Id_Time) REFERENCES Time(Id),
                                FOREIGN KEY (Id_Integrante) REFERENCES Integrante(Id)
);
