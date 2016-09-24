-- MySQL dump 10.13  Distrib 5.7.12, for osx10.11 (x86_64)
--
-- Host: localhost    Database: courselearning
-- ------------------------------------------------------
-- Server version	5.7.12

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bbs`
--

DROP TABLE IF EXISTS `bbs`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bbs` (
  `bbs_id` int(5) NOT NULL AUTO_INCREMENT,
  `class_id` int(5) DEFAULT NULL,
  `event_id` int(5) DEFAULT NULL,
  `bbs_content` varchar(255) DEFAULT NULL,
  `student_id` int(5) DEFAULT NULL,
  `teacher_id` int(5) DEFAULT NULL,
  `bbs_date` datetime DEFAULT NULL,
  `reply_id` int(5) DEFAULT NULL,
  PRIMARY KEY (`bbs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bbs`
--

LOCK TABLES `bbs` WRITE;
/*!40000 ALTER TABLE `bbs` DISABLE KEYS */;
INSERT INTO `bbs` VALUES (1,1,3,'呵呵呵',-1,1,'2016-09-05 06:30:51',-1),(2,1,3,'哈哈哈',-1,1,'2016-09-05 06:54:18',-1),(3,1,3,'嘿嘿嘿\r\n',-1,1,'2016-09-05 06:54:43',1),(4,1,3,'咩咩咩',1,-1,'2016-09-05 07:46:02',1);
/*!40000 ALTER TABLE `bbs` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course`
--

DROP TABLE IF EXISTS `course`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `course` (
  `course_id` int(3) NOT NULL AUTO_INCREMENT,
  `course_name` varchar(255) DEFAULT NULL,
  `course_classcapacity` int(2) DEFAULT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course` DISABLE KEYS */;
INSERT INTO `course` VALUES (1,'操作系统',3),(5,'数据结构',4),(7,'机器学习',4),(9,'算法',3);
/*!40000 ALTER TABLE `course` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `coursestudent`
--

DROP TABLE IF EXISTS `coursestudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `coursestudent` (
  `coursestudent_id` int(5) NOT NULL AUTO_INCREMENT,
  `course_id` int(3) DEFAULT NULL,
  `student_id` int(3) DEFAULT NULL,
  `class_id` int(2) DEFAULT NULL,
  PRIMARY KEY (`coursestudent_id`),
  KEY `course_id` (`course_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `coursestudent_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `coursestudent_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `coursestudent`
--

LOCK TABLES `coursestudent` WRITE;
/*!40000 ALTER TABLE `coursestudent` DISABLE KEYS */;
INSERT INTO `coursestudent` VALUES (1,1,1,1),(3,1,3,1),(4,1,4,1),(5,1,5,1),(7,1,2,1),(8,5,1,1);
/*!40000 ALTER TABLE `coursestudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `courseteacher`
--

DROP TABLE IF EXISTS `courseteacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `courseteacher` (
  `courseteacher_id` int(5) NOT NULL AUTO_INCREMENT,
  `course_id` int(3) DEFAULT NULL,
  `teacher_id` int(3) DEFAULT NULL,
  `class_id` int(3) DEFAULT NULL,
  PRIMARY KEY (`courseteacher_id`),
  KEY `course_id` (`course_id`),
  KEY `teacher_id` (`teacher_id`),
  CONSTRAINT `courseteacher_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `courseteacher_ibfk_2` FOREIGN KEY (`teacher_id`) REFERENCES `teacher` (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `courseteacher`
--

LOCK TABLES `courseteacher` WRITE;
/*!40000 ALTER TABLE `courseteacher` DISABLE KEYS */;
INSERT INTO `courseteacher` VALUES (1,1,1,1),(2,5,1,1),(5,7,1,1),(6,7,2,2),(7,9,1,1),(8,9,2,2);
/*!40000 ALTER TABLE `courseteacher` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `event`
--

DROP TABLE IF EXISTS `event`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `event` (
  `event_id` int(5) NOT NULL AUTO_INCREMENT,
  `class_id` int(5) DEFAULT NULL,
  `section_id` int(5) DEFAULT NULL,
  `event_content` varchar(255) DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL,
  `starttime` datetime DEFAULT NULL,
  `endtime` datetime DEFAULT NULL,
  PRIMARY KEY (`event_id`),
  KEY `section_id` (`section_id`),
  CONSTRAINT `event_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `event`
--

LOCK TABLES `event` WRITE;
/*!40000 ALTER TABLE `event` DISABLE KEYS */;
INSERT INTO `event` VALUES (3,1,1,'测试','听课','2016-09-04 02:14:27','2016-10-02 12:00:00'),(4,1,1,'测试2','听课','2016-09-05 06:56:04','2016-12-31 12:00:00');
/*!40000 ALTER TABLE `event` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homework`
--

DROP TABLE IF EXISTS `homework`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homework` (
  `homework_id` int(5) NOT NULL AUTO_INCREMENT,
  `course_id` int(5) DEFAULT NULL,
  `class_id` int(5) DEFAULT NULL,
  `section_id` int(5) DEFAULT NULL,
  `homework_title` varchar(255) DEFAULT NULL,
  `homework_content` varchar(255) DEFAULT NULL,
  `homework_starttime` datetime DEFAULT NULL,
  `homework_endtime` datetime DEFAULT NULL,
  `homework_accessory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`homework_id`),
  KEY `course_id` (`course_id`),
  KEY `section_id` (`section_id`),
  CONSTRAINT `homework_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`),
  CONSTRAINT `homework_ibfk_2` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homework`
--

LOCK TABLES `homework` WRITE;
/*!40000 ALTER TABLE `homework` DISABLE KEYS */;
INSERT INTO `homework` VALUES (3,1,1,1,'作业1','测试','2016-07-12 22:04:10','2016-08-25 15:44:48',NULL),(6,1,1,2,'作业3','作业','2016-07-12 10:04:10','2016-08-25 03:44:48',NULL);
/*!40000 ALTER TABLE `homework` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `homeworkstudent`
--

DROP TABLE IF EXISTS `homeworkstudent`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `homeworkstudent` (
  `homeworkstudent_id` int(5) NOT NULL AUTO_INCREMENT,
  `homework_id` int(5) DEFAULT NULL,
  `student_id` int(5) DEFAULT NULL,
  `homeworkstudent_comment` varchar(255) DEFAULT NULL,
  `homeworkstudent_accessory` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`homeworkstudent_id`),
  KEY `homework_id` (`homework_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `homeworkstudent_ibfk_1` FOREIGN KEY (`homework_id`) REFERENCES `homework` (`homework_id`),
  CONSTRAINT `homeworkstudent_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `homeworkstudent`
--

LOCK TABLES `homeworkstudent` WRITE;
/*!40000 ALTER TABLE `homeworkstudent` DISABLE KEYS */;
/*!40000 ALTER TABLE `homeworkstudent` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `knowledgeweight`
--

DROP TABLE IF EXISTS `knowledgeweight`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `knowledgeweight` (
  `knowledgeweight_id` int(3) NOT NULL AUTO_INCREMENT,
  `section_id` int(3) DEFAULT NULL,
  `listening_weight` float DEFAULT NULL,
  `answer_weight` float DEFAULT NULL,
  `attendance_weight` float DEFAULT NULL,
  `homework_weight` float DEFAULT NULL,
  `experiment_weight` float DEFAULT NULL,
  `reviewandpreview_weight` float DEFAULT NULL,
  PRIMARY KEY (`knowledgeweight_id`),
  KEY `section_id` (`section_id`),
  CONSTRAINT `knowledgeweight_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `knowledgeweight`
--

LOCK TABLES `knowledgeweight` WRITE;
/*!40000 ALTER TABLE `knowledgeweight` DISABLE KEYS */;
INSERT INTO `knowledgeweight` VALUES (1,1,0.2,0.2,0.1,0.2,0.2,0.1),(2,2,0.3,0.18,0.12,0.16,0.16,0.08),(3,6,0.18,0.24,0.18,0.12,0.2,0.08),(4,29,0.18,0.24,0.18,0.12,0.2,0.08);
/*!40000 ALTER TABLE `knowledgeweight` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `learningstatus`
--

DROP TABLE IF EXISTS `learningstatus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `learningstatus` (
  `learningstatus_id` int(5) NOT NULL AUTO_INCREMENT,
  `class_id` int(5) DEFAULT NULL,
  `student_id` int(5) DEFAULT NULL,
  `event_id` int(5) DEFAULT NULL,
  `classtime` int(3) DEFAULT NULL,
  `inclass` int(3) DEFAULT NULL,
  `outclass` int(3) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`learningstatus_id`),
  KEY `student_id` (`student_id`),
  KEY `event_id` (`event_id`),
  CONSTRAINT `learningstatus_ibfk_1` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`),
  CONSTRAINT `learningstatus_ibfk_2` FOREIGN KEY (`event_id`) REFERENCES `event` (`event_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `learningstatus`
--

LOCK TABLES `learningstatus` WRITE;
/*!40000 ALTER TABLE `learningstatus` DISABLE KEYS */;
/*!40000 ALTER TABLE `learningstatus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `manager_id` int(5) NOT NULL AUTO_INCREMENT,
  `manager_name` varchar(255) DEFAULT NULL,
  `manager_loginname` varchar(255) DEFAULT NULL,
  `manager_password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`manager_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'administrator','administrator','administrator');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `method`
--

DROP TABLE IF EXISTS `method`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `method` (
  `method_id` int(5) NOT NULL AUTO_INCREMENT,
  `method_name` varchar(255) DEFAULT NULL,
  `method_comment` varchar(255) DEFAULT NULL,
  `method_path` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`method_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `method`
--

LOCK TABLES `method` WRITE;
/*!40000 ALTER TABLE `method` DISABLE KEYS */;
INSERT INTO `method` VALUES (1,'K-Means','聚类算法','main.py');
/*!40000 ALTER TABLE `method` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `section`
--

DROP TABLE IF EXISTS `section`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `section` (
  `section_id` int(3) NOT NULL AUTO_INCREMENT,
  `course_id` int(3) DEFAULT NULL,
  `section_name` varchar(255) DEFAULT NULL,
  `section_weight` float DEFAULT NULL,
  PRIMARY KEY (`section_id`),
  KEY `course_id` (`course_id`),
  CONSTRAINT `section_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `section`
--

LOCK TABLES `section` WRITE;
/*!40000 ALTER TABLE `section` DISABLE KEYS */;
INSERT INTO `section` VALUES (1,1,'操作系统概述',0.4),(2,1,'操作系统的硬件环境',0.3),(6,1,'用户接口与作业管理',0.2),(25,5,'导论',1),(29,1,'测试',0.1);
/*!40000 ALTER TABLE `section` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sectionscore`
--

DROP TABLE IF EXISTS `sectionscore`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sectionscore` (
  `sectionscore_id` int(5) NOT NULL AUTO_INCREMENT,
  `section_id` int(3) DEFAULT NULL,
  `student_id` int(5) DEFAULT NULL,
  `listening` float DEFAULT NULL,
  `answer` float DEFAULT NULL,
  `attendance` float DEFAULT NULL,
  `homework` float DEFAULT NULL,
  `experiment` float DEFAULT NULL,
  `reviewandpreview` float DEFAULT NULL,
  PRIMARY KEY (`sectionscore_id`),
  KEY `section_id` (`section_id`),
  KEY `student_id` (`student_id`),
  CONSTRAINT `sectionscore_ibfk_1` FOREIGN KEY (`section_id`) REFERENCES `section` (`section_id`),
  CONSTRAINT `sectionscore_ibfk_2` FOREIGN KEY (`student_id`) REFERENCES `student` (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sectionscore`
--

LOCK TABLES `sectionscore` WRITE;
/*!40000 ALTER TABLE `sectionscore` DISABLE KEYS */;
INSERT INTO `sectionscore` VALUES (1,1,1,70.5,31,34.5,90,0,23.5),(2,1,2,70,34,22,92,10,66.5),(3,1,3,70,34,12,23,0,68.5),(4,1,4,70,44,22,23,30,38.5),(5,1,5,40,10,0,100,100,0),(6,2,1,65,34,20.5,23,40,50),(7,2,2,57,40,30.5,53,60,100),(8,2,3,60,40,40.5,33,30,90),(9,2,4,50,30,50.5,23,40,80),(10,2,5,52,33,51.5,33,90,20),(11,29,1,-1,-1,-1,-1,-1,-1),(12,29,2,-1,-1,-1,-1,-1,-1),(13,29,3,-1,-1,-1,-1,-1,-1),(14,29,4,-1,-1,-1,-1,-1,-1),(15,29,5,-1,-1,-1,-1,-1,-1);
/*!40000 ALTER TABLE `sectionscore` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `student`
--

DROP TABLE IF EXISTS `student`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `student` (
  `student_id` int(5) NOT NULL AUTO_INCREMENT,
  `student_number` varchar(255) DEFAULT NULL,
  `student_password` varchar(255) DEFAULT NULL,
  `student_name` varchar(255) DEFAULT NULL,
  `student_gender` varchar(255) DEFAULT NULL,
  `student_head` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `student`
--

LOCK TABLES `student` WRITE;
/*!40000 ALTER TABLE `student` DISABLE KEYS */;
INSERT INTO `student` VALUES (1,'2013210873','12345678','张三','2013','zhangsan'),(2,'2013210123','12345678','李四','2013','lisi'),(3,'2013210133','12345678','王五','2013','wangwu'),(4,'2013210143','12345678','赵六','2013','zhaoliu'),(5,'2013210223','12345678','钱七','2013','qianqi'),(9,'2013210954','12345678','小明','2013','null');
/*!40000 ALTER TABLE `student` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `teacher`
--

DROP TABLE IF EXISTS `teacher`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `teacher` (
  `teacher_id` int(3) NOT NULL AUTO_INCREMENT,
  `teacher_loginname` varchar(255) DEFAULT NULL,
  `teacher_password` varchar(255) DEFAULT NULL,
  `teacher_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `teacher`
--

LOCK TABLES `teacher` WRITE;
/*!40000 ALTER TABLE `teacher` DISABLE KEYS */;
INSERT INTO `teacher` VALUES (1,'os','os','张老师'),(2,'ly','ly','李老师'),(5,'ls','12345678','王老师');
/*!40000 ALTER TABLE `teacher` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-09-24 22:05:08
