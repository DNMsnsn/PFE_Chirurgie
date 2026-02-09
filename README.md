# Documentation

## Contents
- **Project Name**: PFE Chirurgie  
- **Type**: Hospital Management Application  
- **Developer Name**: KASSOURI Tahar (0xMsnsnCXI) – alias ‘Msnsn’  
- **Technologies**: Java, Jakarta EE (Servlet & JSP), JDBC, Apache Tomcat, MySQL, ZXing, PDFBox  
- **Start Date**: 22-01-2026  
- **Finish Date**: 09-02-2026  

## Introduction

### Why does this project exist?
**Problems**
- Nowadays, hospitals are still using desktop applications with outdated data management systems.  
- Patients must physically visit the hospital to obtain even a simple medical certificate.

**Goals** 
- This application allows medical assistants to manage patients, doctors, and medical documents efficiently using a modern system.  
- The administration can monitor all document-related activities in the hospital, reducing the risk of corruption.  
- Patients can access their data and print medical documents easily using a simple QR code scan.  
- Patients can quickly obtain work justifications and other medical documents through this platform.

## Requirements

**Objectives** 
- Centralize hospital medical data.  
- Simplify the management of hospital resources.  
- Facilitate patient access to their data.

**Users**
- AMSP (Medical Assistant / Admin)  
- Patient

**Main Functionalities**
- Patient and doctor management  
- Resource management  
- Automatic generation of medical documents

## Functional Analysis

**Diagrams**

> **Actors**:
- Admin (AMSP)  
- Patient

> **Actions**:
- Add, update, delete patients / Show & print patient data  
- Add, update, delete doctors  
- Add, update, delete assistants  
- Add, update, delete, show patient diagnostic data  
- Add, update, delete appointments / Show & print appointment data  
- Add, update, delete patient ‘ARRET DE TRAVAIL’ / Show & print patient ‘ARRET DE TRAVAIL’ data  
- Add, update, delete patient ‘CERTIFICAT DE SEJOUR’ / Show & print patient ‘CERTIFICAT DE SEJOUR’ data  
- Add, update, delete patient ‘CERTIFICAT MEDICAL’ / Show & print patient ‘CERTIFICAT MEDICAL’ data  
- Add, update, delete patient ‘CRH’ / Show & print patient ‘CRH’ data  
- Add, update, delete patient ‘CRO’ / Show & print patient ‘CRO’ data  

## Data Analysis

### Database

-- =========================
-- Database
-- =========================
CREATE DATABASE IF NOT EXISTS pfe_chirurgie
CHARACTER SET utf8mb4
COLLATE utf8mb4_unicode_ci;

USE pfe_chirurgie;

-- =========================
-- TABLE PATIENT
-- =========================
CREATE TABLE patient (
    id_patient INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    date_naissance DATE NOT NULL,
    sexe ENUM('Homme','Femme'),
    telephone VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE,
    adresse VARCHAR(255),
    groupe_sanguin VARCHAR(3),
    date_creation DATETIME DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

-- =========================
-- TABLE DOCTOR
-- =========================
CREATE TABLE medecin (
    id_medecin INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    specialite VARCHAR(100) NOT NULL,
    telephone VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE,
    role VARCHAR(30) DEFAULT 'CHIRURGIEN'
) ENGINE=InnoDB;

-- =========================
-- TABLE AMSP
-- =========================
CREATE TABLE amsp (
    id_amsp INT AUTO_INCREMENT PRIMARY KEY,
    nom VARCHAR(50) NOT NULL,
    prenom VARCHAR(50) NOT NULL,
    telephone VARCHAR(20) UNIQUE,
    email VARCHAR(100) UNIQUE,
    fonction VARCHAR(50)
) ENGINE=InnoDB;

-- =========================
-- TABLE APPOINTMENTS
-- =========================
CREATE TABLE rendez_vous (
    id_rdv INT AUTO_INCREMENT PRIMARY KEY,
    date_rdv DATETIME NOT NULL,
    motif VARCHAR(255) NOT NULL,
    statut ENUM('PREVU','ANNULE','REALISE','RATE') DEFAULT 'PREVU',
    id_patient INT NOT NULL,
    id_medecin INT NOT NULL,
    FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
    FOREIGN KEY (id_medecin) REFERENCES medecin(id_medecin)
) ENGINE=InnoDB;

-- =========================
-- TABLE DIAGNOSTICS
-- =========================
CREATE TABLE diagnostic (
    id_diagnostic INT AUTO_INCREMENT PRIMARY KEY,
    description TEXT NOT NULL,
    date_diagnostic DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_patient INT NOT NULL,
    id_medecin INT NOT NULL,
    FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
    FOREIGN KEY (id_medecin) REFERENCES medecin(id_medecin)
) ENGINE=InnoDB;

-- =========================
-- TABLE MEDICAL HISTORY
-- =========================
CREATE TABLE historique (
    id_historique INT NOT NULL AUTO_INCREMENT,
    type_action VARCHAR(50) NOT NULL,
    details TEXT NOT NULL,
    date_action DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_patient INT DEFAULT NULL,
    id_amsp INT DEFAULT NULL,
    PRIMARY KEY (id_historique),
    KEY id_patient (id_patient),
    KEY id_amsp (id_amsp),
    CONSTRAINT historique_ibfk_1 FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
    CONSTRAINT historique_ibfk_2 FOREIGN KEY (id_amsp) REFERENCES amsp(id_amsp)
) ENGINE=InnoDB;

-- =========================
-- TABLE CRH (Hospitalization Report)
-- =========================
CREATE TABLE crh (
    id_crh INT NOT NULL AUTO_INCREMENT,
    id_patient INT NOT NULL,
    id_medecin INT NOT NULL,
    content TEXT,
    date_entree DATE NOT NULL,
    date_sortie DATE DEFAULT NULL,
    PRIMARY KEY (id_crh),
    KEY id_patient (id_patient),
    KEY id_medecin (id_medecin),
    CONSTRAINT crh_ibfk_1 FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
    CONSTRAINT crh_ibfk_2 FOREIGN KEY (id_medecin) REFERENCES medecin(id_medecin)
) ENGINE=InnoDB;

-- =========================
-- TABLE CRO (Operative Report)
-- =========================
CREATE TABLE cro (
    id_cro INT AUTO_INCREMENT PRIMARY KEY,
    date_operation DATE NOT NULL DEFAULT (curdate()),
    bloc INT,
    diagnostic_lesionnel VARCHAR(200) NOT NULL,
    intervention_pratique VARCHAR(200) NOT NULL,
    id_patient INT NOT NULL,
    id_operateur INT NOT NULL,
    id_aide INT NOT NULL,
    id_reanimateur INT NOT NULL,
    id_anesthesiste INT NOT NULL,
    FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
    FOREIGN KEY (id_operateur) REFERENCES medecin(id_medecin),
    FOREIGN KEY (id_aide) REFERENCES medecin(id_medecin),
    FOREIGN KEY (id_reanimateur) REFERENCES medecin(id_medecin),
    FOREIGN KEY (id_anesthesiste) REFERENCES medecin(id_medecin)
) ENGINE=InnoDB;

-- =========================
-- TABLE CERTIFICAT DE SEJOUR
-- =========================
CREATE TABLE certificat_sejour (
    id_titre INT AUTO_INCREMENT PRIMARY KEY,
    date_debut DATE NOT NULL,
    date_fin DATE,
    id_patient INT NOT NULL,
    FOREIGN KEY (id_patient) REFERENCES patient(id_patient)
) ENGINE=InnoDB;

-- =========================
-- TABLE CERTIFICAT MEDICAL
-- =========================
CREATE TABLE certificat_medical (
    id_certificat INT AUTO_INCREMENT PRIMARY KEY,
    statut ENUM('APTE','INAPTE') DEFAULT 'INAPTE',
    date_emission DATE NOT NULL DEFAULT (curdate()),
    id_patient INT NOT NULL,
    id_medecin INT NOT NULL,
    FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
    FOREIGN KEY (id_medecin) REFERENCES medecin(id_medecin)
) ENGINE=InnoDB;

-- =========================
-- TABLE ARRET DE TRAVAIL
-- =========================
CREATE TABLE arret_travail (
    id_arret INT AUTO_INCREMENT PRIMARY KEY,
    type ENUM('UN ARRET DE TRAVAIL','UNE PROLONGATION','UNE REPRISE') DEFAULT 'UN ARRET DE TRAVAIL',
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    id_patient INT NOT NULL,
    id_medecin INT NOT NULL,
    FOREIGN KEY (id_patient) REFERENCES patient(id_patient),
    FOREIGN KEY (id_medecin) REFERENCES medecin(id_medecin)
) ENGINE=InnoDB;


### Conception

**Architecture**
- MVC
- Model Design Pattern : DAO
- Data Access : JDBC
- Controller : Servlet
- View : JSP

**Technologies**
- Backend : Java - Jackarta EE (Servlet & JSP)
- Frontend : HTML, JSP, CSS, JS
- Base de donnees : MySql


### Realisation
- 22 JSP Pages for Views with 2 CSS Pages for Style
- 7 External Libraries with 4 Java Classes for Auto-Generating Documents & QR Codes
- 57 Servlet Pages for Controllers
- 22 Java Class Pages for Model

### Tests
- Unit Tests with JUnit
