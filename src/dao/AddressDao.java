package dao;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Address;


public class AddressDao {

    // Sauvegarder une nouvelle adresse
    // Retourne l'ID de l'adresse créée
    // ou -1 en cas d'erreur

    public int save(String street, String city, String country, int postalCode) {
        String query = "INSERT INTO addresses (street, city, country, postalCode) VALUES (?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, street);
            ps.setString(2, city);
            ps.setString(3, country);
            ps.setInt(4, postalCode);
            ps.executeUpdate();

            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error saving address: " + e.getMessage());
        }

        return -1;

    }

    // Vérifier si une adresse existe déjà (éviter les doublons)
    public Integer findIdByAddress(String street, String city, String country, int postalCode) {

        // Requête pour trouver une adresse existante
        // Retourne l'ID si trouvée, sinon null
        // Utilisation de PreparedStatement pour éviter les injections SQL

        String query = "SELECT address_id FROM addresses WHERE street = ? AND city = ? AND country = ? AND postalCode = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, street);
            ps.setString(2, city);
            ps.setString(3, country);
            ps.setInt(4, postalCode);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("address_id");  // ← Adresse existe déjà
            }

        } catch (SQLException e) {
            System.out.println("Error finding address: " + e.getMessage());
            e.printStackTrace();
        }

        return null; // Adresse n'existe pas
    }

    public int saveOrGetExisting(String street, String city, String country, int postalCode) {
        // 1. Vérifier si l'adresse existe
        Integer existingId = findIdByAddress(street, city, country, postalCode);

        if (existingId != null) {
            System.out.println("✅ Adresse existante trouvée (ID: " + existingId + ")");
            return existingId;
        }

        // 2. Si elle n'existe pas, la créer
        System.out.println("➕ Création d'une nouvelle adresse...");
        return save(street, city, country, postalCode);
    }

    public Address read(int addressId) {
        String query = "SELECT * FROM addresses WHERE address_id = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setInt(1, addressId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String street = rs.getString("street");
                String city = rs.getString("city");
                String country = rs.getString("country");
                int postalCode = rs.getInt("postalCode");

                return new Address(country, city, postalCode, street);
            }

        } catch (Exception e) {
            System.out.println("Error reading address: " + e.getMessage());
        }

        return null;
    }

}