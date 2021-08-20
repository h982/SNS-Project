-- MySQL dump 10.13  Distrib 8.0.23, for Win64 (x86_64)
--
-- Host: i5c105.p.ssafy.io    Database: mydb
-- ------------------------------------------------------
-- Server version	8.0.26

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `board`
--

DROP TABLE IF EXISTS `board`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `board` (
  `board_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `title` varchar(100) NOT NULL,
  `contents` varchar(255) NOT NULL,
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `writer` varchar(45) NOT NULL,
  PRIMARY KEY (`board_id`),
  KEY `fk_Board_Group1_idx` (`team_id`),
  CONSTRAINT `fk_Board_Group1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `board`
--

LOCK TABLES `board` WRITE;
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
INSERT INTO `board` VALUES (1,15,'NIKE! NIKE!! NIKE!!!','NIKE, \nNIKE 에 의한,\nNIKE 를 위한 팀입니다.\n명심해 주십시오.','2021-08-18 17:45:50','김준오'),(2,16,'신입회원 필독!','인사하시고 이름 넣으시면 됩니다.','2021-08-18 17:50:45','김조순'),(3,16,'반가워요 여러분 ','화이팅','2021-08-18 17:50:59','김조순'),(4,16,'요번주 챌린지 미달성시 추방입니다!','꼭 확인해주세요','2021-08-18 17:51:23','김조순'),(5,18,'-공지- 이번 주는 8월 22일 일요일로 약속이 미뤄졌습니다.','개인 사정으로 인하여 만남의 시간을 미뤄야하니 양해 부탁드립니다.','2021-08-18 17:57:56','이호황'),(6,19,'-필공지독- 같은 팀이 되신 분들에게..','저희 팀을 찾아주셔서 너무 감사하고, 반갑습니다. \n\n저희 팀은 수영을 잘하거나 못하거나를 떠나 서로 수영을 즐기며 배우기 위해 모인 사람들 입니다. \n\n팀원들과 사이좋게 지내며 서로 싸우지 마시고 옆팀 마린보이즈를 따라 잡읍시다.\n\n1. 약속에 늦을 시 지각비 : 1000원\n2. 불참 시 미리 연락 부탁드립니다.\n3. 팀원과 싸우면 벌금 : 100억','2021-08-18 18:07:04','이호황'),(7,24,'환영입니다. 신입회원 필독!','인삿말을 남겨주세요!','2021-08-18 18:14:14','이조순'),(8,18,'- 이번주 챌린지 안하시면 추방하겠습니다 -','꼭해주세요!','2021-08-18 23:35:39','이호황'),(9,18,'- 신입생공지 - 가입 및 채팅 인사 ','신입분들은 채팅방에 인사랑 간단한 자기소개를 올려주세요. :)\n','2021-08-18 23:43:58','이호황'),(10,28,'[공지] 채팅매너를 지켜주세요!','채팅매너를 지켜주세요!','2021-08-20 01:30:20','차범희'),(11,28,'[공지] 무차별적인 챌린지 도전 금지','무차별적인 챌린지 도전을 금지합니다!','2021-08-20 01:30:49','차범희'),(12,28,'[이벤트] 한달 챌린지 달성시 치킨 기프티콘 ㄱㄱ','러닝 한달 챌린지 달성시 선착순으로 치킨 기프티콘 ㄱㄱ','2021-08-20 01:31:31','차범희');
/*!40000 ALTER TABLE `board` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `challenge`
--

DROP TABLE IF EXISTS `challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenge` (
  `challenge_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `attendance` int NOT NULL,
  `team_count` int NOT NULL,
  `feed_count` int NOT NULL,
  `comment_count` int NOT NULL,
  `attendance_date` date NOT NULL,
  PRIMARY KEY (`challenge_id`),
  KEY `fk_Challenge_Member1_idx` (`member_id`),
  CONSTRAINT `fk_Challenge_Member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `challenge`
--

LOCK TABLES `challenge` WRITE;
/*!40000 ALTER TABLE `challenge` DISABLE KEYS */;
INSERT INTO `challenge` VALUES (16,16,10,4,2,2,'2021-08-18'),(17,17,3,1,3,0,'2021-08-19'),(18,18,2,1,1,0,'2021-08-18'),(19,19,10,2,1,3,'2021-08-18'),(20,20,24,1,1,8,'2021-08-18'),(21,21,2,1,0,0,'2021-08-18'),(22,22,12,1,0,0,'2021-08-19'),(23,23,59,1,2,4,'2021-08-20'),(24,24,9,1,1,0,'2021-08-19'),(25,25,4,1,0,0,'2021-08-19'),(26,26,12,2,1,1,'2021-08-19'),(27,27,2,0,0,0,'2021-08-18'),(28,28,1,0,0,0,'2021-08-18'),(29,29,10,3,10,14,'2021-08-20'),(31,31,2,0,0,0,'2021-08-18'),(32,32,11,2,1,3,'2021-08-19'),(33,33,4,1,1,13,'2021-08-19'),(34,34,6,1,1,2,'2021-08-18'),(35,35,3,0,0,0,'2021-08-19'),(36,36,7,0,0,0,'2021-08-20'),(37,37,1,0,0,0,'2021-08-19'),(38,38,3,0,0,0,'2021-08-20');
/*!40000 ALTER TABLE `challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT,
  `parent_id` int DEFAULT NULL,
  `feed_id` int NOT NULL,
  `member_id` int NOT NULL,
  `contents` varchar(255) NOT NULL,
  `class` int NOT NULL,
  `order` int NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`comment_id`),
  KEY `fk_Comments_Comments_idx` (`parent_id`),
  KEY `fk_Comments_Feed1_idx` (`feed_id`),
  KEY `fk_Comments_Members1_idx` (`member_id`),
  CONSTRAINT `fk_Comments_Comments` FOREIGN KEY (`parent_id`) REFERENCES `comment` (`comment_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Comments_Feed1` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Comments_Members1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (19,NULL,24,29,'ㅋㅋㅋㅋㅋㅋㅋ',1,1,'2021-08-18 18:29:26'),(20,NULL,28,29,'부리부리부리',1,1,'2021-08-18 18:30:40'),(21,NULL,26,29,'ㅋㅋㅋㅋㅋㅋㅋ',1,1,'2021-08-18 18:38:19'),(23,NULL,26,20,'거기 제 자립니다...',1,1,'2021-08-18 18:39:10'),(24,NULL,25,20,'미흡',1,1,'2021-08-18 18:39:23'),(28,NULL,28,20,'왜빠르지?',1,1,'2021-08-18 23:57:38'),(29,NULL,28,20,'ㅋㅋㅋㅋㅋㅋㅋ',1,1,'2021-08-18 23:57:41'),(30,20,28,20,'?',1,1,'2021-08-18 23:57:45'),(31,20,28,20,'??',1,1,'2021-08-18 23:57:58'),(32,28,28,23,'모르시면 어떡해요',1,1,'2021-08-18 23:59:20'),(33,NULL,28,26,'팀장님은 못말려',1,1,'2021-08-19 00:00:27'),(34,29,28,19,'???',1,1,'2021-08-19 00:00:49'),(35,NULL,28,32,'ㅋㅋㅋ너무재밌네요',1,1,'2021-08-19 00:05:41'),(36,NULL,28,32,'ㅋㅋㅋㅋㅋㅋㅋ',1,1,'2021-08-19 00:17:38'),(37,NULL,28,32,'반가워요',1,1,'2021-08-19 00:17:41'),(38,NULL,37,29,'환영합니다',1,1,'2021-08-19 00:19:44'),(39,NULL,37,33,'내가',1,1,'2021-08-19 01:56:22'),(40,NULL,37,33,'이구역의',1,1,'2021-08-19 01:56:24'),(41,NULL,37,33,'킹이야!',1,1,'2021-08-19 01:56:26'),(42,NULL,37,33,'디두킹!',1,1,'2021-08-19 01:56:29'),(43,NULL,37,33,'요리킹!',1,1,'2021-08-19 01:56:30'),(44,NULL,37,33,'조리킹!',1,1,'2021-08-19 01:56:32'),(45,NULL,37,33,'당신은',1,1,'2021-08-19 01:56:38'),(46,NULL,37,33,'내 100원을 앗아갔어',1,1,'2021-08-19 01:56:41'),(47,NULL,37,33,'와 댓글쓰기 힘드네요',1,1,'2021-08-19 01:56:52'),(48,NULL,37,33,'창의력이 부족해서',1,1,'2021-08-19 01:56:56'),(49,NULL,37,33,'팀장못해먹겠다',1,1,'2021-08-19 01:57:00'),(50,NULL,37,33,'11갠데 왜안대징',1,1,'2021-08-19 01:57:27'),(52,NULL,50,20,'해진님 남자였어요?',1,1,'2021-08-19 16:20:32'),(53,52,50,20,'세상에...',1,1,'2021-08-19 16:20:46'),(54,NULL,50,19,'??',1,1,'2021-08-19 16:21:40'),(55,52,50,19,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ',1,1,'2021-08-19 16:21:50'),(56,NULL,50,16,'She is man',1,1,'2021-08-19 16:23:17'),(57,54,50,16,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋ',1,1,'2021-08-19 16:23:26'),(58,NULL,50,34,'ㅋㅋㅋ너무재밌네요',1,1,'2021-08-19 19:23:07'),(59,58,50,34,'ㅋㅋㅋㅋ',1,1,'2021-08-19 19:23:11'),(60,NULL,59,29,'멋있어요',1,1,'2021-08-20 01:28:34'),(61,NULL,59,29,'ㅋㅋㅋㅋㅋ',1,1,'2021-08-20 01:28:36'),(62,NULL,45,29,'멋있어요',1,1,'2021-08-20 01:28:50'),(63,NULL,50,29,'굳굳',1,1,'2021-08-20 01:29:06'),(64,NULL,40,29,'멋있습니다',1,1,'2021-08-20 01:29:22'),(65,NULL,41,29,'언뇽',1,1,'2021-08-20 01:30:09'),(66,NULL,41,29,'멋있어요',1,1,'2021-08-20 01:30:14'),(67,NULL,41,29,'인증확인이요!',1,1,'2021-08-20 01:30:19'),(68,NULL,39,29,'간거 맞나요?',1,1,'2021-08-20 01:30:31'),(69,NULL,39,29,'ㅋㅋㅋㅋㅋㅋ',1,1,'2021-08-20 01:30:32'),(70,NULL,64,23,'오오 사용하시는 어플이 뭔가요?',1,1,'2021-08-20 01:56:32'),(71,NULL,64,24,'나이키 러닝입니당!!',1,1,'2021-08-20 01:57:15'),(72,NULL,60,23,'우와 진짜 이쁘네요!',1,1,'2021-08-20 01:57:26'),(73,NULL,64,23,'오오 저도 사용해 볼래요',1,1,'2021-08-20 02:00:29');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feed`
--

DROP TABLE IF EXISTS `feed`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feed` (
  `feed_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `member_id` int NOT NULL,
  `teamchallenge_id` int DEFAULT NULL,
  `team_name` varchar(45) NOT NULL,
  `contents` varchar(255) NOT NULL,
  `writer` varchar(45) NOT NULL,
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`feed_id`),
  KEY `fk_Feed_GroupChallenge1_idx` (`teamchallenge_id`),
  KEY `fk_Feed_Member1_idx` (`member_id`),
  KEY `fk_Feed_Team1_idx` (`team_id`),
  CONSTRAINT `fk_Feed_GroupChallenge1` FOREIGN KEY (`teamchallenge_id`) REFERENCES `team_challenge` (`teamchallenge_id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_Feed_Member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Feed_Team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feed`
--

LOCK TABLES `feed` WRITE;
/*!40000 ALTER TABLE `feed` DISABLE KEYS */;
INSERT INTO `feed` VALUES (24,18,19,9,'\"마린보이즈\"','제가 곰한테 쫓길 때 가장 빨랐던 것 같아요','이준오','2021-08-18 18:28:43'),(25,18,29,9,'\"마린보이즈\"','수영하러 왔습니다 인증 좀 부탁드려요~~','이조순','2021-08-18 18:29:03'),(26,18,23,NULL,'\"마린보이즈\"','오늘 락커 번호는 77번','차범희','2021-08-18 18:29:35'),(28,18,20,9,'\"마린보이즈\"','오랜만에 수영하니 조금 힘들다..\n\n우사인볼트가 왜 세계에서 가장 빠른 사람인줄 알아요?','이호황','2021-08-18 18:30:14'),(29,15,17,NULL,'\"Team NIKE\"','로고부터 한번 올려봅니다~','김준오','2021-08-18 20:58:15'),(30,15,17,12,'\"Team NIKE\"','나이키 신고 달리는 모습입니다','김준오','2021-08-18 21:02:17'),(31,15,29,12,'\"Team NIKE\"','오늘자 인증입니다','이조순','2021-08-18 21:04:31'),(32,15,18,12,'\"Team NIKE\"','김조순 인증입니다.','김조순','2021-08-18 21:07:03'),(34,15,17,13,'\"Team NIKE\"','다시다시','김준오','2021-08-18 21:29:35'),(37,18,32,14,'\"마린보이즈\"','3주차 인증입니다','구조순','2021-08-19 00:17:25'),(38,26,29,NULL,'\"탁구왕김제빵\"','탁구 인증이요','이조순','2021-08-19 00:27:41'),(39,26,29,NULL,'\"탁구왕김제빵\"','인증이요','이조순','2021-08-19 00:28:01'),(40,26,29,NULL,'\"탁구왕김제빵\"','인증합니다','이조순','2021-08-19 00:28:46'),(41,26,29,NULL,'\"탁구왕김제빵\"','인증이요','이조순','2021-08-19 00:29:03'),(42,21,16,NULL,'\"맨몸운동 모임\"','헬스 인증합니다.','김동현','2021-08-19 00:30:42'),(43,21,16,NULL,'\"맨몸운동 모임\"','달리자이랴','김동현','2021-08-19 00:31:02'),(45,18,29,NULL,'\"마린보이즈\"','수영하러 왔습니다 인증 좀 부탁드려요~~','이조순','2021-08-19 09:22:51'),(50,18,26,NULL,'\"마린보이즈\"','인증합니다','김해진','2021-08-19 14:30:26'),(59,18,34,14,'\"마린보이즈\"','인증입니다.','이조순','2021-08-19 19:22:49'),(60,28,23,NULL,'\"끝까지 달린다\"','갤럭시 워치 산다!! 이거 차고 열심히 달려봅시다~','차범희','2021-08-20 01:28:39'),(61,26,29,NULL,'\"탁구왕김제빵\"','탁구 인증입니다','최조순','2021-08-20 01:31:10'),(62,18,29,NULL,'\"마린보이즈\"','수영인증입니다\n','최조순','2021-08-20 01:31:48'),(63,15,29,12,'\"Team NIKE\"','인증해주세요~~','최조순','2021-08-20 01:33:00'),(64,28,24,NULL,'\"끝까지 달린다\"','러닝일기~~','김범희','2021-08-20 01:34:48');
/*!40000 ALTER TABLE `feed` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedlike`
--

DROP TABLE IF EXISTS `feedlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `feedlike` (
  `feedlike_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `feed_id` int NOT NULL,
  `feed_like` int NOT NULL,
  PRIMARY KEY (`feedlike_id`),
  KEY `fk_Members_has_Feed_Feed1_idx` (`feed_id`),
  KEY `fk_Members_has_Feed_Members1_idx` (`member_id`),
  CONSTRAINT `fk_Members_has_Feed_Feed1` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Members_has_Feed_Members1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=99 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedlike`
--

LOCK TABLES `feedlike` WRITE;
/*!40000 ALTER TABLE `feedlike` DISABLE KEYS */;
INSERT INTO `feedlike` VALUES (40,29,24,1),(42,16,24,1),(44,26,24,1),(45,23,24,1),(46,23,25,1),(47,23,26,1),(48,29,28,1),(49,26,28,1),(50,19,28,1),(52,29,26,1),(53,29,25,1),(55,20,28,1),(56,17,29,1),(57,29,29,1),(58,29,30,1),(59,17,30,1),(60,29,31,1),(62,18,32,1),(63,17,32,1),(64,18,31,1),(67,17,34,1),(68,18,34,1),(74,23,37,1),(75,19,37,1),(76,29,37,1),(77,33,37,1),(80,26,45,1),(83,26,50,1),(84,34,59,1),(85,19,59,1),(86,16,59,1),(87,20,59,1),(88,29,59,1),(89,29,50,1),(90,29,45,1),(91,29,41,1),(92,29,40,1),(93,29,63,1),(94,29,62,1),(95,29,61,1),(97,23,60,1);
/*!40000 ALTER TABLE `feedlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `join_team`
--

DROP TABLE IF EXISTS `join_team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `join_team` (
  `jointeam_id` int NOT NULL AUTO_INCREMENT,
  `Member_id` int NOT NULL,
  `Team_id` int NOT NULL,
  `join_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`jointeam_id`),
  KEY `fk_Members_has_Group_Group1_idx` (`Team_id`),
  KEY `fk_Members_has_Group_Members1_idx` (`Member_id`),
  CONSTRAINT `fk_Members_has_Group_Group1` FOREIGN KEY (`Team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Members_has_Group_Members1` FOREIGN KEY (`Member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=60 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `join_team`
--

LOCK TABLES `join_team` WRITE;
/*!40000 ALTER TABLE `join_team` DISABLE KEYS */;
INSERT INTO `join_team` VALUES (27,17,15,'2021-08-18 17:39:23'),(28,16,15,'2021-08-18 17:41:00'),(30,18,16,'2021-08-18 17:42:34'),(31,19,17,'2021-08-18 17:50:05'),(32,20,18,'2021-08-18 17:53:51'),(33,21,19,'2021-08-18 18:02:25'),(35,16,21,'2021-08-18 18:10:18'),(37,22,23,'2021-08-18 18:12:14'),(38,25,24,'2021-08-18 18:12:21'),(39,26,25,'2021-08-18 18:14:46'),(40,29,26,'2021-08-18 18:18:16'),(41,19,18,'2021-08-18 18:21:55'),(42,29,18,'2021-08-18 18:21:57'),(43,26,18,'2021-08-18 18:23:20'),(44,16,18,'2021-08-18 18:23:22'),(46,29,15,'2021-08-18 21:01:25'),(48,32,18,'2021-08-18 23:15:35'),(49,32,27,'2021-08-19 00:24:11'),(52,16,27,'2021-08-19 00:37:08'),(54,33,18,'2021-08-19 01:42:36'),(55,34,18,'2021-08-19 15:54:05'),(56,23,28,'2021-08-20 01:25:41'),(57,24,28,'2021-08-20 01:32:56');
/*!40000 ALTER TABLE `join_team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mbti`
--

DROP TABLE IF EXISTS `mbti`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mbti` (
  `team_id` int NOT NULL,
  `INFP` int DEFAULT '0',
  `ENFP` int DEFAULT '0',
  `INFJ` int DEFAULT '0',
  `ENFJ` int DEFAULT '0',
  `INTJ` int DEFAULT '0',
  `ENTJ` int DEFAULT '0',
  `INTP` int DEFAULT '0',
  `ENTP` int DEFAULT '0',
  `ISFP` int DEFAULT '0',
  `ESFP` int DEFAULT '0',
  `ISTP` int DEFAULT '0',
  `ESTP` int DEFAULT '0',
  `ISFJ` int DEFAULT '0',
  `ESFJ` int DEFAULT '0',
  `ISTJ` int DEFAULT '0',
  `ESTJ` int DEFAULT '0',
  PRIMARY KEY (`team_id`),
  KEY `fk_Mbti_Team1_idx` (`team_id`),
  CONSTRAINT `fk_Mbti_Team1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mbti`
--

LOCK TABLES `mbti` WRITE;
/*!40000 ALTER TABLE `mbti` DISABLE KEYS */;
INSERT INTO `mbti` VALUES (15,0,0,1,0,0,0,0,0,0,0,0,1,0,0,1,1),(16,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0),(17,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0),(18,1,0,4,0,2,0,0,1,0,0,0,1,1,0,0,0),(19,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0,0),(21,0,0,1,0,0,0,0,0,0,0,0,0,0,0,0,0),(23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,1,0),(24,1,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0),(25,0,0,0,0,1,0,0,0,0,0,0,0,0,0,0,0),(26,0,0,0,0,0,0,0,0,0,0,0,1,0,0,0,0),(27,0,0,3,0,0,0,0,0,0,0,0,2,0,0,0,0),(28,0,0,0,0,0,0,0,0,0,0,0,0,2,2,0,0);
/*!40000 ALTER TABLE `mbti` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `member`
--

DROP TABLE IF EXISTS `member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `member` (
  `member_id` int NOT NULL AUTO_INCREMENT,
  `photo_id` int DEFAULT NULL,
  `name` varchar(45) NOT NULL,
  `email` varchar(128) NOT NULL,
  `phone` varchar(45) NOT NULL,
  `password` varchar(240) NOT NULL,
  `address` varchar(200) NOT NULL,
  `address_detail` varchar(200) NOT NULL,
  `zonecode` varchar(11) NOT NULL,
  `sex` varchar(1) NOT NULL,
  `point` int NOT NULL DEFAULT '0',
  `mbti` varchar(5) NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `authenticated` tinyint(1) NOT NULL,
  PRIMARY KEY (`member_id`),
  KEY `fk_Member_Photo1_idx` (`photo_id`),
  CONSTRAINT `fk_Member_Photo1` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`photo_id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `member`
--

LOCK TABLES `member` WRITE;
/*!40000 ALTER TABLE `member` DISABLE KEYS */;
INSERT INTO `member` VALUES (16,92,'김동현','y7248527@naver.com','01099999999','qwer1234','서울 강서구 강서로 375 (마곡동)','201동 101호','07803','W',56,'infj','2021-08-18 17:37:12',0),(17,54,'김준오','ericharry@naver.com','01087204043','qwer1234','서울 강남구 강남대로 708 (압구정동)','1동1호','06000','M',5,'estj','2021-08-18 17:37:51',0),(18,79,'김조순','sgs1159@nate.com','01087244774','qwer1234','강원 강릉시 강동면 강동초교길 1','101동 202호','25627','M',65,'istj','2021-08-18 17:40:56',0),(19,52,'이준오','harrychoe55@gmail.com','01012345678','qwer1234','부산 해운대구 APEC로 17 (우동)','1동1호','09123','M',26,'isfj','2021-08-18 17:49:02',0),(20,53,'이호황','dlghghkd52@gmail.com','010-1234-1244','qwer1234','광주 광산구 도천남길 41-60 (수완동)','우리집','62244','M',120,'intj','2021-08-18 17:49:28',0),(21,63,'이호황','ghghkd52@naver.com','010-1234-1244','qwer1234','광주 광산구 도천남길 41-60 (수완동)','우리집2','62244','W',0,'esfj','2021-08-18 17:49:50',0),(22,1,'이동현','mevolicus@gmail.com','01055552222','qwer1234','대전 서구 만년남로 3 (만년동)','102동 201호','35200','M',0,'istj','2021-08-18 17:52:37',0),(23,64,'차범희','qqq960909@naver.com','01045330093','1234','광주 광산구 첨단중앙로 7 (월계동)','호반아파트 110동 103호','62271','M',120,'isfj','2021-08-18 17:59:22',0),(24,62,'김범희','q990511@naver.com','01012341234','1234','광주 광산구 첨단중앙로68번길 22 (산월동, 부영아파트)','부영아파트 110동 103호','62283','M',85,'isfj','2021-08-18 18:00:46',0),(25,56,'이조순','sgs1158@naver.com','01087244774','qwer1234','kyunggido','501-206','11703','M',0,'infp','2021-08-18 18:02:57',0),(26,112,'김해진','jhj030812@naver.com','010-1234-5678','1234','광주 서구 상무대로876번길 23 (쌍촌동)','우리집','61989','W',35,'intj','2021-08-18 18:08:26',0),(27,1,'이해진','j__0308@naver.com','010-1234-5678','1234','광주 서구 상무대로876번길 23 (쌍촌동)','우리집','61989','W',0,'istj','2021-08-18 18:10:20',0),(28,1,'박해진','j__i_nn@naver.com','010-1234-5678','1234','광주 서구 상무대로876번길 23 (쌍촌동)','우리집','61989','W',0,'infj','2021-08-18 18:11:24',0),(29,74,'최조순','sgs1159@naver.com','01087244774','qwer1234~!','kyunggido2','501-2062','11703','M',64,'estp','2021-08-18 18:16:55',1),(31,1,'이조순','josun7289@daum.net','01087244774','1234','kyunggido','501-206','11703','M',0,'infj','2021-08-18 23:00:38',1),(32,111,'구조순','sgs1159@gmail.com','01087244774','qwer1234','kyunggido','501-206','11703','M',99,'infj','2021-08-18 23:04:29',1),(33,93,'박디두','arisu0906@naver.com','01049007998','qwer1234','장신대로 20번안길 26 ','행복한집 301호','62226','M',51,'infp','2021-08-19 01:32:37',1),(34,1,'이조순','sgs11592@naver.com','01087244774','qwer1234','kyunggido','501-206','11703','M',1,'infj','2021-08-19 14:52:09',1),(35,104,'Lin kuangxun','rhkdgns0818@naver.com','01082229047','dla0818','광주 북구 용봉로 77 (용봉동)','생활관 9B 331C','61186','M',0,'entp','2021-08-19 17:00:09',0),(36,1,'거북이','qqq960909@gmail.com','01085564221','qwer1234','광주 광산구 첨단중앙로67번길 7-2 (월계동)','부영 310동 1204호','62268','M',0,'isfj','2021-08-20 01:45:13',0),(37,1,'범수','a43727702@gmail.com','01012345678','qwer1234','한국','구미','06044','M',0,'istj','2021-08-20 03:42:44',0),(38,1,'팍범희','qqq960909@likelion.org','01045644564','qwer1234','광주 광산구 첨단중앙로106번길 10 (월계동)','호반타운 202호','62276','M',0,'intj','2021-08-20 09:45:09',0);
/*!40000 ALTER TABLE `member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `message` (
  `message_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `member_id` int NOT NULL,
  `contents` varchar(255) NOT NULL,
  `write_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `writer` varchar(45) NOT NULL,
  PRIMARY KEY (`message_id`),
  KEY `fk_Message_Group1_idx` (`team_id`),
  KEY `fk_Message_Member1_idx` (`member_id`),
  CONSTRAINT `fk_Message_Group1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Message_Member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=174 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
INSERT INTO `message` VALUES (60,15,17,'This is TEAM NIKE','2021-08-18 17:46:19','김준오'),(61,15,17,'NIKE FOR EVERYTHING!','2021-08-18 17:46:36','김준오'),(62,24,25,'ㅋㅋㅋㅋ','2021-08-18 18:14:19','이조순'),(63,24,25,'환영합니다','2021-08-18 18:14:22','이조순'),(64,24,25,'반가워요','2021-08-18 18:14:26','이조순'),(65,26,29,'탁구의신!!','2021-08-18 18:18:25','이조순'),(66,18,32,'안녕하세요 인사드립니다','2021-08-18 23:28:27','구조순'),(67,18,32,'반가워요','2021-08-18 23:28:32','구조순'),(68,18,20,'ㅋㅋㅋ','2021-08-18 23:34:01','이호황'),(69,18,20,'안녕','2021-08-18 23:34:03','이호황'),(70,18,20,'반가워요','2021-08-18 23:34:06','이호황'),(71,18,20,'ㅋㅋㅋㅋ','2021-08-18 23:34:24','이호황'),(72,18,20,'으악','2021-08-18 23:34:26','이호황'),(73,18,20,'오늘 점심 뭐드셨나요?','2021-08-18 23:34:40','이호황'),(74,18,20,'운동만 주구장창 했습니다','2021-08-18 23:34:48','이호황'),(75,18,20,'하하','2021-08-18 23:34:49','이호황'),(76,18,20,'수영 오셔야죠 ㅋㅋㅋ','2021-08-18 23:34:53','이호황'),(77,18,20,'왜 안오세요','2021-08-18 23:34:56','이호황'),(78,18,32,'인사드려요','2021-08-18 23:40:25','구조순'),(79,18,32,'반갑습니다','2021-08-18 23:40:28','구조순'),(80,18,20,'안녕하세요 ㅎㅎ','2021-08-18 23:40:32','이호황'),(81,18,20,'반가워요','2021-08-18 23:40:34','이호황'),(82,18,20,'오늘 점심 뭐드셨나요?','2021-08-18 23:42:10','이호황'),(83,18,20,'샐러드 먹었습니다','2021-08-18 23:42:19','이호황'),(84,18,20,'인증하셔야하는거 아닌가요?','2021-08-18 23:42:24','이호황'),(85,18,20,'이것도 인증할까요? ㅋㅋㅋ','2021-08-18 23:42:30','이호황'),(86,18,20,'넵넵 ㅋㅋㅋ 해주시죠','2021-08-18 23:42:36','이호황'),(87,18,20,'오키 다음 챌린지는 샐러드 인증으로하죠','2021-08-18 23:42:50','이호황'),(88,18,20,'ㅇㅋㅇㅋ','2021-08-18 23:42:51','이호황'),(89,18,20,'ㅋㅋㅋㅋ','2021-08-18 23:42:53','이호황'),(90,18,32,'안녕하세요','2021-08-18 23:44:42','구조순'),(91,18,20,'ㅋㅋ','2021-08-18 23:45:57','이호황'),(92,18,20,' 오늘 점심 뭐드셨나요?','2021-08-18 23:46:38','이호황'),(93,18,20,'샐러드 먹었습니다','2021-08-18 23:46:42','이호황'),(94,18,20,'인증하셔야하는거 아닌가요?','2021-08-18 23:46:46','이호황'),(95,18,20,'이것도 인증할까요? ㅋㅋㅋ','2021-08-18 23:46:50','이호황'),(96,18,20,'넵넵 ㅋㅋㅋ 해주시죠','2021-08-18 23:46:54','이호황'),(97,18,20,'키 다음 챌린지는 샐러드 인증으로하죠','2021-08-18 23:46:58','이호황'),(98,18,20,'좋아요 ㅋㅋㅋ','2021-08-18 23:47:01','이호황'),(99,18,20,'우오앙','2021-08-18 23:47:07','이호황'),(100,18,20,'ㅋㅋㅋㅋ','2021-08-18 23:47:10','이호황'),(101,18,32,'안녕하세요','2021-08-18 23:47:55','구조순'),(102,18,20,'안녕하세요 ㅎㅎ 환영합니다:)','2021-08-18 23:47:58','이호황'),(103,18,32,'반가워용 ㅎㅎ','2021-08-18 23:48:02','구조순'),(104,18,20,'저희도 반가워요','2021-08-18 23:48:08','이호황'),(105,18,32,'안녕하세요','2021-08-18 23:49:43','구조순'),(106,18,20,'안녕하세여 반갑습니다','2021-08-18 23:49:45','이호황'),(107,18,32,'ㅋㅋㅋㅋ','2021-08-18 23:49:48','구조순'),(108,18,20,'ㅋㅋㅋㅋㅋ','2021-08-18 23:49:50','이호황'),(109,18,20,'헤헤','2021-08-18 23:50:36','이호황'),(110,18,20,'반갑다','2021-08-18 23:50:38','이호황'),(111,18,20,'점심뭐먹지','2021-08-18 23:50:42','이호황'),(112,18,20,'후','2021-08-18 23:50:44','이호황'),(113,18,20,'그냥 암거나 주워드셈','2021-08-18 23:50:49','이호황'),(114,18,20,'운동이나가야겠다...','2021-08-18 23:50:56','이호황'),(115,18,20,'근손실 심하네요','2021-08-18 23:51:03','이호황'),(116,18,20,'치킨이나 먹어야지..','2021-08-18 23:51:15','이호황'),(117,18,20,'코딩손실올듯','2021-08-18 23:51:20','이호황'),(118,18,32,'안녕하세요','2021-08-18 23:52:01','구조순'),(119,18,20,'반가워요','2021-08-18 23:52:05','이호황'),(120,18,32,'ㅎㅎ','2021-08-18 23:52:08','구조순'),(121,18,20,'ㅋㅋ','2021-08-18 23:52:10','이호황'),(122,18,20,'ㅋㅋㅋㅋ','2021-08-18 23:54:00','이호황'),(123,18,20,'ㅎㅎㅎㅎ','2021-08-18 23:54:01','이호황'),(124,18,20,'치킨먹고싶다..','2021-08-18 23:54:11','이호황'),(125,18,20,'담부턴 치킨안먹기 인증 챌린지 가시죠','2021-08-18 23:54:24','이호황'),(126,18,20,'그건 불가능...','2021-08-18 23:54:28','이호황'),(127,18,20,'팀탈퇴할게요 ㅋㅋㅋ','2021-08-18 23:54:32','이호황'),(128,18,20,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2021-08-18 23:54:37','이호황'),(129,18,20,'신입분 안오시낭','2021-08-18 23:54:56','이호황'),(130,18,20,'ㅎㅎ','2021-08-18 23:54:57','이호황'),(131,18,32,'안녕하세요','2021-08-18 23:56:30','구조순'),(132,18,20,'어서오세요 :)','2021-08-18 23:56:32','이호황'),(133,18,32,'ㅎㅎ','2021-08-18 23:56:34','구조순'),(134,18,20,'ㅋㅋㅋ','2021-08-18 23:56:37','이호황'),(135,18,19,'담부턴 치킨안먹기 인증 챌린지 가시죠','2021-08-19 00:01:26','이준오'),(136,18,19,'그건 불가능...','2021-08-19 00:01:31','이준오'),(137,18,19,' 팀탈퇴할게요 ...','2021-08-19 00:01:35','이준오'),(138,18,19,'안돼...ㅠ','2021-08-19 00:01:39','이준오'),(139,18,19,'신입분 안오시낭','2021-08-19 00:01:42','이준오'),(140,18,19,'왜요?','2021-08-19 00:01:54','이준오'),(141,18,19,'ㅋㅋㅋㅋ','2021-08-19 00:01:59','이준오'),(142,18,19,'아무나오셔라','2021-08-19 00:02:17','이준오'),(143,18,19,'후','2021-08-19 00:02:20','이준오'),(144,18,19,'ㅋㅋㅋㅋ','2021-08-19 00:02:23','이준오'),(145,18,32,'안녕하세요','2021-08-19 00:04:32','구조순'),(146,18,19,'반가워용 :)','2021-08-19 00:04:34','이준오'),(147,18,32,'ㅋㅋ','2021-08-19 00:04:36','구조순'),(148,18,19,'gㅎㅎ','2021-08-19 00:04:40','이준오'),(149,18,32,'안녕하세요','2021-08-19 00:14:35','구조순'),(150,18,32,'반가워요','2021-08-19 00:14:36','구조순'),(151,18,32,'안녕하세요','2021-08-19 00:15:08','구조순'),(152,18,23,'환영합니다! ㅎㅎ 반가워요~!','2021-08-19 00:15:13','차범희'),(153,18,32,'안녕하세요','2021-08-19 00:16:20','구조순'),(154,18,32,'반가워요','2021-08-19 00:16:22','구조순'),(155,18,23,'환영합니다! ㅎㅎ 반가워요~!','2021-08-19 00:16:24','차범희'),(156,18,23,'ㅋㅋㅋ','2021-08-19 00:16:26','차범희'),(157,18,32,'ㅎㅎ','2021-08-19 00:16:28','구조순'),(158,18,33,'dtd','2021-08-19 01:54:52','박디두'),(159,18,33,'dtd','2021-08-19 01:54:53','박디두'),(160,18,33,'ㅋㅋㅋ 100원 날먹당하고 갑니다','2021-08-19 01:55:00','박디두'),(161,23,22,'asdfa','2021-08-19 15:10:27','이동현'),(162,23,22,'asfdasdf','2021-08-19 15:23:35','이동현'),(163,18,20,'ㅋㅋㅋㅋㅋㅋㅋㅋㅋ','2021-08-19 16:08:03','이호황'),(164,18,34,'안녕하세요~!','2021-08-19 16:08:56','이조순'),(165,18,20,'반갑습니다 환영합니다 이조순님!','2021-08-19 16:08:58','이호황'),(166,18,34,'ㅎㅎ','2021-08-19 16:09:01','이조순'),(167,18,20,'ㅋㅋㅋ','2021-08-19 16:09:04','이호황'),(168,15,29,'ㅋㅋㅋㅋ','2021-08-20 01:29:33','최조순'),(169,15,29,'뭐지','2021-08-20 01:29:38','최조순'),(170,28,23,'어서오세요~~ 환영합니다!!','2021-08-20 01:31:48','차범희'),(171,28,24,'오오 안녕하세용!','2021-08-20 01:33:12','김범희'),(172,28,24,'열심히 활동하겠습니당!!','2021-08-20 01:33:35','김범희');
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `photo`
--

DROP TABLE IF EXISTS `photo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `photo` (
  `photo_id` int NOT NULL AUTO_INCREMENT,
  `feed_id` int DEFAULT NULL,
  `original_filename` varchar(255) NOT NULL,
  `file_path` varchar(255) NOT NULL,
  `file_size` int DEFAULT NULL,
  `image_name` varchar(255) DEFAULT NULL,
  `image_extension` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`photo_id`),
  KEY `fk_Photo_Feed1_idx` (`feed_id`),
  CONSTRAINT `fk_Photo_Feed1` FOREIGN KEY (`feed_id`) REFERENCES `feed` (`feed_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `photo`
--

LOCK TABLES `photo` WRITE;
/*!40000 ALTER TABLE `photo` DISABLE KEYS */;
INSERT INTO `photo` VALUES (1,NULL,'default.jpeg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/member_default.jpeg',NULL,'member_default','jpeg'),(2,NULL,'thumbnail.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/team_default.jpg',NULL,'team_default','jpg'),(46,NULL,'팀 나이키.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/c04a287e-2a10-405a-98f0-b683e31cec07.png',NULL,'c04a287e-2a10-405a-98f0-b683e31cec07','png'),(47,NULL,'health7.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/2b780c2e-2117-47a5-8ee5-b92799eba307.JPG',NULL,'2b780c2e-2117-47a5-8ee5-b92799eba307','JPG'),(49,NULL,'한강 달리기.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/235ba5b6-95f8-4379-940e-a2f3aa897dbb.png',NULL,'235ba5b6-95f8-4379-940e-a2f3aa897dbb','png'),(50,NULL,'마린보이.gif','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/8a904eaf-fd79-4203-a899-eb318b1ba903.gif',NULL,'8a904eaf-fd79-4203-a899-eb318b1ba903','gif'),(52,NULL,'달리기.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/4eda5cc0-77d3-4d55-bec2-8eb766702bca.jpg',NULL,'4eda5cc0-77d3-4d55-bec2-8eb766702bca','jpg'),(53,NULL,'짱구.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/e29d0ffc-a608-4b62-9fc5-4d42eb4805a3.png',NULL,'e29d0ffc-a608-4b62-9fc5-4d42eb4805a3','png'),(54,NULL,'나이키.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/26936d47-ed41-4eae-b6fc-43a047c1fe32.jpg',NULL,'26936d47-ed41-4eae-b6fc-43a047c1fe32','jpg'),(55,NULL,'맥주병.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/9bcd2691-2653-4600-9e32-176f25ebc234.jpg',NULL,'9bcd2691-2653-4600-9e32-176f25ebc234','jpg'),(56,NULL,'조인성.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/76c165bc-1273-49f3-945e-3a7cda63365c.jpg',NULL,'76c165bc-1273-49f3-945e-3a7cda63365c','jpg'),(57,NULL,'수영썸네일.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/ceb3c6db-3ef6-4f79-a1e0-a87faab4dc9b.jpg',NULL,'ceb3c6db-3ef6-4f79-a1e0-a87faab4dc9b','jpg'),(58,NULL,'health1.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/09d3d710-08ed-485f-ab09-7a5179dadba0.jpg',NULL,'09d3d710-08ed-485f-ab09-7a5179dadba0','jpg'),(59,NULL,'탁구썸네일.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/3f57a08b-7c8f-458f-adbe-dcd8bb4b8aa9.jpg',NULL,'3f57a08b-7c8f-458f-adbe-dcd8bb4b8aa9','jpg'),(60,NULL,'health2.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/28ac101e-7d19-4074-8b92-0ce34794a879.jpg',NULL,'28ac101e-7d19-4074-8b92-0ce34794a879','jpg'),(61,NULL,'swim.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/b18cf3a8-3a82-48f4-aad4-fae5f09a1aee.jpg',NULL,'b18cf3a8-3a82-48f4-aad4-fae5f09a1aee','jpg'),(62,NULL,'탁구프로필.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/701596ae-b759-4e20-9b1d-b56a8c6eb533.png',NULL,'701596ae-b759-4e20-9b1d-b56a8c6eb533','png'),(63,NULL,'도라에몽.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/3b9851fe-ee7a-42b2-a1f0-89f8f2466089.jpg',NULL,'3b9851fe-ee7a-42b2-a1f0-89f8f2466089','jpg'),(64,NULL,'수영프로필.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/262d281b-d451-4b1c-9756-0a661cd559ed.png',NULL,'262d281b-d451-4b1c-9756-0a661cd559ed','png'),(65,NULL,'pingpong7.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/e25bd984-5b1b-4837-9ef4-61f99178b77e.JPG',NULL,'e25bd984-5b1b-4837-9ef4-61f99178b77e','JPG'),(66,NULL,'탁구의 신.jfif','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/96afab44-a6c6-487e-a6f8-762874dcc43c.jfif',NULL,'96afab44-a6c6-487e-a6f8-762874dcc43c','jfif'),(69,24,'곰.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/839f1fcd-57f5-4479-9551-11a8a66fdbbf.jpg',NULL,'839f1fcd-57f5-4479-9551-11a8a66fdbbf','jpg'),(70,25,'서강준 수영.jfif','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/3cd442f2-e4f4-4525-ba77-25e565105bce.jfif',NULL,'3cd442f2-e4f4-4525-ba77-25e565105bce','jfif'),(71,26,'락커.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/0e354399-2990-47c1-95db-215555f1d7e9.jpg',NULL,'0e354399-2990-47c1-95db-215555f1d7e9','jpg'),(73,28,'짱구수영.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/38d52366-9627-4fbc-a777-df121201df0b.jpg',NULL,'38d52366-9627-4fbc-a777-df121201df0b','jpg'),(74,NULL,'cha1.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/b5080c8f-bf86-4ff8-9756-c9feb710b0be.jpg',NULL,'b5080c8f-bf86-4ff8-9756-c9feb710b0be','jpg'),(76,30,'달리기.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/3a894aea-3acb-4bd5-8034-c418965ed791.jpg',NULL,'3a894aea-3acb-4bd5-8034-c418965ed791','jpg'),(77,31,'run7.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/ca7c5f74-4a48-4068-afc8-e93f44a76537.JPG',NULL,'ca7c5f74-4a48-4068-afc8-e93f44a76537','JPG'),(78,32,'run2.jfif','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/26de90f9-97cc-418c-845c-f24ef91cc8e0.jfif',NULL,'26de90f9-97cc-418c-845c-f24ef91cc8e0','jfif'),(79,NULL,'다운로드.jfif','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/5e8def7e-6409-4e08-b51e-e2c2d33aedb7.jfif',NULL,'5e8def7e-6409-4e08-b51e-e2c2d33aedb7','jfif'),(81,34,'한강 달리기.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/6c4ce2ae-1636-4da9-bc15-7eaaeb20792a.png',NULL,'6c4ce2ae-1636-4da9-bc15-7eaaeb20792a','png'),(84,37,'swim2.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/63380583-e0a7-48e3-9514-dae68de036ca.jpg',NULL,'63380583-e0a7-48e3-9514-dae68de036ca','jpg'),(85,NULL,'health1.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/435cd1a4-5376-41df-b1fb-f662bd4784a0.JPG',NULL,'435cd1a4-5376-41df-b1fb-f662bd4784a0','JPG'),(86,38,'pingpong9.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/b8cff961-2df7-4684-a44b-bb811f5e3f82.JPG',NULL,'b8cff961-2df7-4684-a44b-bb811f5e3f82','JPG'),(87,39,'pingpong10.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/7203af4a-f159-4765-bd40-0546d6dd3c1b.JPG',NULL,'7203af4a-f159-4765-bd40-0546d6dd3c1b','JPG'),(88,40,'pingpong5.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/32387c9c-797b-4920-bf3b-dd5ae2087a1f.JPG',NULL,'32387c9c-797b-4920-bf3b-dd5ae2087a1f','JPG'),(89,41,'pingpong6.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/cab5f698-38a1-47b3-b4e7-9db6248eb910.JPG',NULL,'cab5f698-38a1-47b3-b4e7-9db6248eb910','JPG'),(90,42,'health6.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/b9be5b9a-58e8-47fe-8e37-8aaedfdfe4cd.JPG',NULL,'b9be5b9a-58e8-47fe-8e37-8aaedfdfe4cd','JPG'),(91,43,'health10.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/fb692dac-b024-4424-a3bf-9392081f2b86.JPG',NULL,'fb692dac-b024-4424-a3bf-9392081f2b86','JPG'),(92,NULL,'health8.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/f7c7b6d0-bcb1-4817-8ede-cb9fece183ef.JPG',NULL,'f7c7b6d0-bcb1-4817-8ede-cb9fece183ef','JPG'),(93,NULL,'standmalang.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/039e88c6-e807-480b-a6bc-ef43f9885430.png',NULL,'039e88c6-e807-480b-a6bc-ef43f9885430','png'),(95,45,'swim.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/a52e9601-bae6-4222-bc69-cc6f66739f08.jpg',NULL,'a52e9601-bae6-4222-bc69-cc6f66739f08','jpg'),(97,29,'곰.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/fcf00b8e-d02e-4c72-9a20-aa51ee7ef45f.jpg',NULL,'fcf00b8e-d02e-4c72-9a20-aa51ee7ef45f','jpg'),(103,50,'swim1.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/6c8b0701-d4ac-4069-9a56-88bd0d3d2bed.jpg',NULL,'6c8b0701-d4ac-4069-9a56-88bd0d3d2bed','jpg'),(104,NULL,'루피.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/9a1d8189-028f-404b-8a8d-d5bbaa247663.png',NULL,'9a1d8189-028f-404b-8a8d-d5bbaa247663','png'),(111,NULL,'다운로드.jfif','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/e4ffb15f-d155-4347-943e-20d94dfb245f.jfif',NULL,'e4ffb15f-d155-4347-943e-20d94dfb245f','jfif'),(112,NULL,'swim5.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/aa7b6426-40d8-4326-b93e-1dfcbc470cd4.jpg',NULL,'aa7b6426-40d8-4326-b93e-1dfcbc470cd4','jpg'),(115,59,'swim2.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/de513bf0-0cf6-4744-9b3a-ca6dad879753.jpg',NULL,'de513bf0-0cf6-4744-9b3a-ca6dad879753','jpg'),(116,NULL,'러닝.PNG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/c13561b5-19fc-4781-b133-856d0b298a8e.PNG',NULL,'c13561b5-19fc-4781-b133-856d0b298a8e','PNG'),(118,60,'dd.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/57db1207-c6fc-4740-821d-7e3e7b2e230f.jpg',NULL,'57db1207-c6fc-4740-821d-7e3e7b2e230f','jpg'),(119,61,'pingpong6.JPG','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/174d5280-6628-472a-9059-d5e37e2e6d08.JPG',NULL,'174d5280-6628-472a-9059-d5e37e2e6d08','JPG'),(120,62,'swim3.jpg','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/3ac7c08b-9348-4ceb-bf1b-f50ce59f437b.jpg',NULL,'3ac7c08b-9348-4ceb-bf1b-f50ce59f437b','jpg'),(121,63,'run6.jfif','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/bb97e513-1770-409c-991b-fe68db9a72d6.jfif',NULL,'bb97e513-1770-409c-991b-fe68db9a72d6','jfif'),(122,64,'ee.png','https://lhh-image.s3.ap-northeast-2.amazonaws.com/static/aaf3d891-970d-4892-a06b-bb78e865b3ba.png',NULL,'aaf3d891-970d-4892-a06b-bb78e865b3ba','png');
/*!40000 ALTER TABLE `photo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `request`
--

DROP TABLE IF EXISTS `request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `request` (
  `request_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `member_id` int NOT NULL,
  `status` int NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`request_id`),
  KEY `fk_Request_Group1_idx` (`team_id`),
  KEY `fk_Request_Member1_idx` (`member_id`),
  CONSTRAINT `fk_Request_Group1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Request_Member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `request`
--

LOCK TABLES `request` WRITE;
/*!40000 ALTER TABLE `request` DISABLE KEYS */;
INSERT INTO `request` VALUES (14,15,16,1,'2021-08-18 17:39:39','2021-08-18 17:41:00'),(16,18,19,1,'2021-08-18 18:21:39','2021-08-18 18:21:55'),(17,18,29,1,'2021-08-18 18:21:44','2021-08-18 18:21:58'),(18,18,26,1,'2021-08-18 18:22:05','2021-08-18 18:23:20'),(19,18,16,1,'2021-08-18 18:22:08','2021-08-18 18:23:22'),(21,15,29,1,'2021-08-18 21:01:09','2021-08-18 21:01:25'),(23,18,32,1,'2021-08-18 23:15:17','2021-08-18 23:15:35'),(27,27,16,1,'2021-08-19 00:35:48','2021-08-19 00:37:09'),(29,18,33,1,'2021-08-19 01:42:14','2021-08-19 01:42:36'),(30,24,33,0,'2021-08-19 14:41:51','2021-08-19 14:41:51'),(32,18,34,1,'2021-08-19 15:53:31','2021-08-19 15:54:05'),(34,18,23,0,'2021-08-20 01:21:39','2021-08-20 01:21:39'),(35,28,24,1,'2021-08-20 01:32:38','2021-08-20 01:32:56');
/*!40000 ALTER TABLE `request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sport`
--

DROP TABLE IF EXISTS `sport`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sport` (
  `sport_id` int NOT NULL AUTO_INCREMENT,
  `sport_name` varchar(45) NOT NULL,
  PRIMARY KEY (`sport_id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sport`
--

LOCK TABLES `sport` WRITE;
/*!40000 ALTER TABLE `sport` DISABLE KEYS */;
INSERT INTO `sport` VALUES (1,'러닝'),(2,'헬스'),(3,'수영'),(4,'탁구');
/*!40000 ALTER TABLE `sport` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team`
--

DROP TABLE IF EXISTS `team`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team` (
  `team_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `sport_id` int NOT NULL,
  `photo_id` int NOT NULL,
  `name` varchar(45) NOT NULL,
  `introduction` varchar(255) NOT NULL,
  `leader` varchar(45) NOT NULL,
  `member_count` int DEFAULT NULL,
  `img_path` varchar(255) DEFAULT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`team_id`),
  KEY `fk_Group_Member1_idx` (`member_id`),
  KEY `fk_Group_Sport1_idx` (`sport_id`),
  KEY `fk_Team_Photo1_idx` (`photo_id`),
  CONSTRAINT `fk_Group_Member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE RESTRICT ON UPDATE CASCADE,
  CONSTRAINT `fk_Group_Sport1` FOREIGN KEY (`sport_id`) REFERENCES `sport` (`sport_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Team_Photo1` FOREIGN KEY (`photo_id`) REFERENCES `photo` (`photo_id`) ON DELETE RESTRICT ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team`
--

LOCK TABLES `team` WRITE;
/*!40000 ALTER TABLE `team` DISABLE KEYS */;
INSERT INTO `team` VALUES (15,17,1,46,'\"Team NIKE\"','\"나이키를 사랑하는 러닝 모임입니다. 아디다스 X 무조건 나이키만 입고 오셔야 합니다. 어느 연령대나 상관없이 같이 나이키~ 합시다!!\"','\"김준오\"',3,NULL,'2021-08-18 17:39:23'),(16,18,2,47,'\"헬스의신!\"','\"헬을 맛보고싶나\"','\"김조순\"',1,NULL,'2021-08-18 17:42:34'),(17,19,1,49,'\"한강원정대\"','\"매주 주말마다 한강을 달리는 모임입니다~\"','\"이준오\"',1,NULL,'2021-08-18 17:50:05'),(18,20,3,50,'\"마린보이즈\"','\"매주 토요일 오후 2시 야무지게 수영하실 분 환영합니다!!\"','\"이호황\"',8,NULL,'2021-08-18 17:53:51'),(19,21,3,55,'\"맥주병모임\"','\"팀장도 수영 잘 못합니다. 같이 배워요!\"','\"이호황\"',1,NULL,'2021-08-18 18:02:25'),(21,16,2,58,'\"맨몸운동 모임\"','\"맨몸운동으로 빡세게 운동\"','\"김동현\"',1,NULL,'2021-08-18 18:10:18'),(23,22,2,60,'\"헬스 집중\"','\"하체 집중!!\"','\"이동현\"',1,NULL,'2021-08-18 18:12:14'),(24,25,3,61,'\"워터그자체\"','\"수영왕이 되기 위해\"','\"이조순\"',1,NULL,'2021-08-18 18:12:21'),(25,26,4,65,'\"핑퐁핑퐁\"','\"정기적으로 모여서 탁구쳐요!\"','\"김해진\"',1,NULL,'2021-08-18 18:14:46'),(26,29,4,66,'\"탁구왕김제빵\"','\"탁구의 신이 되자\"','\"이조순\"',1,NULL,'2021-08-18 18:18:16'),(27,32,2,85,'\"3대 500찍을때까지\"','\"헬을 맛보고싶나\"','\"구조순\"',2,NULL,'2021-08-19 00:24:11'),(28,23,1,116,'\"끝까지 달린다\"','\"끝까지 달리기 때문에..\"','차범희',2,NULL,'2021-08-20 01:25:41');
/*!40000 ALTER TABLE `team` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_challenge`
--

DROP TABLE IF EXISTS `team_challenge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_challenge` (
  `teamchallenge_id` int NOT NULL AUTO_INCREMENT,
  `team_id` int NOT NULL,
  `title` varchar(128) NOT NULL,
  `contents` varchar(255) NOT NULL,
  `status` int NOT NULL,
  `member_count` int NOT NULL,
  `goal_count` int NOT NULL,
  `start_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `end_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`teamchallenge_id`),
  KEY `fk_GroupChallenge_Group1_idx` (`team_id`),
  CONSTRAINT `fk_GroupChallenge_Group1` FOREIGN KEY (`team_id`) REFERENCES `team` (`team_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_challenge`
--

LOCK TABLES `team_challenge` WRITE;
/*!40000 ALTER TABLE `team_challenge` DISABLE KEYS */;
INSERT INTO `team_challenge` VALUES (8,15,'오늘의 NIKE인증하기','오늘 입은 NIKE 사진을 찍어주시면 됩니다.',1,1,1,'2021-08-19 00:00:00','2021-08-21 00:00:00'),(9,18,'우솨인볼트가 끝까지 갔다.','자유형 100m 자기 최고기록을 인증해주세요',1,1,3,'2021-08-22 00:00:00','2021-09-27 00:00:00'),(10,19,'50m 완주','50m를 완주하고 인증샷을 남겨주세요!',1,1,0,'2021-08-22 00:00:00','2021-09-05 00:00:00'),(11,24,'요번주 수영장 인증하시면 됩니다!','수영장에서 사진 찍어 주세요!',1,1,0,'2021-08-23 00:00:00','2021-08-27 00:00:00'),(12,15,'나이키 글 하나 쓰기','글 하나 더 써주세요',1,1,3,'2021-08-19 00:00:00','2021-08-21 00:00:00'),(13,15,'나이키 테스트 중','이번에는 아무거나 올려주세여',1,1,1,'2021-08-19 00:00:00','2021-08-21 00:00:00'),(14,18,'8월 3째주 수영장가기 인증!','수영장을 가봅시당!',1,1,4,'2021-08-19 00:00:00','2021-08-25 00:00:00'),(15,28,'매일매일 3km러닝','매일 3km씩 한달동안 달립시다!',1,1,0,'2021-08-21 00:00:00','2021-09-22 00:00:00'),(16,28,'싸피 마라톤 도전','싸피 마라톤 준비 및 도전',1,1,0,'2021-08-21 00:00:00','2021-11-04 00:00:00');
/*!40000 ALTER TABLE `team_challenge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `team_challenger`
--

DROP TABLE IF EXISTS `team_challenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `team_challenger` (
  `teamchallenger_id` int NOT NULL AUTO_INCREMENT,
  `member_id` int NOT NULL,
  `teamchallenge_id` int NOT NULL,
  `done` tinyint NOT NULL,
  PRIMARY KEY (`teamchallenger_id`),
  KEY `fk_Member_has_GroupChallenge_GroupChallenge1_idx` (`teamchallenge_id`),
  KEY `fk_Member_has_GroupChallenge_Member1_idx` (`member_id`),
  CONSTRAINT `fk_Member_has_GroupChallenge_GroupChallenge1` FOREIGN KEY (`teamchallenge_id`) REFERENCES `team_challenge` (`teamchallenge_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_Member_has_GroupChallenge_Member1` FOREIGN KEY (`member_id`) REFERENCES `member` (`member_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `team_challenger`
--

LOCK TABLES `team_challenger` WRITE;
/*!40000 ALTER TABLE `team_challenger` DISABLE KEYS */;
INSERT INTO `team_challenger` VALUES (17,21,10,0),(18,25,11,0),(19,19,9,1),(20,29,9,1),(21,26,9,0),(24,29,12,1),(25,18,12,1),(36,32,14,1),(39,33,14,1),(40,34,14,1),(41,29,14,0),(42,23,15,0),(43,23,16,0);
/*!40000 ALTER TABLE `team_challenger` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-20  9:55:34
