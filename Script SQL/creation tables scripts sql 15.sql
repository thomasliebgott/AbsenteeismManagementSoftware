--select CONCAT(CONCAT('drop table ', table_name), 'cascade constraints;') from user_tables;
 
drop table UTILISATEUR cascade constraints;
drop table SEANCE cascade constraints;
drop table GROUPE cascade constraints;
drop table ENSEIGNANT cascade constraints;
drop table ETUDIANT cascade constraints;
drop table COURS cascade constraints;
drop table ABSENCE cascade constraints;
drop table HORAIRE cascade constraints;
drop table JUSTIFICATIF cascade constraints;
drop table LASESSION cascade constraints;
drop table SANCTION cascade constraints;
drop table QUOTAS cascade constraints;

DROP SEQUENCE absence_auto_incr;
DROP SEQUENCE enseignant_auto_incr;
DROP SEQUENCE etudiant_auto_incr;
DROP SEQUENCE groupe_auto_incr;
DROP SEQUENCE cours_auto_incr;
DROP SEQUENCE justificatif_auto_incr;
DROP SEQUENCE laSession_auto_incr;
DROP SEQUENCE seance_auto_incr;
DROP SEQUENCE utilisateur_auto_incr;
Drop SEQUENCE sanction_auto_incr;
Drop SEQUENCE quotas_auto_incr;

--CREATION DES TABLES --------------

--utilisateur--
CREATE TABLE utilisateur(
id_utilisateur NUMBER(8),
mdp VARCHAR(50),
profil VARCHAR(50),
CONSTRAINT PK_utilisateur PRIMARY KEY(id_utilisateur));

--Groupe-- 
CREATE TABLE groupe(
numeroGrp NUMBER(4),
capaciteMax NUMBER(4) NOT NULL,
filiere VARCHAR2(50)NOT NULL,
CONSTRAINT PK_numeroGrp PRIMARY KEY(numeroGrp)); 

--enseignant--
CREATE TABLE enseignant(
idEnseignant NUMBER(4),
id_utilisateur NUMBER(8),
nomEnseignant VARCHAR2(50) NOT NULL,
prenomEnseignant VARCHAR2(50)NOT NULL,
numTel NUMBER(20),
CONSTRAINT PK_idEnseignant PRIMARY KEY(idEnseignant));

--Etudiant--
CREATE TABLE etudiant(
idEtudiant NUMBER(4),
id_utilisateur NUMBER(8),
adresseMail VARCHAR2(100),
nomEtudiant VARCHAR2(50) NOT NULL,
prenomEtudiant VARCHAR2(50)NOT NULL,
numeroGrp NUMBER(4)NOT NULL,
filiere VARCHAR2(50) NOT NULL,
CONSTRAINT PK_idEtudiant PRIMARY KEY(idEtudiant));

--Cours--
create table cours(
idCours NUMBER(4),
idEnseignant NUMBER(4),
nomCours VARCHAR(10),
masseHoraireAmphi number(4),
masseHoraireTD number(4),
masseHoraireTP number(4),
masseHoraireExam number(4),
CONSTRAINT PK_idCours PRIMARY KEY(idCours));

--seance--
CREATE TABLE seance(
idSeance NUMBER(4),
typeSeance VARCHAR(10),
numSession NUMBER(4),
numeroGrp NUMBER(4),
idEnseignant NUMBER(4),
idCours NUMBER(4),
idHoraire NUMBER(4),
dateSeance date,
idSemaine NUMBER(4),
CONSTRAINT PK_idSeance PRIMARY KEY(idSeance));

--horaire--
CREATE TABLE horaire(
idHoraire NUMBER(4),
heureDebut NUMBER(4),
heureFin NUMBER(4),
CONSTRAINT PK_idHoraire PRIMARY KEY(idHoraire));

--session-- 
CREATE TABLE laSession(
numSession NUMBER(4),
dateDebutSession date,
dateFinSession date,
CONSTRAINT PK_numSession PRIMARY KEY(numSession));

--absence--
CREATE TABLE absence(
idAbsence NUMBER(4),
idEtudiant NUMBER(4),
idSeance NUMBER(4),
idJustificatif NUMBER(4),
justifieOuNon VARCHAR2(50),
horsQuota VARCHAR(50),
CONSTRAINT PK_absence PRIMARY KEY(idAbsence));

--justificatif--
CREATE TABLE justificatif(
idJustificatif NUMBER(4),
motif VARCHAR2(10),
chemin VARCHAR(10),
CONSTRAINT PK_idJustificatif PRIMARY KEY(idJustificatif));

--sanction--
CREATE TABLE sanction(
idSanction NUMBER(4),
idEtudiant NUMBER(4),
typeSanction VARCHAR(100),
CONSTRAINT PK_idSanction PRIMARY KEY(idSanction));

--Quotas
CREATE TABLE quotas(
idQuotas NUMBER(4),
seuil NUMBER(4),
penalite VARCHAR(100),
CONSTRAINT PK_idQuotas PRIMARY KEY(idQuotas));

--ajout des FK--

--seance--
ALTER TABLE seance
ADD CONSTRAINT FK_seance_numSession FOREIGN KEY(numSession) REFERENCES laSession(numSession)ON DELETE SET NULL;

ALTER TABLE seance
ADD CONSTRAINT FK_seance_numeroGrp FOREIGN KEY(numeroGrp) REFERENCES groupe(numeroGrp)ON DELETE SET NULL;

ALTER TABLE seance
ADD CONSTRAINT FK_seance_idEnseignant FOREIGN KEY(idEnseignant) REFERENCES enseignant(idEnseignant)ON DELETE SET NULL;

ALTER TABLE seance
ADD CONSTRAINT FK_seance_idCours FOREIGN KEY(idCours) REFERENCES cours(idCours)ON DELETE SET NULL;

ALTER TABLE seance
ADD CONSTRAINT FK_seance_idHoraire FOREIGN KEY(idHoraire) REFERENCES horaire(idHoraire)ON DELETE SET NULL;

--etudiant--
ALTER TABLE etudiant
ADD CONSTRAINT FK_etudiant_numeroGrp FOREIGN KEY(numeroGrp) REFERENCES groupe(numeroGrp)ON DELETE SET NULL;

ALTER TABLE etudiant
ADD CONSTRAINT FK_etudiant_utilisateur FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur)ON DELETE SET NULL;

--enseignant--
ALTER TABLE enseignant
ADD CONSTRAINT FK_enseignant_utilisateur FOREIGN KEY(id_utilisateur) REFERENCES utilisateur(id_utilisateur)ON DELETE SET NULL;

--absence--
ALTER TABLE absence
ADD CONSTRAINT FK_absence_idEtudiant FOREIGN KEY(idEtudiant) REFERENCES etudiant(idEtudiant)ON DELETE SET NULL;

ALTER TABLE absence
ADD CONSTRAINT FK_absence_idSeance FOREIGN KEY(idSeance) REFERENCES seance(idSeance)ON DELETE SET NULL;

ALTER TABLE absence
ADD CONSTRAINT FK_absence_idJustificatif FOREIGN KEY(idJustificatif) REFERENCES justificatif(idJustificatif)ON DELETE SET NULL;

--justificatif--
--ALTER TABLE justificatif
--ADD CONSTRAINT FK_justificatif_idAbsence FOREIGN KEY(idAbsence) REFERENCES absence(idAbsence)ON DELETE SET NULL;

--sanction--
ALTER TABLE sanction
ADD CONSTRAINT FK_sanction_idEtudiant FOREIGN KEY(idEtudiant) REFERENCES etudiant(idEtudiant)ON DELETE SET NULL;

--quotas--
--ALTER TABLE quotas
--ADD CONSTRAINT FK_quotas_idSanction FOREIGN KEY(idSanction) REFERENCES sanction(idSanction)ON DELETE SET NULL;



-----------------------------------Creation des séquences--------------------------------------------

--utilisateur--
CREATE SEQUENCE utilisateur_auto_incr  
    START WITH 1 
    INCREMENT BY 1 ;

--enseignant--
CREATE SEQUENCE enseignant_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--etudiant--
CREATE SEQUENCE etudiant_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--absence--
CREATE SEQUENCE absence_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--seance--
CREATE SEQUENCE seance_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--laSession--
CREATE SEQUENCE laSession_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--groupe--
CREATE SEQUENCE groupe_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--justificatif--
CREATE SEQUENCE justificatif_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--cours--
CREATE SEQUENCE cours_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;

--idSanction--
CREATE SEQUENCE sanction_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;
	
--idQuota--
CREATE SEQUENCE quotas_auto_incr  
    START WITH 1  
    INCREMENT BY 1 ;
	
--remplissage tables ---
--utIlisateurs--
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Etudiant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Gestionnaire');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Etudiant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');
insert into utilisateur(id_utilisateur,mdp,profil) Values (utilisateur_auto_incr.nextval,'test','Enseignant');

--groupe--
insert into groupe(numeroGrp,capaciteMax,filiere) values (groupe_auto_incr.nextval,50,'Classique');
insert into groupe(numeroGrp,capaciteMax,filiere) values (groupe_auto_incr.nextval,50,'Classique');
insert into groupe(numeroGrp,capaciteMax,filiere) values (groupe_auto_incr.nextval,50,'Classique');
insert into groupe(numeroGrp,capaciteMax,filiere) values (groupe_auto_incr.nextval,50,'Classique');
insert into groupe(numeroGrp,capaciteMax,filiere) values (groupe_auto_incr.nextval,50,'Apprentissage');
insert into groupe(numeroGrp,capaciteMax,filiere) values (groupe_auto_incr.nextval,50,'Apprentissage');

--etudiant--
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,1,'edouart-philipe@groupe-esigelec.org','philipe','edouart',1,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,4,'jean-michelle@groupe-esigelec.org','jean','michelle',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,5,'emanuel-macron@groupe-esigelec.org','emanuel','macron',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,6,'jonnhy-halyday@groupe-esigelec.org','jonnhy','halyday',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,7,'herve-ducray@groupe-esigelec.org','hervé','ducray',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,8,'michelle-berger@groupe-esigelec.org','michelle','berger',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,9,'anthony-joshua@groupe-esigelec.org','anthony','joshua',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,10,'conor-Mcgregor@groupe-esigelec.org','conor','Mcgregor',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,11,'francis-Ngannnou@groupe-esigelec.org','Ngannnou','francis',2,'Classique');
insert into etudiant(idEtudiant,id_Utilisateur,adresseMail,nomEtudiant,prenomEtudiant,numeroGrp,filiere) Values (etudiant_auto_incr.nextval,12,'ferguson-tony@groupe-esigelec.org','Ferguson','tony',2,'Classique');

--enseignant--
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,2,'Aldrich','Killian',0648789652);
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,5,'Tony','Stark',0648789652);
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,6,'Banner','Bruce',0648789652);
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,7,'Balmer','Nero',0648789652);
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,8,'Lord','Vike',0648789652);
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,9,'Pasteur','Louis',0648789652);
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,10,'Malfoy','Drago',0648789652);
insert into enseignant(idEnseignant,id_Utilisateur,nomEnseignant,prenomEnseignant,numTel) values (enseignant_auto_incr.nextval,11,'Harry','Potter',0648789652);

--Cours--
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'Maths', 10, 10, 10, 10, 1);
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'PDL', 10, 10, 10, 10, 1);
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'PIC', 10, 10, 10, 10, 1);
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'Anglais', 10, 10, 10, 10, 4);
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'Lifi', 10, 10, 10, 10, 5);
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'EK', 10, 10, 10, 10, 6);
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'EP', 10, 10, 10, 10, 7);
INSERT INTO cours (idCours, nomCours, masseHoraireAmphi, masseHoraireTD , masseHoraireTP, masseHoraireExam, idEnseignant) 
 VALUES (cours_auto_incr.nextval, 'JAVA', 10, 10, 10, 10, 8);

--Horaire--
Insert into horaire(idHoraire, heureDebut, heureFin) values (1, 8, 9);
Insert into horaire(idHoraire, heureDebut, heureFin) values (2, 9, 10);
Insert into horaire(idHoraire, heureDebut, heureFin) values (3, 10, 11);
Insert into horaire(idHoraire, heureDebut, heureFin) values (4, 11, 12);
Insert into horaire(idHoraire, heureDebut, heureFin) values (5, 12, 13);
Insert into horaire(idHoraire, heureDebut, heureFin) values (6, 13, 14);
Insert into horaire(idHoraire, heureDebut, heureFin) values (7, 14,15 );
Insert into horaire(idHoraire, heureDebut, heureFin) values (8, 15, 16);
Insert into horaire(idHoraire, heureDebut, heureFin) values (9, 16, 17);
Insert into horaire(idHoraire, heureDebut, heureFin) values (10, 17, 18);
Insert into horaire(idHoraire, heureDebut, heureFin) values (11, 18, 19);

--Sessions
INSERT INTO laSession(numSession, dateDebutSession, dateFinSession) VALUES(1, TO_DATE('2020/05/10', 'YYYY/MM/DD'), TO_DATE('2020/10/10', 'YYYY/MM/DD'));
INSERT INTO laSession(numSession, dateDebutSession, dateFinSession) VALUES(2, TO_DATE('2020/05/10', 'YYYY/MM/DD'), TO_DATE('2020/10/10', 'YYYY/MM/DD'));

--seance--
insert into seance(idSeance ,typeSeance,numSession ,numeroGrp ,idEnseignant,idCours,idHoraire, dateSeance,idSemaine) values (1,'exam',1,2,1,1,1,TO_DATE('2020/05/10', 'YYYY/MM/DD'),1);

--justificatif--
insert into justificatif(idJustificatif,motif,chemin) values (1,'pdf','D');

--insert into absence(idAbsence,idEtudiant,idSeance,idJustificatif,justifieOuNon,horsQuota) values (absence_auto_incr.nextval,1,1,1,'non','non');



