# Evaluation Métier Java

### Ce projet est une évaluation métier basée sur le langage Java. Le principe est de réaliser une application console permettant dans un premier temps d'afficher des formations disponibles en tant qu'utilisateur. Celles-ci peuvent être triées par formation distancielle ou présentielle. De plus il est possible de filtrer les formations par mot-clé:

### Prérequis
- Java Development Kit (JDK) 8 ou supérieur installé sur votre machine.
- Un IDE Java (comme IntelliJ IDEA, Eclipse, etc.) ou un éditeur de texte.

### Instructions pour exécuter l'application
1. Clonez le dépôt ou téléchargez les fichiers source.
2. Ouvrez le projet dans votre IDE ou éditeur de texte préféré.
3. Télécharger le fichier .sql
4. Importer le fichier .sql à la base de donnée MYSQL de votre choix.
5. Compilez les fichiers Java.
6. Exécutez la classe principale `Main.java`.
7. Suivez les instructions affichées dans la console pour interagir avec l'application.

### Fonctionnalités
- Affichage de la liste des formations disponibles.
- Tri des formations par type (distancielle ou présentielle).
- Filtrage des formations par mot-clé.
- Interface utilisateur simple en console.

### Dans un second temps, l'application permet en tant qu'utilisateur de s'authentifier ou de créer un compte puis de pouvoir ajouter ou supprimer une formation d'une shopping list elle-même rattachée à un utilisateur:

### Fonctionnalités supplémentaires
- Création de compte utilisateur.
- Authentification des utilisateurs.
- Ajout et suppression de formations dans une shopping list personnelle.
- Gestion des utilisateurs et de leurs shopping lists.
- Interface utilisateur en console pour la gestion des comptes et des shopping lists.

### Toutes les fonctionnalités sont codées en s'appuyant sur une base de données MYSQL.

### Structure du projet
- `src/` : Contient les fichiers source Java.
- `lib/` : Contient les bibliothèques externes nécessaires (si applicable).
- `README.md` : Ce fichier d'instructions.
- Dans `src/` :
  - `Main.java` : Point d'entrée de l'application.
  - `models/` : Contient les classes représentant les entités (Formation, Utilisateur, ShoppingList, etc.).
  - `utils/` : Contient les utilitaires pour la gestion de la base de données et autres fonctions auxiliaires.
  - `app1/` : Contient les fonctionnalités de la première partie (affichage et filtrage des formations).
  - `app2/` : Contient les fonctionnalités de la seconde partie (version hybride).
  - `app3/` : Contient les fonctionnalités supplémentaires (version entièrement à partir de la base de données).

### Auteur
- David BERNARD