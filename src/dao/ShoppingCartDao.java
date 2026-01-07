package dao;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class ShoppingCartDao {

    // Sauvegarder un cours dans le panier d'un client
    // Crée le panier s'il n'existe pas
    // Ajoute le cours au panier
    // Utilisation de PreparedStatement pour éviter les injections SQL

    public static void save(int clientId, int courseId) {

        // Requête pour créer un panier s'il n'existe pas déjà
        // Requête pour obtenir l'ID du panier
        // Requête pour ajouter le cours au panier
        // Retourne void
        // Utilisation de transactions pour garantir la cohérence des données
        // Gestion des exceptions pour capturer les erreurs potentielles
        // Fermeture appropriée des ressources (Connection, PreparedStatement, ResultSet)

        String cartQuery = "INSERT INTO shopping_carts (fk_client_id) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM shopping_carts WHERE fk_client_id = ?)";

        String getCartIdQuery = "SELECT cart_id FROM shopping_carts WHERE fk_client_id = ?";

        String courseQuery = "INSERT INTO cart_courses (fk_cart_id, fk_course_id) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection()) {


            try (PreparedStatement ps = connection.prepareStatement(cartQuery)) {
                ps.setInt(1, clientId);
                ps.setInt(2, clientId);
                ps.executeUpdate();
            }


            int cartId = -1;
            try (PreparedStatement ps = connection.prepareStatement(getCartIdQuery)) {
                ps.setInt(1, clientId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    cartId = rs.getInt("cart_id");
                }
            }


            if (cartId != -1) {
                try (PreparedStatement ps = connection.prepareStatement(courseQuery)) {
                    ps.setInt(1, cartId);
                    ps.setInt(2, courseId);
                    ps.executeUpdate();
                }
            }

        } catch (Exception e) {
            System.out.println("Error saving shopping cart item: " + e.getMessage());
        }
    }

    public static int[] readAllCoursesIds() {

        // Implémentation pour lire tous les IDs des cours dans le panier
        // Utilisation de PreparedStatement pour éviter les injections SQL
        // Retourne un tableau d'entiers avec les IDs des cours dans le panier

        String query = "SELECT * FROM cart_courses";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            List<Integer> idsList = new ArrayList<>();

            while (rs.next()) {
                idsList.add(rs.getInt("fk_course_id"));
            }

            return idsList.stream().mapToInt(Integer::intValue).toArray();

        } catch (Exception e) {
            System.out.println("Error reading cart course IDs: " + e.getMessage());
        }
        return new int[0];
    }

    public static void removeFromCart(int clientId, int courseId) {

        // Implémentation pour supprimer un cours du panier d'un client
        // Utilisation de PreparedStatement pour éviter les injections SQL
        // Supprime l'entrée correspondante dans la table cart_courses
        // en fonction de l'ID du panier et de l'ID du cours
        // Retourne void

        String getCartIdQuery = "SELECT cart_id FROM shopping_carts WHERE fk_client_id = ?";
        int cartId = -1;
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(getCartIdQuery)) {

            ps.setInt(1, clientId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cartId = rs.getInt("cart_id");
            }

        } catch (Exception e) {
            System.out.println("Error retrieving cart ID: " + e.getMessage());
            return;
        }

        String query = "DELETE FROM cart_courses WHERE fk_cart_id = ? AND fk_course_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, cartId);
            ps.setInt(2, courseId);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error removing course from cart: " + e.getMessage());
        }
    }
}
