package dao;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import models.Address;


public class AddressDao {

    public int save(String street, String city, String country, int postalCode) {

        /**
         * Sauvegarde une nouvelle adresse dans la base de données.
         * @param street Le nom de la rue
         * @param city La ville
         * @param country Le pays
         * @param postalCode Le code postal
         * @return L'ID de l'adresse nouvellement créée, ou -1 en cas d'erreur
         */

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

        /**
         * Vérifie si une adresse existe déjà dans la base de données.
         * @param street Le nom de la rue
         * @param city La ville
         * @param country Le pays
         * @param postalCode Le code postal
         * @return L'ID de l'adresse si elle existe, sinon null
         */

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

        /*
          Vérifie si une adresse existe déjà dans la base de données.
          Si elle existe, retourne son ID.
          Sinon, crée une nouvelle adresse et retourne son ID.
          @param street Le nom de la rue
         * @param city La ville
         * @param country Le pays
         * @param postalCode Le code postal
         * @return L'ID de l'adresse existante ou nouvellement créée
         */

        Integer existingId = findIdByAddress(street, city, country, postalCode);

        if (existingId != null) {
            System.out.println("✅ Adresse existante trouvée (ID: " + existingId + ")");
            return existingId;
        }

        System.out.println("➕ Création d'une nouvelle adresse...");
        return save(street, city, country, postalCode);
    }

    public Address read(int addressId) {

        /*
         * Récupère une adresse depuis la base de données en utilisant son ID.
         * @param addressId L'ID de l'adresse à récupérer
         * @return L'objet Address correspondant, ou null si non trouvé
         */

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