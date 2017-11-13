# MySQL-Front 3.2  (Build 2.10)

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET CHARACTER SET 'gb2312' */;


# Host: 127.0.0.1    Database: check
# ------------------------------------------------------
# Server version 5.0.41-community-nt

CREATE DATABASE `check`;
USE `check`;

#
# Table structure for table allvar
#

CREATE TABLE `allvar` (
  `dictId` int(6) unsigned NOT NULL auto_increment,
  `bianliang` char(20) default NULL,
  PRIMARY KEY  (`dictId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

#
# Dumping data for table allvar
#


#
# Table structure for table dict
#

CREATE TABLE `dict` (
  `dictId` int(6) unsigned NOT NULL auto_increment,
  `bianliang` char(20) default NULL,
  `len` char(4) default NULL,
  `crf` char(20) default NULL,
  `crfname` char(50) default NULL,
  `bname` char(100) default NULL,
  `paixu` char(3) default NULL,
  `bvalue` text,
  `page` int(6) default NULL,
  `view` char(20) default NULL,
  `flag` char(5) default NULL,
  `range` char(50) default NULL,
  `ques` char(200) default NULL,
  PRIMARY KEY  (`dictId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

#
# Dumping data for table dict
#


#
# Table structure for table flagvar
#

CREATE TABLE `flagvar` (
  `dictId` int(6) unsigned NOT NULL auto_increment,
  `bianliang` char(20) default NULL,
  `originId` int(6) default NULL,
  PRIMARY KEY  (`dictId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

#
# Dumping data for table flagvar
#


#
# Table structure for table keyword
#

CREATE TABLE `keyword` (
  `id` int(6) unsigned NOT NULL auto_increment,
  `word` char(20) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

#
# Dumping data for table keyword
#

INSERT INTO `keyword` VALUES (1,'abs');
INSERT INTO `keyword` VALUES (2,'abstract');
INSERT INTO `keyword` VALUES (3,'action');
INSERT INTO `keyword` VALUES (4,'add');
INSERT INTO `keyword` VALUES (5,'after');
INSERT INTO `keyword` VALUES (6,'aggregate');
INSERT INTO `keyword` VALUES (7,'all');
INSERT INTO `keyword` VALUES (8,'alter');
INSERT INTO `keyword` VALUES (9,'analyze');
INSERT INTO `keyword` VALUES (10,'and');
INSERT INTO `keyword` VALUES (11,'as');
INSERT INTO `keyword` VALUES (12,'asc');
INSERT INTO `keyword` VALUES (13,'assert');
INSERT INTO `keyword` VALUES (14,'atan');
INSERT INTO `keyword` VALUES (15,'avg');
INSERT INTO `keyword` VALUES (16,'begin');
INSERT INTO `keyword` VALUES (17,'between');
INSERT INTO `keyword` VALUES (18,'binary');
INSERT INTO `keyword` VALUES (19,'bit');
INSERT INTO `keyword` VALUES (20,'blob');
INSERT INTO `keyword` VALUES (21,'bool');
INSERT INTO `keyword` VALUES (22,'boolean');
INSERT INTO `keyword` VALUES (23,'both');
INSERT INTO `keyword` VALUES (24,'break');
INSERT INTO `keyword` VALUES (25,'buckup');
INSERT INTO `keyword` VALUES (26,'by');
INSERT INTO `keyword` VALUES (27,'byte');
INSERT INTO `keyword` VALUES (28,'cascade');
INSERT INTO `keyword` VALUES (29,'case');
INSERT INTO `keyword` VALUES (30,'catch');
INSERT INTO `keyword` VALUES (31,'celling');
INSERT INTO `keyword` VALUES (32,'change');
INSERT INTO `keyword` VALUES (33,'char');
INSERT INTO `keyword` VALUES (34,'character');
INSERT INTO `keyword` VALUES (35,'check');
INSERT INTO `keyword` VALUES (36,'class');
INSERT INTO `keyword` VALUES (37,'coalesce');
INSERT INTO `keyword` VALUES (38,'collate');
INSERT INTO `keyword` VALUES (39,'collation');
INSERT INTO `keyword` VALUES (40,'column');
INSERT INTO `keyword` VALUES (41,'comment');
INSERT INTO `keyword` VALUES (42,'commit');
INSERT INTO `keyword` VALUES (43,'concat');
INSERT INTO `keyword` VALUES (44,'const');
INSERT INTO `keyword` VALUES (45,'continue');
INSERT INTO `keyword` VALUES (46,'conv');
INSERT INTO `keyword` VALUES (47,'count');
INSERT INTO `keyword` VALUES (48,'create');
INSERT INTO `keyword` VALUES (49,'cross');
INSERT INTO `keyword` VALUES (50,'data');
INSERT INTO `keyword` VALUES (51,'database');
INSERT INTO `keyword` VALUES (52,'date');
INSERT INTO `keyword` VALUES (53,'datetime');
INSERT INTO `keyword` VALUES (54,'day');
INSERT INTO `keyword` VALUES (55,'dec');
INSERT INTO `keyword` VALUES (56,'decimal');
INSERT INTO `keyword` VALUES (57,'default');
INSERT INTO `keyword` VALUES (58,'delete');
INSERT INTO `keyword` VALUES (59,'desc');
INSERT INTO `keyword` VALUES (60,'describe');
INSERT INTO `keyword` VALUES (61,'distinct');
INSERT INTO `keyword` VALUES (62,'do');
INSERT INTO `keyword` VALUES (63,'double');
INSERT INTO `keyword` VALUES (64,'drop');
INSERT INTO `keyword` VALUES (65,'else');
INSERT INTO `keyword` VALUES (66,'encode');
INSERT INTO `keyword` VALUES (67,'encrypt');
INSERT INTO `keyword` VALUES (68,'end');
INSERT INTO `keyword` VALUES (69,'engine');
INSERT INTO `keyword` VALUES (70,'enum');
INSERT INTO `keyword` VALUES (71,'errors');
INSERT INTO `keyword` VALUES (72,'escape');
INSERT INTO `keyword` VALUES (73,'exists');
INSERT INTO `keyword` VALUES (74,'exp');
INSERT INTO `keyword` VALUES (75,'explain');
INSERT INTO `keyword` VALUES (76,'extends');
INSERT INTO `keyword` VALUES (77,'field');
INSERT INTO `keyword` VALUES (78,'file');
INSERT INTO `keyword` VALUES (79,'final');
INSERT INTO `keyword` VALUES (80,'finally');
INSERT INTO `keyword` VALUES (81,'first');
INSERT INTO `keyword` VALUES (82,'float');
INSERT INTO `keyword` VALUES (83,'floor');
INSERT INTO `keyword` VALUES (84,'flush');
INSERT INTO `keyword` VALUES (85,'for');
INSERT INTO `keyword` VALUES (86,'foreign');
INSERT INTO `keyword` VALUES (87,'format');
INSERT INTO `keyword` VALUES (88,'from');
INSERT INTO `keyword` VALUES (89,'full');
INSERT INTO `keyword` VALUES (90,'fulltext');
INSERT INTO `keyword` VALUES (91,'function');
INSERT INTO `keyword` VALUES (92,'global');
INSERT INTO `keyword` VALUES (93,'goto');
INSERT INTO `keyword` VALUES (94,'grant');
INSERT INTO `keyword` VALUES (95,'greatest');
INSERT INTO `keyword` VALUES (96,'group');
INSERT INTO `keyword` VALUES (97,'having');
INSERT INTO `keyword` VALUES (98,'heap');
INSERT INTO `keyword` VALUES (99,'hex');
INSERT INTO `keyword` VALUES (100,'hosts');
INSERT INTO `keyword` VALUES (101,'hour');
INSERT INTO `keyword` VALUES (102,'identified');
INSERT INTO `keyword` VALUES (103,'if');
INSERT INTO `keyword` VALUES (104,'ifnull');
INSERT INTO `keyword` VALUES (105,'ignore');
INSERT INTO `keyword` VALUES (106,'implements');
INSERT INTO `keyword` VALUES (107,'import');
INSERT INTO `keyword` VALUES (108,'in');
INSERT INTO `keyword` VALUES (109,'index');
INSERT INTO `keyword` VALUES (110,'infile');
INSERT INTO `keyword` VALUES (111,'inner');
INSERT INTO `keyword` VALUES (112,'insert');
INSERT INTO `keyword` VALUES (113,'instanceof');
INSERT INTO `keyword` VALUES (114,'instr');
INSERT INTO `keyword` VALUES (115,'int');
INSERT INTO `keyword` VALUES (116,'integer');
INSERT INTO `keyword` VALUES (117,'interface');
INSERT INTO `keyword` VALUES (118,'interval');
INSERT INTO `keyword` VALUES (119,'into');
INSERT INTO `keyword` VALUES (120,'is');
INSERT INTO `keyword` VALUES (121,'isam');
INSERT INTO `keyword` VALUES (122,'isnull');
INSERT INTO `keyword` VALUES (123,'isolation');
INSERT INTO `keyword` VALUES (124,'join');
INSERT INTO `keyword` VALUES (125,'key');
INSERT INTO `keyword` VALUES (126,'keys');
INSERT INTO `keyword` VALUES (127,'keyword');
INSERT INTO `keyword` VALUES (128,'kill');
INSERT INTO `keyword` VALUES (129,'lcase');
INSERT INTO `keyword` VALUES (130,'leading');
INSERT INTO `keyword` VALUES (131,'least');
INSERT INTO `keyword` VALUES (132,'left');
INSERT INTO `keyword` VALUES (133,'length');
INSERT INTO `keyword` VALUES (134,'level');
INSERT INTO `keyword` VALUES (135,'like');
INSERT INTO `keyword` VALUES (136,'limit');
INSERT INTO `keyword` VALUES (137,'lines');
INSERT INTO `keyword` VALUES (138,'load');
INSERT INTO `keyword` VALUES (139,'local');
INSERT INTO `keyword` VALUES (140,'locate');
INSERT INTO `keyword` VALUES (141,'lock');
INSERT INTO `keyword` VALUES (142,'log');
INSERT INTO `keyword` VALUES (143,'logs');
INSERT INTO `keyword` VALUES (144,'long');
INSERT INTO `keyword` VALUES (145,'longblob');
INSERT INTO `keyword` VALUES (146,'longtext');
INSERT INTO `keyword` VALUES (147,'lower');
INSERT INTO `keyword` VALUES (148,'lpad');
INSERT INTO `keyword` VALUES (149,'ltrim');
INSERT INTO `keyword` VALUES (150,'match');
INSERT INTO `keyword` VALUES (151,'max');
INSERT INTO `keyword` VALUES (152,'md5');
INSERT INTO `keyword` VALUES (153,'meddleint');
INSERT INTO `keyword` VALUES (154,'mediumblob');
INSERT INTO `keyword` VALUES (155,'mediumint');
INSERT INTO `keyword` VALUES (156,'mediumtext');
INSERT INTO `keyword` VALUES (157,'merge');
INSERT INTO `keyword` VALUES (158,'mid');
INSERT INTO `keyword` VALUES (159,'min');
INSERT INTO `keyword` VALUES (160,'minute');
INSERT INTO `keyword` VALUES (161,'mod');
INSERT INTO `keyword` VALUES (162,'modify');
INSERT INTO `keyword` VALUES (163,'month');
INSERT INTO `keyword` VALUES (164,'monthname');
INSERT INTO `keyword` VALUES (165,'myisam');
INSERT INTO `keyword` VALUES (166,'mysql');
INSERT INTO `keyword` VALUES (167,'names');
INSERT INTO `keyword` VALUES (168,'national');
INSERT INTO `keyword` VALUES (169,'native');
INSERT INTO `keyword` VALUES (170,'natural');
INSERT INTO `keyword` VALUES (171,'new');
INSERT INTO `keyword` VALUES (172,'no');
INSERT INTO `keyword` VALUES (173,'not');
INSERT INTO `keyword` VALUES (174,'now');
INSERT INTO `keyword` VALUES (175,'null');
INSERT INTO `keyword` VALUES (176,'nullif');
INSERT INTO `keyword` VALUES (177,'numeric');
INSERT INTO `keyword` VALUES (178,'oct');
INSERT INTO `keyword` VALUES (179,'on');
INSERT INTO `keyword` VALUES (180,'open');
INSERT INTO `keyword` VALUES (181,'optimize');
INSERT INTO `keyword` VALUES (182,'option');
INSERT INTO `keyword` VALUES (183,'optionally');
INSERT INTO `keyword` VALUES (184,'or');
INSERT INTO `keyword` VALUES (185,'ord');
INSERT INTO `keyword` VALUES (186,'order');
INSERT INTO `keyword` VALUES (187,'outer');
INSERT INTO `keyword` VALUES (188,'outfile');
INSERT INTO `keyword` VALUES (189,'package');
INSERT INTO `keyword` VALUES (190,'partial');
INSERT INTO `keyword` VALUES (191,'password');
INSERT INTO `keyword` VALUES (192,'pi');
INSERT INTO `keyword` VALUES (193,'position');
INSERT INTO `keyword` VALUES (194,'pow');
INSERT INTO `keyword` VALUES (195,'power');
INSERT INTO `keyword` VALUES (196,'precision');
INSERT INTO `keyword` VALUES (197,'primary');
INSERT INTO `keyword` VALUES (198,'private');
INSERT INTO `keyword` VALUES (199,'privileges');
INSERT INTO `keyword` VALUES (200,'procedure');
INSERT INTO `keyword` VALUES (201,'process');
INSERT INTO `keyword` VALUES (202,'protected');
INSERT INTO `keyword` VALUES (203,'public');
INSERT INTO `keyword` VALUES (204,'quarter');
INSERT INTO `keyword` VALUES (205,'radians');
INSERT INTO `keyword` VALUES (206,'rand');
INSERT INTO `keyword` VALUES (207,'read');
INSERT INTO `keyword` VALUES (208,'real');
INSERT INTO `keyword` VALUES (209,'reference');
INSERT INTO `keyword` VALUES (210,'regexp');
INSERT INTO `keyword` VALUES (211,'reload');
INSERT INTO `keyword` VALUES (212,'rename');
INSERT INTO `keyword` VALUES (213,'repair');
INSERT INTO `keyword` VALUES (214,'repeat');
INSERT INTO `keyword` VALUES (215,'replace');
INSERT INTO `keyword` VALUES (216,'restore');
INSERT INTO `keyword` VALUES (217,'restrict');
INSERT INTO `keyword` VALUES (218,'return');
INSERT INTO `keyword` VALUES (219,'returns');
INSERT INTO `keyword` VALUES (220,'reverse');
INSERT INTO `keyword` VALUES (221,'revoke');
INSERT INTO `keyword` VALUES (222,'right');
INSERT INTO `keyword` VALUES (223,'rlike');
INSERT INTO `keyword` VALUES (224,'rollback');
INSERT INTO `keyword` VALUES (225,'round');
INSERT INTO `keyword` VALUES (226,'row');
INSERT INTO `keyword` VALUES (227,'rows');
INSERT INTO `keyword` VALUES (228,'rpad');
INSERT INTO `keyword` VALUES (229,'rtrim');
INSERT INTO `keyword` VALUES (230,'schema');
INSERT INTO `keyword` VALUES (231,'second');
INSERT INTO `keyword` VALUES (232,'select');
INSERT INTO `keyword` VALUES (233,'session');
INSERT INTO `keyword` VALUES (234,'set');
INSERT INTO `keyword` VALUES (235,'short');
INSERT INTO `keyword` VALUES (236,'show');
INSERT INTO `keyword` VALUES (237,'sign');
INSERT INTO `keyword` VALUES (238,'sin');
INSERT INTO `keyword` VALUES (239,'smallint');
INSERT INTO `keyword` VALUES (240,'snapshot');
INSERT INTO `keyword` VALUES (241,'soname');
INSERT INTO `keyword` VALUES (242,'soundex');
INSERT INTO `keyword` VALUES (243,'space');
INSERT INTO `keyword` VALUES (244,'sqrt');
INSERT INTO `keyword` VALUES (245,'start');
INSERT INTO `keyword` VALUES (246,'starting');
INSERT INTO `keyword` VALUES (247,'static');
INSERT INTO `keyword` VALUES (248,'status');
INSERT INTO `keyword` VALUES (249,'std');
INSERT INTO `keyword` VALUES (250,'stddev');
INSERT INTO `keyword` VALUES (251,'strcmp');
INSERT INTO `keyword` VALUES (252,'strictfp');
INSERT INTO `keyword` VALUES (253,'string');
INSERT INTO `keyword` VALUES (254,'subdate');
INSERT INTO `keyword` VALUES (255,'sum');
INSERT INTO `keyword` VALUES (256,'super');
INSERT INTO `keyword` VALUES (257,'switch');
INSERT INTO `keyword` VALUES (258,'sysdate');
INSERT INTO `keyword` VALUES (259,'table');
INSERT INTO `keyword` VALUES (260,'tables');
INSERT INTO `keyword` VALUES (261,'tan');
INSERT INTO `keyword` VALUES (262,'temporary');
INSERT INTO `keyword` VALUES (263,'temptable');
INSERT INTO `keyword` VALUES (264,'terminated');
INSERT INTO `keyword` VALUES (265,'text');
INSERT INTO `keyword` VALUES (266,'then');
INSERT INTO `keyword` VALUES (267,'this');
INSERT INTO `keyword` VALUES (268,'throw');
INSERT INTO `keyword` VALUES (269,'throws');
INSERT INTO `keyword` VALUES (270,'time');
INSERT INTO `keyword` VALUES (271,'timestamp');
INSERT INTO `keyword` VALUES (272,'tinyblob');
INSERT INTO `keyword` VALUES (273,'tinyint');
INSERT INTO `keyword` VALUES (274,'tinytext');
INSERT INTO `keyword` VALUES (275,'to');
INSERT INTO `keyword` VALUES (276,'trailing');
INSERT INTO `keyword` VALUES (277,'transient');
INSERT INTO `keyword` VALUES (278,'trim');
INSERT INTO `keyword` VALUES (279,'truncate');
INSERT INTO `keyword` VALUES (280,'try');
INSERT INTO `keyword` VALUES (281,'type');
INSERT INTO `keyword` VALUES (282,'ucase');
INSERT INTO `keyword` VALUES (283,'undefined');
INSERT INTO `keyword` VALUES (284,'unique');
INSERT INTO `keyword` VALUES (285,'unlock');
INSERT INTO `keyword` VALUES (286,'unsigned');
INSERT INTO `keyword` VALUES (287,'update');
INSERT INTO `keyword` VALUES (288,'upper');
INSERT INTO `keyword` VALUES (289,'usage');
INSERT INTO `keyword` VALUES (290,'use');
INSERT INTO `keyword` VALUES (291,'user');
INSERT INTO `keyword` VALUES (292,'using');
INSERT INTO `keyword` VALUES (293,'values');
INSERT INTO `keyword` VALUES (294,'varbinary');
INSERT INTO `keyword` VALUES (295,'varchar');
INSERT INTO `keyword` VALUES (296,'variables');
INSERT INTO `keyword` VALUES (297,'varying');
INSERT INTO `keyword` VALUES (298,'version');
INSERT INTO `keyword` VALUES (299,'view');
INSERT INTO `keyword` VALUES (300,'void');
INSERT INTO `keyword` VALUES (301,'volatile');
INSERT INTO `keyword` VALUES (302,'warnings');
INSERT INTO `keyword` VALUES (303,'week');
INSERT INTO `keyword` VALUES (304,'weekday');
INSERT INTO `keyword` VALUES (305,'when');
INSERT INTO `keyword` VALUES (306,'where');
INSERT INTO `keyword` VALUES (307,'while');
INSERT INTO `keyword` VALUES (308,'with');
INSERT INTO `keyword` VALUES (309,'work');
INSERT INTO `keyword` VALUES (310,'write');
INSERT INTO `keyword` VALUES (311,'year');
INSERT INTO `keyword` VALUES (312,'yearweek');
INSERT INTO `keyword` VALUES (313,'zerofill');

#
# Table structure for table structure
#

CREATE TABLE `structure` (
  `Id` int(6) unsigned NOT NULL auto_increment,
  `tableName` char(20) default NULL,
  `tableDesc` char(50) default NULL,
  `tablelx` char(2) default NULL,
  `tableKey` char(30) default NULL,
  `tableType` char(1) default NULL,
  `tableItem` char(20) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

#
# Dumping data for table structure
#


/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
