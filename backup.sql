CREATE DATABASE  IF NOT EXISTS `lootheaven` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `lootheaven`;
-- MySQL dump 10.13  Distrib 8.0.32, for Win64 (x86_64)
--
-- Host: localhost    Database: lootheaven
-- ------------------------------------------------------
-- Server version	8.0.31

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
-- Table structure for table `loots`
--

DROP TABLE IF EXISTS `loots`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `loots` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `content_url` varchar(255) DEFAULT NULL,
  `description` text,
  `loot_name` varchar(255) DEFAULT NULL,
  `price` bigint DEFAULT NULL,
  `product_name` varchar(255) DEFAULT NULL,
  `published` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` enum('ACCOUNT','ITEM','KEY','CURRENCY','GAME_PASS','OTHER') DEFAULT NULL,
  `creator_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9ptguow7433de7nyl1n5rvw0c` (`creator_id`),
  CONSTRAINT `FK9ptguow7433de7nyl1n5rvw0c` FOREIGN KEY (`creator_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `loots`
--

LOCK TABLES `loots` WRITE;
/*!40000 ALTER TABLE `loots` DISABLE KEYS */;
INSERT INTO `loots` VALUES (1,'https://mcserv2847.github.io/','АККАУНТ 6500 ДОТА 2 ТИТАН ПОРЯДОЧНОСТЬ 10К БЕЗ СТИМ ГВАРДА ПОСЛЕ ПОКУПКИ ВАМ ПРИСЫЛАЕТСЯ ССЫЛКА НА TXT ФАЙЛ С ПАРОЛЕМ И ЛОГИНОМ','АККАУНТ 6500 ДОТА 2 ТИТАН ПОРЯДОЧНОСТЬ 10К БЕЗ СТИМ ГВАРДА',999,'Dota 2','2024-05-03 14:24:24.085000','sold','ACCOUNT',1),(14,'login: bebra password: 228228','Spotify Premium Account 1 month TURKEY FREE ACTIVATION WTF','Spotify Premium Account 1 month',699,'Spotify','2024-05-05 19:00:29.828000','sold','ACCOUNT',4),(15,'EWRY26DJ21U127:EEK33','крутое описание','Ключ Elden Ring Steam',3999,'Steam','2024-05-05 18:15:06.555000','active','KEY',1),(16,'EWRTI88J21U1123MD1J3','крутое описание','Код на Warcraft III Reforged | ПОПОЛНЯЕМ АККАУНТЫ РФ, БЕЛАРУСЬ',1799,'Battlenet','2024-05-05 18:15:06.555000','sold','KEY',1),(17,'https://dota2.com/crownfall','✅Работает АВТОВЫДАЧА - БОТ после оплаты вам сразу поступит текстовый файл с login_password от аккаунта\n❗Только для аккаунтов стим с регионом Россия, Украина, Казахстан, СНГ','✅АВТОВЫДАЧА✅Prime Status Upgrade (Россия)✅Подарок Steam✅',1635,'CS','2024-05-06 16:25:14.936000','active','ACCOUNT',2),(18,'legacy@gmail.com bebra228','⭐ После оплаты вы получите: Лицензионный аккаунт \"Minecraft\" купленный с помощью новой учетной записью Microsoft с доступом к почте.\n⭐ Почту можно сменить сразу. Аккаунт остается у вас навсегда с лиценцзионной игрой MINECRAFT JAVA EDITION.','?⭐ ????????? ???? ??????? + Лицензия | Полная смена данных ⭐?',1579,'Minecraft','2024-05-06 17:40:50.955000','active','ACCOUNT',4),(19,'kipikod@gmail.com 13567822','❓О продукте:\n\n⭐✨ Вы получите данные: (логин и пароль) ⭐✨\n\n⭐Обязательные действие перед активацией:\n✨ Открываем \"Параметры\" --> Учетные записи --> Параметры входа. Удаляем PIN код если он есть\n✨ Включить службу обновлений Windows\n✨ Выходим из своего аккаунта в Windows Store\n✨ Cкачиваем приложение XBOX и входим под своим личным аккаунтом\n\n⭐АКТИВАЦИЯ:\n✨ Переходим в приложение Microsoft Store, не в браузере, а именно в магазине на PC\n✨ В Microsoft Store вверху справа будет человечек кликаем на него. Если вы уже залогинены, то жмем выйти.\n✨ Вводим данные, которые я вам выдал.\n✨ Далее в поиске пишем название той игры которую вы хотите скачать, и скачиваем, НЕ ЗАПУСКАЕМ.\n✨ Заходим в приложение XBOX в ВАШ аккаунт.\n✨ Когда игра скачается, то она появится в XBOX во кладке \"мои игры\". Запускаете и играете\n\n⭐Примечания:\n✨ Вечная гарантия\n✨ Это активация моего аккаунта с игрой на ВАШЕМ ПК\n✨ Товар не подлежит возврату, только замена не верных данных.\n✨ Сменна данных ЗАПРЕЩЕНА.\n✨ У вас будет ваш личный ник и ваш личный прогресс\n✨ Список всех игр по ссылке - https://www.xbox.com/ru-RU/xbox-game-pass/games','?【АВТОВЫДАЧА】?【Minecraft】?【400 игр】?【3 МЕСЯЦА】?【Game Pass ULTIMATE】✅',3099,'Minecraft','2024-05-07 10:09:15.697000','active','GAME_PASS',2),(20,'QR: wqety25713dh123','?Я вам пришлю QR-код, ваша задача сканировать его через\n?приложение в телефоне (дискорд)\n?Или по данным\n─━─━─━─━─━─━─━─━─━─━─━─━─━─━─━─━\n✅Перед оплатой напишите продавцу.\n✅Почему стоит выбрать меня:\n⏩Возможен вход по токену дискорда\n⏩ Активация в течении 5-10 минут\n⏩Приобретаю на ВАШ аккаунт подписку своей ЛИЧНОЙ картой.\n⏩Подходит для РФ\n⏩Работает даже если было нитро до этого куплено.\n⏩Гарантия (1 месяц)\n\n❗Для клиентов, которые не покупали nitro у меня.\n❗Если вы покупали nitro у меня раньше, не оплачивайте это предложение.?\n✅В моем профиле есть то же самое предложение для вас, вы можете написать мне, и напишу вам все подробно.','???【??????? ????? ???? + 2 БУСТА】 ??? 【ВЫДАЧА СРАЗУ】?【ДЛЯ ВСЕХ】?【QR / Данные】?',400,'Discord','2024-05-07 10:10:48.146000','active','OTHER',4);
/*!40000 ALTER TABLE `loots` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `orders`
--

DROP TABLE IF EXISTS `orders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `transaction_cost` bigint DEFAULT NULL,
  `order_date` datetime(6) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `buyer_id` bigint DEFAULT NULL,
  `loot_id` bigint DEFAULT NULL,
  `seller_id` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKhtx3insd5ge6w486omk4fnk54` (`buyer_id`),
  KEY `FKgbm0ju2m4e7rxt9hhcybvwbnl` (`loot_id`),
  KEY `FKsb9w6305d2be0rwbtifi7wymp` (`seller_id`),
  CONSTRAINT `FKgbm0ju2m4e7rxt9hhcybvwbnl` FOREIGN KEY (`loot_id`) REFERENCES `loots` (`id`),
  CONSTRAINT `FKhtx3insd5ge6w486omk4fnk54` FOREIGN KEY (`buyer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKsb9w6305d2be0rwbtifi7wymp` FOREIGN KEY (`seller_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `orders`
--

LOCK TABLES `orders` WRITE;
/*!40000 ALTER TABLE `orders` DISABLE KEYS */;
INSERT INTO `orders` VALUES (1,999,'2024-05-04 12:11:18.209209','completed',2,1,1),(3,699,'2024-05-06 22:15:51.635808','completed',2,14,4),(4,1799,'2024-05-07 15:17:58.434229','completed',2,16,1);
/*!40000 ALTER TABLE `orders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `balance` bigint DEFAULT NULL,
  `deals_count` bigint DEFAULT NULL,
  `discord_id` varchar(255) NOT NULL,
  `email` varchar(255) NOT NULL,
  `orders_count` bigint DEFAULT NULL,
  `reg_date` date DEFAULT NULL,
  `role` enum('USER','ADMIN') DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_8h3kehimxhos38stbts56bkba` (`discord_id`),
  UNIQUE KEY `UK_6dotkott2kjsp8vw4d0m25fb7` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,_binary '','https://cdn.discordapp.com/avatars/371581535469436930/f6ec1e4906bd0809a800f48611c1ab03',3402,2,'371581535469436930','ts22072003@gmail.com',0,'2024-05-04','USER','fuji.tsu'),(2,_binary '','https://i.pinimg.com/originals/2e/a0/65/2ea065083b076ad79dc93e3a96938218.jpg',97748,0,'962772934143909908','yofujitsuuu@gmail.com',3,'2024-05-04','ADMIN','admin'),(3,_binary '','https://cdn.discordapp.com/avatars/265527224390647808/8996801e7b6645f4ec36025efeb83334',1,0,'265527224390647808','rodiontrig@gmail.com',0,'2024-05-05','USER','9pozitiv4ik'),(4,_binary '\0','https://cdn.discordapp.com/avatars/623210710209396749/01343e749950980ce12d9d59aec1b8c3',0,1,'623210710209396749','cadoni827@gmail.com',0,'2024-05-05','USER','what_isss_love');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-05-08 11:36:04
