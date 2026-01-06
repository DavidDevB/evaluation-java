package dao;

import utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import models.Address;
import models.Client;
import java.util.List;
import java.util.ArrayList;

public class ClientDao {

    // Dépendance à AddressDao pour gérer les adresses
    // Utilisation de l'injection de dépendance pour une meilleure testabilité

    private final AddressDao addressDao = new AddressDao();

    public void saveWithAddress(String firstName, String lastName, String email, String phoneNumber, Address address) {

        int addressId = addressDao.saveOrGetExisting(address.getStreet(), address.getCity(), address.getCountry(), address.getPostalCode());

        if (addressId == -1) {
            System.out.println("Failed to save or retrieve address.");
            return;
        }

        String query = "INSERT INTO clients (firstName, lastName, email, phoneNumber, fk_address_id) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = DBConnection.getConnection(); PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setString(1, firstName);
            ps.setString(2, lastName);
            ps.setString(3, email);
            ps.setString(4, phoneNumber);
            ps.setInt(5, addressId);
            ps.executeUpdate();

            System.out.println("Successfully saved client.");

        } catch (SQLException e) {
            System.out.println("Error saving client: " + e.getMessage());
            e.printStackTrace();
        }

    }

    private List<Client> readAll() {
        // Implementation for reading all clients from the database
        List<Client> clients = new ArrayList<>();
        String query = "SELECT * FROM clients";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            var rs = ps.executeQuery();

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
}
