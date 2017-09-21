# HeidiSQL Dump 
#
# --------------------------------------------------------
# Host:                 127.0.0.1
# Database:             halodb
# Server version:       5.1.39-community
# Server OS:            Win32
# Target-Compatibility: Standard ANSI SQL
# HeidiSQL version:     3.2 Revision: 1129
# --------------------------------------------------------

/*!40100 SET CHARACTER SET latin1;*/
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ANSI';*/
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;*/


#
# Database structure for database 'halodb'
#

CREATE DATABASE /*!32312 IF NOT EXISTS*/ "halodb" /*!40100 DEFAULT CHARACTER SET latin1 */;

USE "halodb";


#
# Table structure for table 'nodepath'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "nodepath" (
  "node" varchar(100) DEFAULT NULL,
  "path" varchar(500) DEFAULT NULL
) /*!40100 DEFAULT CHARSET=latin1*/;



#
# Dumping data for table 'nodepath'
#

LOCK TABLES "nodepath" WRITE;
/*!40000 ALTER TABLE "nodepath" DISABLE KEYS;*/
REPLACE INTO "nodepath" ("node", "path") VALUES
	('CREAZIONE-PC2','CREAZIONE-PC2#');
REPLACE INTO "nodepath" ("node", "path") VALUES
	('CREAZIONE-PC3','CREAZIONE-PC3#');
REPLACE INTO "nodepath" ("node", "path") VALUES
	('DESKTOP-4F5L61N','CREAZIONE-PC3#DESKTOP-4F5L61N#');
REPLACE INTO "nodepath" ("node", "path") VALUES
	('DIPAK-PC','CREAZIONE-PC3#DESKTOP-4F5L61N#DIPAK-PC#');
REPLACE INTO "nodepath" ("node", "path") VALUES
	('MTECH-PC','CREAZIONE-PC3#DESKTOP-4F5L61N#DIPAK-PC#MTECH-PC#');
REPLACE INTO "nodepath" ("node", "path") VALUES
	('SHEKHAR','CREAZIONE-PC3#DESKTOP-4F5L61N#DIPAK-PC#MTECH-PC#SHEKHAR#');
REPLACE INTO "nodepath" ("node", "path") VALUES
	('TECH-HUB-PC','CREAZIONE-PC3#DESKTOP-4F5L61N#DIPAK-PC#MTECH-PC#SHEKHAR#TECH-HUB-PC#');
REPLACE INTO "nodepath" ("node", "path") VALUES
	('VIREN','CREAZIONE-PC3#DESKTOP-4F5L61N#DIPAK-PC#MTECH-PC#SHEKHAR#TECH-HUB-PC#VIREN#');
/*!40000 ALTER TABLE "nodepath" ENABLE KEYS;*/
UNLOCK TABLES;


#
# Table structure for table 'receivehistory'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "receivehistory" (
  "fid" int(10) unsigned NOT NULL DEFAULT '0',
  "filename" varchar(100) DEFAULT NULL,
  "filesize" varchar(50) DEFAULT NULL,
  "date" varchar(50) DEFAULT NULL,
  "time" varchar(50) DEFAULT NULL,
  "receivername" varchar(50) DEFAULT NULL,
  "requiretime" varchar(50) DEFAULT NULL,
  PRIMARY KEY ("fid")
) /*!40100 DEFAULT CHARSET=latin1*/;



#
# Dumping data for table 'receivehistory'
#

# (No data found.)



#
# Table structure for table 'senderhistory'
#

CREATE TABLE /*!32312 IF NOT EXISTS*/ "senderhistory" (
  "fid" int(30) unsigned NOT NULL AUTO_INCREMENT,
  "filename" varchar(100) DEFAULT NULL,
  "filesize" varchar(100) DEFAULT NULL,
  "date" varchar(50) DEFAULT NULL,
  "time" varchar(50) DEFAULT NULL,
  "sendfileto" varchar(100) DEFAULT NULL,
  "status" varchar(50) DEFAULT NULL,
  PRIMARY KEY ("fid")
) AUTO_INCREMENT=2 /*!40100 DEFAULT CHARSET=latin1*/;



#
# Dumping data for table 'senderhistory'
#

LOCK TABLES "senderhistory" WRITE;
/*!40000 ALTER TABLE "senderhistory" DISABLE KEYS;*/
REPLACE INTO "senderhistory" ("fid", "filename", "filesize", "date", "time", "sendfileto", "status") VALUES
	('1','sandip.jpg','4MB','12/27/2016','12:00PM','CreazionePC2','success');
/*!40000 ALTER TABLE "senderhistory" ENABLE KEYS;*/
UNLOCK TABLES;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE;*/
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;*/
