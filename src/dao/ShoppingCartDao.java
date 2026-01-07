package dao;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;


public class ShoppingCartDao {

    public static void save(int clientId, int courseId) {

        // 1. Créer le panier si n'existe pas
        String cartQuery = "INSERT INTO shopping_carts (fk_client_id) SELECT ? WHERE NOT EXISTS (SELECT 1 FROM shopping_carts WHERE fk_client_id = ?)";

        // 2. Récupérer l'ID du panier
        String getCartIdQuery = "SELECT cart_id FROM shopping_carts WHERE fk_client_id = ?";

        // 3. Ajouter le cours au panier
        String courseQuery = "INSERT INTO cart_courses (fk_cart_id, fk_course_id) VALUES (?, ?)";

        try (Connection connection = DBConnection.getConnection()) {

            // Étape 1 : Créer panier si n'existe pas
            try (PreparedStatement ps = connection.prepareStatement(cartQuery)) {
                ps.setInt(1, clientId);
                ps.setInt(2, clientId);
                ps.executeUpdate();
            }

            // Étape 2 : Récupérer cart_id
            int cartId = -1;
            try (PreparedStatement ps = connection.prepareStatement(getCartIdQuery)) {
                ps.setInt(1, clientId);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    cartId = rs.getInt("cart_id");
                }
            }

            // Étape 3 : Ajouter le cours
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


    public static int getIdByClientId(int clientId) {
        String query = "SELECT cart_id FROM shopping_carts WHERE fk_client_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, clientId);
            var rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("cart_id");
            }

        } catch (Exception e) {
            System.out.println("Error retrieving cart ID: " + e.getMessage());
        }
        return -1;
    }

    public static int[] readAllCoursesIds() {

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
