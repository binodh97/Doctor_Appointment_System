DROP DATABASE IF EXISTS ChannelingSystem;
CREATE DATABASE IF NOT EXISTS ChannelingSystem;
SHOW DATABASES;
USE ChannelingSystem;

DROP TABLE IF EXISTS Login;
CREATE TABLE IF NOT EXISTS Login(
     rId VARCHAR (15),
     userName VARCHAR(25) NOT NULL,
     password VARCHAR(25) NOT NULL,
     role VARCHAR(10) NOT NULL,
     CONSTRAINT PRIMARY KEY (rId)
);

SHOW TABLES;
DESCRIBE Login;

SELECT * FROM Login;

#=======================================================================================================================

DROP TABLE IF EXISTS Reception;
CREATE TABLE IF NOT EXISTS Reception(
    rId VARCHAR(15),
    rName VARCHAR (50) NOT NULL DEFAULT 'Unknown',
    rAge VARCHAR(10),
    rGender VARCHAR (10),
    rAddress TEXT,
    rContact VARCHAR(15),
    rMail VARCHAR(30),
    CONSTRAINT PRIMARY KEY (rId)
);

SHOW TABLES;
DESC Reception;

INSERT INTO Reception(rId,rName, rAge, rGender, rAddress, rContact, rMail, rUserN, rPassword)
VALUES ('R001','Sanduni','24','Female','Rathgama','0761234567','binoddljjlll','Sandu123','12345San');


#=======================================================================================================================

DROP TABLE IF EXISTS Patient;
CREATE TABLE IF NOT EXISTS Patient(
    pId VARCHAR(15) ,
    pName VARCHAR (50) NOT NULL DEFAULT 'Unknown',
    pAge VARCHAR(10),
    pGender VARCHAR (10),
    pAddress TEXT,
    pContact VARCHAR(15),
    pMail VARCHAR(30),
    CONSTRAINT PRIMARY KEY (pId)
);

SHOW TABLES;
DESCRIBE Patient;

INSERT INTO Patient(pId, pName, pAge, pGender, pAddress, pContact, pMail)
VALUES ('001','b','20','Male','Rathgama','073636363','asfsdgvzdfbgvdz');

SELECT * FROM Patient;

#========================================================

DROP TABLE IF EXISTS Doctor;
CREATE TABLE IF NOT EXISTS Doctor(
      dId VARCHAR(15) ,
      dName VARCHAR (50) NOT NULL DEFAULT 'Unknown',
      dAge VARCHAR(5),
      dGender VARCHAR (10),
      dAddress TEXT,
      dContact VARCHAR(15),
      dSpecialize VARCHAR(30),
      dFees DOUBLE,
      CONSTRAINT PRIMARY KEY (dId)
);

SHOW TABLES;
DESCRIBE Doctor;

INSERT INTO Doctor(dId, dName, dAge, dGender, dAddress, dContact, dSpecialize, dFees)
VALUES ('123','Kamal','44','Male','sajgdjhxdxd','437243876','Ordinary','1500.00');

SELECT * FROM Doctor;

#========================================================

DROP TABLE IF EXISTS Nurse;
CREATE TABLE IF NOT EXISTS Nurse(
      nId VARCHAR(15) ,
      nName VARCHAR (50) NOT NULL DEFAULT 'Unknown',
      nAge VARCHAR(5),
      nGender VARCHAR (10),
      nAddress TEXT ,
      nContact VARCHAR(15),
      CONSTRAINT PRIMARY KEY (nId)
);

SHOW TABLES;
DESCRIBE Nurse;

INSERT INTO Nurse(nId, nName, nAge, nGender, nAddress, nContact)
VALUES ('222','Ruwani','22','Male','Unawatuna','287394872');

SELECT * FROM Nurse;

#=========================================================

DROP TABLE IF EXISTS Appointment;
CREATE TABLE IF NOT EXISTS Appointment(
      aId VARCHAR(15),
      patId VARCHAR(15),
      patName VARCHAR (50) NOT NULL,
      docId VARCHAR(15),
      docName VARCHAR (50) NOT NULL,
      nDate VARCHAR(15),
      nTime VARCHAR(15),
      aDate VARCHAR(15),
      aTime VARCHAR (10),
      cost DOUBLE,
      CONSTRAINT PRIMARY KEY (aId),
      CONSTRAINT FOREIGN KEY (patId) REFERENCES Patient(pId) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT FOREIGN KEY (docId) REFERENCES Doctor(dId) ON DELETE CASCADE ON UPDATE CASCADE
);

SHOW TABLES ;
DESCRIBE Appointment;

INSERT INTO Appointment(aId, patId, docId, nDate, nTime, aDate, aTime, cost)
VALUES ('001','001','123','45','757564','55666','75475','1500.00');

SELECT * FROM Appointment;

#=========================================================

DROP TABLE IF EXISTS AppointmentDetails;
CREATE TABLE IF NOT EXISTS AppointmentDetails(
     apId VARCHAR(15),
     ptId VARCHAR(15),
     date VARCHAR(15),
     charge DOUBLE,
     CONSTRAINT PRIMARY KEY(apId,ptId),
     CONSTRAINT FOREIGN KEY (apId) REFERENCES Appointment(aId) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT FOREIGN KEY (ptId) REFERENCES Patient(pId) ON DELETE CASCADE ON UPDATE CASCADE
);


SHOW TABLES ;
DESCRIBE AppointmentDetails;

INSERT INTO AppointmentDetails(apId,ptId,date,charge)
VALUES ('124','234','23455','2345.00');



#=========================================================

DROP TABLE IF EXISTS Treatment;
CREATE TABLE IF NOT EXISTS Treatment(
      trId VARCHAR(15),
      trTreat VARCHAR(30),
      trFee DOUBLE,
      CONSTRAINT PRIMARY KEY(trId)
);

SHOW TABLES ;
DESCRIBE Treatment;

INSERT INTO Treatment(trId,trTreat,trDate)
VALUES ('001','XRay','123');

SELECT * FROM Treatment;

#========================================================



#======================================================================================================

DROP TABLE IF EXISTS Serve;
CREATE TABLE IF NOT EXISTS Serve(
      dctId VARCHAR(15),
      nurId VARCHAR(15),
      date VARCHAR(10),
      CONSTRAINT PRIMARY KEY(dctId,nurId),
      CONSTRAINT FOREIGN KEY (dctId) REFERENCES Doctor(dID) ON DELETE CASCADE ON UPDATE CASCADE,
      CONSTRAINT FOREIGN KEY (nurId) REFERENCES Nurse(nId) ON DELETE CASCADE ON UPDATE CASCADE
);

SHOW TABLES ;
DESCRIBE Serve;

INSERT INTO Serve(dctId, nurId, date)
VALUES ('123','222','676556');

SELECT * FROM Serve;
#========================================================

DROP TABLE IF EXISTS Payments;
CREATE TABLE IF NOT EXISTS Payments(
      payId VARCHAR(15),
      drId VARCHAR(15),
      aId  VARCHAR(15),
      date DATE,
      total DOUBLE,
      CONSTRAINT PRIMARY KEY(payId),
      CONSTRAINT FOREIGN KEY (drId) REFERENCES Doctor(dID) ON DELETE CASCADE ON UPDATE CASCADE
);

SHOW TABLES ;
DESCRIBE Payments;

INSERT INTO Payments(payId, drId, aId, date, total)
VALUES ('001','123','001','13123','13223');

#========================================================

DROP TABLE IF EXISTS TreatmentManage;
CREATE TABLE IF NOT EXISTS TreatmentManage(
     tId VARCHAR(15),
     patId VARCHAR(15),
     patName VARCHAR (50) NOT NULL,
     treat VARCHAR (30) NOT NULL,
     tDate VARCHAR(15),
     tTime VARCHAR(15),
     cost DOUBLE,
     CONSTRAINT PRIMARY KEY (tId),
     CONSTRAINT FOREIGN KEY (tId) REFERENCES Treatment(trId) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT FOREIGN KEY (patId) REFERENCES Patient(pId) ON DELETE CASCADE ON UPDATE CASCADE
);

SHOW TABLES ;
DESCRIBE TreatmentManage;

#========================================================

DROP TABLE IF EXISTS TreatmentDetails;
CREATE TABLE IF NOT EXISTS TreatmentDetails(
     trtId VARCHAR(15),
     pId VARCHAR(15),
     date VARCHAR (10),
     total DOUBLE,
     CONSTRAINT PRIMARY KEY(trtId,pId),
     CONSTRAINT FOREIGN KEY (trtId) REFERENCES Treatment(trId) ON DELETE CASCADE ON UPDATE CASCADE,
     CONSTRAINT FOREIGN KEY (pId) REFERENCES Patient(pId) ON DELETE CASCADE ON UPDATE CASCADE
);

SHOW TABLES ;
DESCRIBE TreatmentDetails;

SELECT * FROM TreatmentDetails;