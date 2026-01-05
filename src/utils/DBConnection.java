package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    // Détails de la connexion à la base de données
    // Remplacez par vos propres informations de connexion
    // Assurez-vous que le pilote JDBC pour MySQL est dans le classpath
    // URL de connexion, utilisateur et mot de passe

    private static final String URL = "jdbc:mysql://localhost:3306/formation";
    private static final String USER = "formation";
    private static final String PASSWORD = "gS4_)xp9!qUosghv";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
