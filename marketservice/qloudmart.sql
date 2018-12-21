-- MySQL dump 10.16  Distrib 10.1.30-MariaDB, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: qloudmart
-- ------------------------------------------------------
-- Server version	10.1.30-MariaDB-1~jessie

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
-- Table structure for table `tb_account`
--

DROP TABLE IF EXISTS `tb_account`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_account` (
  `id` varchar(32) NOT NULL COMMENT '账户ID',
  `name` varchar(64) DEFAULT NULL COMMENT '账户名',
  `hook` varchar(256) DEFAULT NULL COMMENT '通知地址',
  `contact_num` varchar(24) DEFAULT NULL COMMENT '联系电话',
  `email` varchar(128) DEFAULT NULL COMMENT '电子邮箱',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_account`
--

LOCK TABLES `tb_account` WRITE;
/*!40000 ALTER TABLE `tb_account` DISABLE KEYS */;
INSERT INTO `tb_account` VALUES ('10001','测试账户','http://192.168.1.145:32091/deployer/notify','123456','123456@139.com','N'),('10002','test2','','123456','123456@139.com','N'),('MYCMB','MYCMB','http://192.168.1.12:30650/deployer/notify','123456','123456@139.com','N'),('Test','测试账户','http://192.168.1.145:32091/deployer/notify',NULL,NULL,'N');
/*!40000 ALTER TABLE `tb_account` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_account_attr`
--

DROP TABLE IF EXISTS `tb_account_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_account_attr` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `account_id` varchar(32) NOT NULL,
  `attr_key` varchar(32) NOT NULL,
  `attr_value` varchar(256) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  `attr_type` varchar(64) DEFAULT NULL,
  `attr_desc` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`,`account_id`,`attr_key`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_account_attr`
--

LOCK TABLES `tb_account_attr` WRITE;
/*!40000 ALTER TABLE `tb_account_attr` DISABLE KEYS */;
INSERT INTO `tb_account_attr` VALUES (1,'10001','buy_addr','http://marketdeployer:8091/deployer/notify','N','DEP_ADDR','部署地址1'),(1,'10001','try_addr','http://marketdeployer:8091/deployer/notify','N','DEP_ADDR','部署地址2');
/*!40000 ALTER TABLE `tb_account_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_artifact`
--

DROP TABLE IF EXISTS `tb_artifact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_artifact` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL COMMENT '组件链接',
  `product_id` varchar(32) DEFAULT NULL,
  `artifact_type` int(12) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  `tag` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_id` (`product_id`),
  KEY `fk_type` (`artifact_type`),
  CONSTRAINT `fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`),
  CONSTRAINT `fk_type` FOREIGN KEY (`artifact_type`) REFERENCES `tb_artifact_type` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=83 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_artifact`
--

LOCK TABLES `tb_artifact` WRITE;
/*!40000 ALTER TABLE `tb_artifact` DISABLE KEYS */;
INSERT INTO `tb_artifact` VALUES (6,'ID-SERVICE','qloud/id-service','571911509110689792',1,'N','1.1.4'),(7,'CAS','qloudid/cas','571911509110689792',1,'N','1.0'),(8,'gerrit','qlouddop/gerrit','571828364302618630',1,'N','2.15.3'),(9,'jenkins','qlouddop/jenkins','571828364302618629',1,'N','1.4'),(12,'DOP','qlouddop/qlouddop-hub','571828364302618624',1,'N','2.2.14'),(13,'arrange','qloudtest/arrange','571829434831605760',1,'N','1.0.4'),(14,'pluginUI','qloudtest/standalone-chrome','571910296180887552',1,'N','latest'),(15,'engine','qloudtest/engine','571829128651608064',1,'N','1.0.35'),(16,'qloudtest/web','qloudtest/web','571829434831605760',1,'N','1.0.31'),(17,'qloud-itg-mock','qloudtest/store','571829434831605760',1,'N','1.0.5'),(19,'jenkinsChart','jenkins-1.0.1.tgz','571828364302618629',2,'N',NULL),(20,'DOPChart','qlouddop-1.0.1.tgz','571828364302618624',2,'N',NULL),(21,'ssoChart','sso-1.0.1.tgz','571911509110689792',2,'N',NULL),(22,'testChart','qloud-test-1.0.0.tgz','571829434831605760',2,'N',NULL),(23,'engineChart','qloudtest-engine-1.0.0.tgz','571829128651608064',2,'N',NULL),(24,'uiChart','qloudtest-ui-1.0.1.tgz','571910296180887552',2,'N',NULL),(25,'gerritChart','gerrit-1.0.1.tgz','571828364302618630',2,'N',NULL),(26,'qloud/consul','qloud/consul','575852507633291264',1,'Y','1.2.2'),(27,'qloud/vault','qloud/vault','575852507633291264',1,'Y','0.11.0'),(28,'qloud/security','qloud/security','575852507633291264',1,'Y','stable'),(29,'qloud/config','qloud/config','575852507633291264',1,'Y','stable'),(30,'qloudservice-conductor','qloudservice-conductor-1.0.1.tgz','575852507633291264',2,'N',NULL),(31,'qloudapi','qloud-api-1.0.1.tgz','575888592824438784',2,'N',NULL),(33,'qloud/conductor-bridge','qloudservice/qloudconductor-bridge','575852507633291264',1,'N','stable'),(34,'qloudcdc-mariadb','qloudservice/qloudcdc-mariadb','575852507633291264',1,'N','stable'),(35,'qloudservice/qloudservice-qloudsaga-reliability-rollback','qloudservice/qloudservice-qloudsaga-reliability-rollback','575852507633291264',1,'N','stable'),(36,'qloudconductor-sagabridge','qloudservice/qloudconductor-sagabridge','575852507633291264',1,'Y','stable'),(37,'qloudsaga-reliability-start','qloudservice/qloudservice-qloudsaga-reliability-start','575852507633291264',1,'N','stable'),(38,'qloudsaga-reliability-task1','qloudservice/qloudsaga-reliability-task1','575852507633291264',1,'Y','stable'),(39,'qloudsaga-reliability-task2','qloudservice/qloudsaga-reliability-task2','575852507633291264',1,'Y','stable'),(40,'qloudconfig','qloudservice/qloudconfig','575852507633291264',1,'Y','stable'),(41,'qlouddiscovery','qloudservice/qlouddiscovery','575852507633291264',1,'Y','stable'),(42,'qloudsecurity','qloudservice/qloudsecurity','575852507633291264',1,'Y','stable'),(43,'qloudstomp','qloudservice/qloudstomp','575852507633291264',1,'N','stable'),(44,'qloudapi/qloudwebui-onlyapi','qloudapi/qloudwebui-onlyapi','575852507633291264',1,'N','latest'),(45,'qloudapi/qloudapi','qloudapi/qloudapi','575888592824438784',1,'N','stable'),(46,'qloudapi/qloudauth','qloudapi/qloudauth','575888592824438784',1,'N','stable'),(47,'qloudapi/qloudgrid','qloudapi/qloudgrid','575888592824438784',1,'N','stable'),(48,'qloudapi/qloudmnt','qloudapi/qloudmnt','575888592824438784',1,'N','stable'),(49,'qloud/consul','qloud/consul','578416429041192960',1,'Y','1.2.2'),(52,'qloudservice/qlouddiscovery','qloudservice/qlouddiscovery','578416429041192960',1,'Y','stable'),(53,'qloudservice/qloudconfig','qloudservice/qloudconfig','578416429041192960',1,'Y','stable'),(54,'qloudservice/qloudcdc-mariadb','qloudservice/qloudcdc-mariadb','578416429041192960',1,'Y','stable'),(55,'qloudapi/qloudview','qloudapi/qloudview','578416429041192960',1,'N','stable'),(56,'qloud/qloudgrid','qloud/qloudgrid','578416429041192960',1,'Y','chart.0.1'),(57,'qloud/qloudapi','qloud/qloudapi','578416429041192960',1,'Y','4.0.0'),(58,'qloud/qloudmnt','qloud/qloudmnt','578416429041192960',1,'Y','4.0.0'),(59,'qloudapi/qloudwebui-onlyapi','qloudapi/qloudwebui-onlyapi','578416429041192960',1,'Y','latest'),(60,'qloud/qloudauth','qloud/qloudauth','578416429041192960',1,'Y','chart.0.1'),(61,'qloud-view-1.2.1.tgz','qloud-view-1.2.1.tgz','578416429041192960',2,'N',NULL),(62,'qloudobp/cdcview:latest','qloudobp/cdcview','578416429041192960',1,'Y','latest'),(63,'qloudapi/id-service','qloudapi/id-service','575888592824438784',1,'N','1.1.4'),(64,'qloudapi/cas','qloudapi/cas','575888592824438784',1,'N','1.0.2'),(65,'qloudapi/qloudwebui-onlyapi','qloudapi/qloudwebui-onlyapi','575888592824438784',1,'N','stable'),(66,'qloudmonitor-1.2.1.tgz','qloudmonitor-1.2.1.tgz','584896407874310144',2,'N',NULL),(68,'qloudpaas/logstash','qloudpaas/logstash','584896407874310144',1,'N','6.0.1'),(69,'qloudpaas/grafana','qloudpaas/grafana','584896407874310144',1,'N','5.1.3'),(70,'qloudpaas/kibana','qloudpaas/kibana','584896407874310144',1,'N','5.6.12'),(71,'qloudpaas/statsd','qloudpaas/statsd','584896407874310144',1,'N','0.8.0'),(72,'qloudpaas/zipkin','qloudpaas/zipkin','584896407874310144',1,'N','2.7.6'),(73,'qloudservice/qloudconductor-server','qloudservice/qloudconductor-server','575852507633291264',1,'N','stable'),(74,'qloudpaas/zipkin-dependencies','qloudpaas/zipkin-dependencies','584896407874310144',1,'N','1.11.4'),(75,'qloudpaas/conductor-ui','qloudpaas/conductor-ui','575852507633291264',1,'N','stable'),(76,'qloudpaas/elastalert','qloudpaas/elastalert','584896407874310144',1,'N','0.0.14'),(77,'qloudservice/qloudservice-qloudsaga-reliability-task1','qloudservice/qloudservice-qloudsaga-reliability-task1','575852507633291264',1,'N','stable'),(78,'qloudservice/qloudservice-qloudsaga-reliability-task2','qloudservice/qloudservice-qloudsaga-reliability-task2','575852507633291264',1,'N','stable'),(79,'qloudidauthz-1.2.1.tgz','qloudidauthz-1.2.1.tgz','586371204433055744',2,'N',NULL),(80,'qloudid/qloudauthz','qloudid/qloudauthz','586371204433055744',1,'N','stable'),(81,'qloudsense/site','qloudsense/site','586386231659597824',1,'N','stable'),(82,'qloudsense-site-1.2.1.tgz','qloudsense-site-1.2.1.tgz','586386231659597824',2,'N',NULL);
/*!40000 ALTER TABLE `tb_artifact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_artifact_attr`
--

DROP TABLE IF EXISTS `tb_artifact_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_artifact_attr` (
  `artifact_id` int(12) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `attr_name` varchar(64) DEFAULT NULL,
  `attr_value` varchar(128) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_artifact_attr`
--

LOCK TABLES `tb_artifact_attr` WRITE;
/*!40000 ALTER TABLE `tb_artifact_attr` DISABLE KEYS */;
INSERT INTO `tb_artifact_attr` VALUES (8,1,'tag','2.15.3','N'),(9,2,'tag','1.0','N');
/*!40000 ALTER TABLE `tb_artifact_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_artifact_type`
--

DROP TABLE IF EXISTS `tb_artifact_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_artifact_type` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL,
  `des` varchar(128) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_artifact_type`
--

LOCK TABLES `tb_artifact_type` WRITE;
/*!40000 ALTER TABLE `tb_artifact_type` DISABLE KEYS */;
INSERT INTO `tb_artifact_type` VALUES (1,'docker','镜像','N'),(2,'chart','chart文件','N');
/*!40000 ALTER TABLE `tb_artifact_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_license`
--

DROP TABLE IF EXISTS `tb_license`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_license` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '证书ID',
  `expired_date` varchar(24) NOT NULL COMMENT '失效时间',
  `content` varchar(256) NOT NULL COMMENT '证书内容',
  `product_id` varchar(32) NOT NULL COMMENT '商品ID',
  `account_id` varchar(32) NOT NULL COMMENT '账户ID',
  `status` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除',
  `create_date` varchar(32) DEFAULT NULL,
  `pcode` varchar(64) NOT NULL,
  PRIMARY KEY (`expired_date`,`pcode`),
  UNIQUE KEY `idx_id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=436 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_license`
--

LOCK TABLES `tb_license` WRITE;
/*!40000 ALTER TABLE `tb_license` DISABLE KEYS */;
INSERT INTO `tb_license` VALUES (433,'20181212','wdqq','567867819828321981','10001','N','2018-12-11 14:05:21','test-e5e62b71-547e-42b7-a205-0a38c900ade8'),(435,'20501231','testtesttesttesttesttesttesttest','571910296180887552','10001','N','2018-12-12 17:37:03','345678900-4567890-23668'),(434,'20501231','testtesttesttesttesttesttesttest','567867819828321981','10001','N','2018-12-11 14:05:37','dwqdwqdq');
/*!40000 ALTER TABLE `tb_license` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_order`
--

DROP TABLE IF EXISTS `tb_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_order` (
  `id` varchar(64) NOT NULL COMMENT '订单ID',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `order_status` varchar(12) DEFAULT NULL COMMENT '订单状态',
  `purchase_code` varchar(64) NOT NULL COMMENT '购买编码',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账户ID',
  `product_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `license_id` int(12) DEFAULT NULL,
  `product_name` varchar(64) NOT NULL,
  `deploy_msg` longtext COMMENT '部署信息',
  `order_type` varchar(32) DEFAULT NULL,
  `deploy_addr` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_purchase_code` (`purchase_code`),
  KEY `fk_tb_order_tb_license1` (`license_id`),
  KEY `fk_order_account` (`account_id`),
  KEY `fk_order_product` (`product_id`),
  CONSTRAINT `fk_order_account` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_order_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_tb_order_tb_license1` FOREIGN KEY (`license_id`) REFERENCES `tb_license` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_order`
--

LOCK TABLES `tb_order` WRITE;
/*!40000 ALTER TABLE `tb_order` DISABLE KEYS */;
INSERT INTO `tb_order` VALUES ('59c9fd40-0334-49c7-be08-8ecff287c6c2','2018-12-11 06:05:23','unDeployed','test-e5e62b71-547e-42b7-a205-0a38c900ade8','10001','567867819828321981',433,'testABC','{\"msg\":\"exeption on your programs.\",\"data\":{\"method\":\"notify\",\"orderInfo\":{\"purchaseCode\":\"test-e5e62b71-547e-42b7-a205-0a38c900ade8\",\"productId\":\"567867819828321981\",\"orderStatus\":\"paid\",\"productName\":\"testABC\",\"accountId\":\"10001\",\"id\":\"59c9fd40-0334-49c7-be08-8ecff287c6c2\",\"licenseId\":433,\"createDate\":\"2018-12-11 06:05:21.0\"}},\"succeed\":false}','try',NULL),('65a4a602-7221-40ec-8b83-bc04490a459b','2018-12-11 06:05:37','paid','dwqdwqdq','10001','567867819828321981',434,'testABC',NULL,'buy',NULL),('961e59df-6631-4195-8fee-25712caf9500','2018-12-12 09:37:03','paid','345678900-4567890-23668','10001','571910296180887552',435,'QloudTest - Selenium',NULL,'buy',NULL);
/*!40000 ALTER TABLE `tb_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product`
--

DROP TABLE IF EXISTS `tb_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product` (
  `id` varchar(32) NOT NULL COMMENT '商品ID',
  `name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `short_desc` varchar(256) DEFAULT NULL COMMENT '短描述',
  `icon` varchar(256) DEFAULT NULL COMMENT '图标地址',
  `long_desc` longtext COMMENT '长描述',
  `picture_path` varchar(256) DEFAULT NULL COMMENT '商品图片地址',
  `desc_url` varchar(256) DEFAULT NULL COMMENT '商品描述url',
  `type_id` int(12) DEFAULT NULL COMMENT '商品类型ID',
  `vendor_id` int(12) DEFAULT NULL COMMENT '供应商ID',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  `category_id` int(12) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_product_category` (`category_id`),
  KEY `fk_product_type` (`type_id`),
  KEY `fk_product_vendor` (`vendor_id`),
  CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `tb_product_category` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_type` FOREIGN KEY (`type_id`) REFERENCES `tb_product_type` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_product_vendor` FOREIGN KEY (`vendor_id`) REFERENCES `tb_vendor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product`
--

LOCK TABLES `tb_product` WRITE;
/*!40000 ALTER TABLE `tb_product` DISABLE KEYS */;
INSERT INTO `tb_product` VALUES ('567867819828321981','testABC','这是一个测试','http://mart.csftgroup.com:30024/images/icon/qloudservice.jpg',NULL,'http://mart.csftgroup.com:30024/images/icon/qloudview.jpg','https://jenkins.io',1,1,'N',1),('571828364302618624','QloudDOP','端到端DevOps平台，提供全自动流水线，支持可插拔工具集','http://mart.csftgroup.com:30024/images/icon/dop.jpg',NULL,'','http://www.qloudmart.com',2,1,'N',1),('571828364302618629','Jenkins','QloudDOP的CI/CD(持续集成和持续部署)插件','http://mart.csftgroup.com:30024/images/icon/jenkins.jpg','',NULL,'https://jenkins.io',1,1,'N',1),('571828364302618630','Gerrit','QloudDOP的代码评审插件','http://mart.csftgroup.com:30024/images/icon/gerrit.jpg','',NULL,'http://qloudsso.io',1,1,'N',1),('571829128651608064','QloudTest - Engine','QloudTest测试执行引擎插件','http://mart.csftgroup.com:30024/images/icon/qloudtest-engine.jpg','','','http://www.qloudmart.com',1,1,'N',1),('571829434831605760','QloudTest','分布式自动化测试平台，支持API, UI等多种测试场景，可视化的案例编排与执行','http://mart.csftgroup.com:30024/images/icon/qloudtest.jpg','','','http://www.qloudmart.com',2,1,'N',1),('571910296180887552','QloudTest - Selenium','QloudTest UI 自动测试的驱动插件','http://mart.csftgroup.com:30024/images/icon/qloudtest-pluginuidriver.jpg','','','http://www.qloudmart.com',1,1,'N',1),('571911509110689792','QloudSSO','统一认证和单点登录服务，支持OAuth2, OpenID, OpenID Connector等协议','http://mart.csftgroup.com:30024/images/icon/qloudsso.jpg','','','http://www.qloudmart.com',9,1,'N',1),('575852507633291264','QloudService','金融级微服务平台，提供事件级安全，动态配置管理。交易所级高性能、高并发、一致性事务处理','http://mart.csftgroup.com:30024/images/icon/qloudservice.jpg','','http://mart.csftgroup.com:30024/images/icon/qloudservice.jpg','http://mart.csftgroup.com:30024',9,1,'N',2),('575888592824438784','QloudAPI','API服务平台，移动后台服务和数据的安全统一对外网关接口','http://mart.csftgroup.com:30024/images/icon/qloudapi.jpg','','http://mart.csftgroup.com:30024/images/icon/qloudapi.jpg','http://mart.csftgroup.com:30024',4,1,'N',2),('578416429041192960','QloudView','随需而见，快速构建Schemaless数据视图','http://mart.csftgroup.com:30024/images/icon/qloudview.jpg','','http://mart.csftgroup.com:30024/images/icon/qloudview.jpg','http://mart.csftgroup.com:30024',4,1,'N',2),('584896407874310144','QloudMonitor','平台与服务监控','http://mart.csftgroup.com:30024/images/icon/qloudmonitor.jpg',NULL,'http://mart.csftgroup.com:30024/images/icon/qloudmonitor.jpg','http://mart.csftgroup.com:30024',7,1,'N',3),('586371204433055744','QloudAuthz','统一认证服务','http://mart.csftgroup.com:30024/images/icon/qloudauthz.jpg','null','http://mart.csftgroup.com:30024/images/icon/qloudauthz.jpg','http://mart.csftgroup.com:30024',4,1,'N',2),('586386231659597824','QloudSense','用户行为感知和分析平台','http://mart.csftgroup.com:30024/images/icon/qloudsense.jpg','null','http://mart.csftgroup.com:30024/images/icon/qloudsense.jpg','http://mart.csftgroup.com:30024',7,1,'N',2);
/*!40000 ALTER TABLE `tb_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_attr`
--

DROP TABLE IF EXISTS `tb_product_attr`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_attr` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `product_id` varchar(32) NOT NULL,
  `attr_key` varchar(32) NOT NULL,
  `attr_value` varchar(32) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`,`attr_key`,`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_attr`
--

LOCK TABLES `tb_product_attr` WRITE;
/*!40000 ALTER TABLE `tb_product_attr` DISABLE KEYS */;
/*!40000 ALTER TABLE `tb_product_attr` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_category`
--

DROP TABLE IF EXISTS `tb_product_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_category` (
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `des` varchar(256) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_category`
--

LOCK TABLES `tb_product_category` WRITE;
/*!40000 ALTER TABLE `tb_product_category` DISABLE KEYS */;
INSERT INTO `tb_product_category` VALUES (1,'银行开发管理','银行开发管理','N'),(2,'金融产品系统','金融产品系统','N'),(3,'银行IT治理','银行IT治理','N');
/*!40000 ALTER TABLE `tb_product_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_picture`
--

DROP TABLE IF EXISTS `tb_product_picture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_picture` (
  `product_id` varchar(32) NOT NULL,
  `url` varchar(256) NOT NULL,
  `status` varchar(1) NOT NULL DEFAULT 'N',
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `sort` int(12) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_picture`
--

LOCK TABLES `tb_product_picture` WRITE;
/*!40000 ALTER TABLE `tb_product_picture` DISABLE KEYS */;
INSERT INTO `tb_product_picture` VALUES ('571826561171329024','http://pic2.ooopic.com/10/50/25/52b1OOOPICa3.jpg','N',3,1),('571828364302618624','http://mart.csftgroup.com:30024/images/icon/dop.jpg','N',4,1),('571828364302618624','http://mart.csftgroup.com:30024/images/icon/qloudsso.jpg','N',5,1),('571829128651608064','http://mart.csftgroup.com:30024/images/icon/qloudtest-engine.jpg','N',6,1),('571829434831605760','http://mart.csftgroup.com:30024/images/icon/qloudtest.jpg','N',7,1),('571826561171329024','\nhttp://static.qloudmart.com/images/icon/jenkins.png\n\n','N',8,2);
/*!40000 ALTER TABLE `tb_product_picture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_tag`
--

DROP TABLE IF EXISTS `tb_product_tag`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_tag` (
  `product_id` varchar(32) DEFAULT NULL COMMENT '商品ID',
  `name` varchar(32) NOT NULL COMMENT '商品标签',
  `id` int(12) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`),
  KEY `fk_tag_product` (`product_id`),
  CONSTRAINT `fk_tag_product` FOREIGN KEY (`product_id`) REFERENCES `tb_product` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_tag`
--

LOCK TABLES `tb_product_tag` WRITE;
/*!40000 ALTER TABLE `tb_product_tag` DISABLE KEYS */;
INSERT INTO `tb_product_tag` VALUES ('571828364302618629','oss',1),('571828364302618624','qloud',2),('571829128651608064','qloud',3),('571829434831605760','qloud',4),('571910296180887552','qloud',5),('571911509110689792','qloud',6),('571828364302618630','oss',10),('575852507633291264','qloud',11),('575888592824438784','qloud',12),('578416429041192960','qloud',13),('571828364302618629','qloud',14),('584896407874310144','qloud',15),('571828364302618630','qloud',16),('586371204433055744','qloud',17),('586386231659597824','qloud',18),('584896407874310144','oss',19);
/*!40000 ALTER TABLE `tb_product_tag` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_product_type`
--

DROP TABLE IF EXISTS `tb_product_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_product_type` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `name` varchar(64) DEFAULT NULL COMMENT '商品名称',
  `des` varchar(256) DEFAULT NULL COMMENT '短描述',
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_product_type`
--

LOCK TABLES `tb_product_type` WRITE;
/*!40000 ALTER TABLE `tb_product_type` DISABLE KEYS */;
INSERT INTO `tb_product_type` VALUES (1,'插件','插件','N'),(2,'应用','应用','N'),(3,'系统','系统','N'),(4,'API','API','N'),(5,'微服务','微服务','N'),(6,'算法','算法','N'),(7,'平台','平台','N'),(8,'SDK','SDK','N'),(9,'服务','服务','N');
/*!40000 ALTER TABLE `tb_product_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_recomand_product`
--

DROP TABLE IF EXISTS `tb_recomand_product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_recomand_product` (
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `product_id` varchar(32) NOT NULL COMMENT '产品ID',
  `account_id` varchar(32) NOT NULL COMMENT '账户ID',
  `status` varchar(1) NOT NULL DEFAULT 'N' COMMENT '是否删除N为存在 Y为删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_recomand_product`
--

LOCK TABLES `tb_recomand_product` WRITE;
/*!40000 ALTER TABLE `tb_recomand_product` DISABLE KEYS */;
INSERT INTO `tb_recomand_product` VALUES (1,'571828364302618624','qloudbank','N'),(2,'571828364302618624\r\n571828364302','10001','N'),(3,'571829434831605760','10001','N'),(4,'571910296180887552','10001','N'),(5,'571911509110689792','10001','N'),(6,'575852507633291264','10001','N'),(7,'575888592824438784','10001','N'),(8,'578416429041192960','10001','N'),(9,'584896407874310144','10001','N'),(10,'586371204433055744','10001','N'),(11,'586386231659597824','10001','N');
/*!40000 ALTER TABLE `tb_recomand_product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_role`
--

DROP TABLE IF EXISTS `tb_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_role` (
  `role_id` varchar(64) NOT NULL DEFAULT '' COMMENT '角色ID',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `status` varchar(1) DEFAULT 'N' COMMENT '状态 N 代表在用',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_role`
--

LOCK TABLES `tb_role` WRITE;
/*!40000 ALTER TABLE `tb_role` DISABLE KEYS */;
INSERT INTO `tb_role` VALUES ('acountuser','用户角色','N'),('administrator','管理员','N');
/*!40000 ALTER TABLE `tb_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_source`
--

DROP TABLE IF EXISTS `tb_source`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_source` (
  `role_id` varchar(64) DEFAULT NULL,
  `id` int(12) NOT NULL AUTO_INCREMENT,
  `status` varchar(1) DEFAULT 'N',
  `source` varchar(256) DEFAULT NULL COMMENT '资源内容',
  `source_type` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_source`
--

LOCK TABLES `tb_source` WRITE;
/*!40000 ALTER TABLE `tb_source` DISABLE KEYS */;
INSERT INTO `tb_source` VALUES ('administrator',1,'N','POST /accounts','api'),('acountuser',2,'N','GET /accounts/*/products','api'),('acountuser',3,'N','POST /orders','api'),('acountuser',4,'N','POST /orders/*','api'),('acountuser',5,'N','GET /orders/*','api'),('acountuser',6,'N','GET /orders/*/*','api'),('acountuser',7,'N','GET /orders','api'),('accountuser',8,'N','GET /users/*/accounts','api');
/*!40000 ALTER TABLE `tb_source` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_sys_attr_key`
--

DROP TABLE IF EXISTS `tb_sys_attr_key`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_sys_attr_key` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `key` varchar(32) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `status` varchar(1) DEFAULT 'N',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_key` (`key`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_sys_attr_key`
--

LOCK TABLES `tb_sys_attr_key` WRITE;
/*!40000 ALTER TABLE `tb_sys_attr_key` DISABLE KEYS */;
INSERT INTO `tb_sys_attr_key` VALUES (1,'IS_SALE','是否可买','N'),(2,'deploy_addr_1','部署地址1','N');
/*!40000 ALTER TABLE `tb_sys_attr_key` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user`
--

DROP TABLE IF EXISTS `tb_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user` (
  `name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `id` varchar(32) NOT NULL COMMENT '用户ID',
  `account_id` varchar(32) DEFAULT NULL COMMENT '账户ID',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  `pwd` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_user_account` (`account_id`),
  CONSTRAINT `fk_user_account` FOREIGN KEY (`account_id`) REFERENCES `tb_account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user`
--

LOCK TABLES `tb_user` WRITE;
/*!40000 ALTER TABLE `tb_user` DISABLE KEYS */;
INSERT INTO `tb_user` VALUES ('管理员','admin','10001','N','E10ADC3949BA59ABBE56E057F20F883E'),('陈晓稷','chenxiaoji','10001','N','E10ADC3949BA59ABBE56E057F20F883E'),('查克贝','chuckberry','10001','N','92D7DDD2A010C59511DC2905B7E14F64'),('海盛','haisheng','10001','N','E10ADC3949BA59ABBE56E057F20F883E'),('管理员','MYSYYH','10001','N','C33367701511B4F6020EC61DED352059'),('POC','POC','MYCMB','N','42974CF927AD7559328109582B7D7F84'),('测试用户','test','Test','N','B4C0F78449FB5193A970A80C519AD3A5'),('管理员','testUser','10001','N','E10ADC3949BA59ABBE56E057F20F883E'),('张三','zhangsan','10001','N','E10ADC3949BA59ABBE56E057F20F883E');
/*!40000 ALTER TABLE `tb_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_user_role`
--

DROP TABLE IF EXISTS `tb_user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_user_role` (
  `user_id` varchar(32) NOT NULL COMMENT '用户id',
  `role_id` varchar(64) NOT NULL COMMENT '角色id',
  `id` int(12) NOT NULL AUTO_INCREMENT COMMENT 'id 自增长',
  `status` varchar(1) DEFAULT 'N' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_user_role`
--

LOCK TABLES `tb_user_role` WRITE;
/*!40000 ALTER TABLE `tb_user_role` DISABLE KEYS */;
INSERT INTO `tb_user_role` VALUES ('haisheng','administrator',1,'N'),('chenxiaoji','acountuser',2,'N'),('chuckberry','acountuser',3,'N');
/*!40000 ALTER TABLE `tb_user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tb_vendor`
--

DROP TABLE IF EXISTS `tb_vendor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tb_vendor` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '供应商ID',
  `name` varchar(64) DEFAULT NULL COMMENT '供应商名称',
  `email` varchar(128) DEFAULT NULL COMMENT '供应商电子邮箱',
  `contact_phone` varchar(24) DEFAULT NULL COMMENT '联系电话',
  `url` varchar(256) DEFAULT NULL COMMENT '网站地址',
  `hook_addr` varchar(256) DEFAULT NULL COMMENT 'hook 地址',
  `status` varchar(1) DEFAULT 'N' COMMENT '是否删除',
  `display_name` varchar(128) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tb_vendor`
--

LOCK TABLES `tb_vendor` WRITE;
/*!40000 ALTER TABLE `tb_vendor` DISABLE KEYS */;
INSERT INTO `tb_vendor` VALUES (1,'华软金科','1234567@139.com','1234567','www.testvendor.com','http://10.43.61.73:8100/purchase','N','华软'),(3,'testVendor','1234567@139.com','1234567','www.testvendor.com','','N','displayVen22');
/*!40000 ALTER TABLE `tb_vendor` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-18  6:38:13
