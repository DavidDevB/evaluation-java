package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import utils.DBConnection;
import java.sql.SQLException;



public class AdminDao {

    public void saveCourse(String name, String description, int duration, String type, int price ) {

        String query = "INSERT INTO courses (name, description, duration, type, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, duration);
            ps.setString(4, type);
            ps.setInt(5, price);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public void modifyCourse(String nameToChange, String description, int duration, String type, int price, String name) {

        String query = "UPDATE courses SET name = ?, description = ?, duration = ?, type = ?, price = ? WHERE name = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, nameToChange);
            ps.setString(2, description);
            ps.setInt(3, duration);
            ps.setString(4, type);
            ps.setInt(5, price);
            ps.setString(6, name);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();

        }
    }

    public void deleteCourse(String name) {

        String query = "DELETE FROM courses WHERE name = ?";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, name);

            ps.executeUpdate();

        } catch (SQLException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();

        }
    }
}
