-- phpMyAdmin SQL Dump
-- version 5.2.3
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : mer. 07 jan. 2026 à 08:39
-- Version du serveur : 8.4.7
-- Version de PHP : 8.3.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `formation`
--

-- --------------------------------------------------------

--
-- Structure de la table `addresses`
--

DROP TABLE IF EXISTS `addresses`;
CREATE TABLE IF NOT EXISTS `addresses` (
  `address_id` int NOT NULL AUTO_INCREMENT,
  `country` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `city` varchar(20) COLLATE utf8mb4_unicode_ci NOT NULL,
  `postalCode` int NOT NULL,
  `street` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `addresses`
--

INSERT INTO `addresses` (`address_id`, `country`, `city`, `postalCode`, `street`) VALUES
(2, 'France', 'Paris', 75001, '12 Rue de la Paix'),
(3, 'France', 'Lyon', 69001, '25 Avenue des Champs'),
(4, 'France', 'Marseille', 13001, '8 Boulevard Victor Hugo'),
(5, 'France', 'Lille', 59000, '45 Rue Nationale'),
(6, 'France', 'Lyon', 69002, '33 Place Bellecour'),
(7, 'France', 'Toulouse', 31000, '67 Rue de la République'),
(8, 'France', 'Nantes', 44000, '15 Avenue Jean Jaurès'),
(9, 'France', 'Bordeaux', 33000, '89 Rue du Commerce'),
(10, 'France', 'Nice', 6000, '22 Boulevard Gambetta'),
(11, 'France', 'Strasbourg', 67000, '56 Rue de Strasbourg');

-- --------------------------------------------------------

--
-- Structure de la table `cart_courses`
--

DROP TABLE IF EXISTS `cart_courses`;
CREATE TABLE IF NOT EXISTS `cart_courses` (
  `cart_course_id` int NOT NULL AUTO_INCREMENT,
  `fk_cart_id` int NOT NULL,
  `fk_course_id` int NOT NULL,
  PRIMARY KEY (`cart_course_id`),
  KEY `fk_cart_id` (`fk_cart_id`),
  KEY `fk_course_id` (`fk_course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `clients`
--

DROP TABLE IF EXISTS `clients`;
CREATE TABLE IF NOT EXISTS `clients` (
  `client_id` int NOT NULL AUTO_INCREMENT,
  `firstName` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `lastName` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `email` varchar(25) COLLATE utf8mb4_unicode_ci NOT NULL,
  `fk_address_id` int NOT NULL,
  `phoneNumber` varchar(10) COLLATE utf8mb4_unicode_ci NOT NULL,
  PRIMARY KEY (`client_id`),
  KEY `fk_address_id` (`fk_address_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `clients`
--

INSERT INTO `clients` (`client_id`, `firstName`, `lastName`, `email`, `fk_address_id`, `phoneNumber`) VALUES
(21, 'Jean', 'Dupont', 'jean.dupont@email.fr', 11, '0601020304'),
(22, 'Marie', 'Martin', 'marie.martin@email.fr', 2, '0612345678'),
(23, 'Pierre', 'Bernard', 'pierre.bernard@email.fr', 3, '0623456789'),
(24, 'Sophie', 'Dubois', 'sophie.dubois@email.fr', 4, '0634567890'),
(25, 'Luc', 'Thomas', 'luc.thomas@email.fr', 5, '0645678901'),
(26, 'Emma', 'Robert', 'emma.robert@email.fr', 6, '0656789012'),
(27, 'Paul', 'Petit', 'paul.petit@email.fr', 7, '0667890123'),
(28, 'Julie', 'Durand', 'julie.durand@email.fr', 8, '0678901234'),
(29, 'Marc', 'Leroy', 'marc.leroy@email.fr', 9, '0689012345'),
(30, 'Alice', 'Moreau', 'alice.moreau@email.fr', 10, '0690123456');

-- --------------------------------------------------------

--
-- Structure de la table `courses`
--

DROP TABLE IF EXISTS `courses`;
CREATE TABLE IF NOT EXISTS `courses` (
  `course_id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL,
  `description` varchar(255) COLLATE utf8mb4_unicode_ci NOT NULL,
  `duration` int NOT NULL,
  `type` varchar(15) COLLATE utf8mb4_unicode_ci NOT NULL,
  `price` int NOT NULL,
  PRIMARY KEY (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Déchargement des données de la table `courses`
--

INSERT INTO `courses` (`course_id`, `name`, `description`, `duration`, `type`, `price`) VALUES
(1, 'Python Débutant', 'Apprenez les fondamentaux de la programmation avec Python : variables, boucles, conditions et fonctions', 40, 'Présentiel', 300),
(2, 'Full Stack Web', 'Maîtrisez HTML, CSS, JavaScript, Node.js et les bases de données pour créer des applications web complètes', 120, 'Distanciel', 900),
(3, 'JavaScript ES6+', 'Explorez les fonctionnalités avancées de JavaScript : async/await, destructuring, modules et programmation fonctionnelle', 35, 'Présentiel', 350),
(4, 'React & Redux', 'Créez des interfaces utilisateur dynamiques avec React, hooks, context API et gestion d\'état avec Redux', 60, 'Distanciel', 500),
(5, 'SQL Avancé', 'Maîtrisez SQL, conception de bases de données, requêtes complexes et optimisation des performances', 45, 'Présentiel', 380),
(6, 'Python Data Science', 'Analysez et visualisez des données avec Pandas, NumPy, Matplotlib et introduction au Machine Learning', 80, 'Distanciel', 700),
(7, 'Git & GitHub', 'Travaillez efficacement en équipe avec Git : branches, merge, pull requests et workflows collaboratifs', 20, 'Distanciel', 200),
(8, 'API REST Node.js', 'Concevez et développez des APIs RESTful sécurisées avec authentification JWT et documentation Swagger', 50, 'Présentiel', 450),
(9, 'Docker Essentiel', 'Déployez vos applications avec Docker, Docker Compose et introduction à Kubernetes', 35, 'Distanciel', 400),
(10, 'Sécurité Web', 'Protégez vos applications contre les vulnérabilités : XSS, CSRF, injection SQL et bonnes pratiques OWASP', 40, 'Présentiel', 430);

-- --------------------------------------------------------

--
-- Structure de la table `shopping_carts`
--

DROP TABLE IF EXISTS `shopping_carts`;
CREATE TABLE IF NOT EXISTS `shopping_carts` (
  `cart_id` int NOT NULL AUTO_INCREMENT,
  `fk_client_id` int NOT NULL,
  PRIMARY KEY (`cart_id`),
  KEY `fk_client_id` (`fk_client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `cart_courses`
--
ALTER TABLE `cart_courses`
  ADD CONSTRAINT `fk_cart_id` FOREIGN KEY (`fk_cart_id`) REFERENCES `shopping_carts` (`cart_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_course_id` FOREIGN KEY (`fk_course_id`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `clients`
--
ALTER TABLE `clients`
  ADD CONSTRAINT `fk_address_id` FOREIGN KEY (`fk_address_id`) REFERENCES `addresses` (`address_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `shopping_carts`
--
ALTER TABLE `shopping_carts`
  ADD CONSTRAINT `fk_client_id` FOREIGN KEY (`fk_client_id`) REFERENCES `clients` (`client_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
