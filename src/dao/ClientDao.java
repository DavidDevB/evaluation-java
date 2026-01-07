package dao;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Address;
import models.Client;
import java.util.List;
import java.util.ArrayList;
import java.sql.ResultSet;

public class ClientDao {

    private static final AddressDao addressDao = new AddressDao();

    public static List<Client> readAll() {

        /**
         * Implementation for reading all clients from the database
         * Utilisation de PreparedStatement pour éviter les injections SQL
         * Retourne une liste vide si aucun client n'est trouvé
         * Retourne une liste de clients si trouvés
         * @return List<Client>
         */

        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                // Récupérer les données de chaque client
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String email = rs.getString("email");
                String phoneNumber = rs.getString("phoneNumber");
                int addressId = rs.getInt("fk_address_id");

                Address address = addressDao.read(addressId);
                Client client = new Client(firstName, lastName, email, address, phoneNumber);
                clients.add(client);
            }
            return clients;

        } catch (Exception e) {
            System.out.println("Error reading clients: " + e.getMessage());
        }
        return clients;
    }

    public static Client read(String email) {

        /**
         * Implementation for reading a client by email from the database
         * Utilisation de PreparedStatement pour éviter les injections SQL
         * Retourne null si le client n'est pas trouvé
         * Retourne le client si trouvé
         * @param email String
         * @return Client
         */

        String query = "SELECT * FROM clients WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String firstName = rs.getString("firstName");
                String lastName = rs.getString("lastName");
                String phoneNumber = rs.getString("phoneNumber");
                int addressId = rs.getInt("fk_address_id");

                Address address = addressDao.read(addressId);
                return new Client(firstName, lastName, email, address, phoneNumber);
            }

        } catch (Exception e) {
            System.out.println("Error reading client: " + e.getMessage());
        }
        return null;
    }

    public int getIdByEmail(String email) {

        /**
         * Implementation for getting a client ID by email from the database
         * Utilisation de PreparedStatement pour éviter les injections SQL
         * Retourne -1 si le client n'est pas trouvé
         * Retourne l'ID du client si trouvé
         * @param email String
         * @return int
         */

        String query = "SELECT client_id FROM clients WHERE email = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt("client_id");
            }

        } catch (Exception e) {
            System.out.println("Error getting client ID: " + e.getMessage());
        }
        return -1;
    }
}
