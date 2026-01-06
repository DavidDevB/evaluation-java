package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import models.Course;
import utils.DBConnection;
import java.sql.ResultSet;
import java.util.List;
import java.util.ArrayList;

public class CourseDao {

    public void save(String name, String description, int duration, String type, int price) {

        String query = "INSERT INTO courses (name, description, duration, type, price) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, duration);
            ps.setString(4, type);
            ps.setInt(5, price);
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error saving course: " + e.getMessage());
        }

    }

    public List<Course> readAll() {

        String query = "SELECT * FROM courses";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement ps = connection.prepareStatement(query)) {

            ResultSet rs = ps.executeQuery();

            List<Course> courses = new ArrayList<>();

            while (rs.next()) {
                // Récupérer les données de chaque cours
                String name = rs.getString("name");
                String description = rs.getString("description");
                int duration = rs.getInt("duration");
                String type = rs.getString("type");
                int price = rs.getInt("price");

                Course course = new Course(name, description, duration, type, price);
                courses.add(course);
            }

            return courses;

        } catch (Exception e) {
            System.out.println("Error reading courses: " + e.getMessage());
        }
        return null;
    }
}
