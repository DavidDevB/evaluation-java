package app3;

import java.util.Objects;
import java.util.Scanner;
import models.User;
import dao.ClientDao;
import dao.CourseDao;
import dao.ShoppingCartDao;
import models.Course;
import models.Cart;
import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {

        System.out.println("--------------");
        System.out.println("Welcome Stranger!");
        System.out.println("--------------");
        System.out.println("Please authentify yourself (username): ");
        Scanner scanner = new Scanner(System.in);
        String username = scanner.nextLine();
        User user = new User(username);
        System.out.println("Hello " + user.getUsername() + ", welcome to our application!");
        System.out.println("--------------");
        System.out.println("Please enter your password");
        String password = user.authentify();
        if (!password.equals("123456")) {
            System.out.println("Wrong password!");
            System.out.println("Would you like to create an account? (yes/no)");
            String response = scanner.nextLine();
            if (response.equalsIgnoreCase("yes")) {
                System.out.println("Account created successfully!");
            } else {
                System.out.println("Exiting application. Goodbye!");
                scanner.close();
            }
        } else {
            System.out.println("Authentication successful!");
            System.out.println("--------------");
            System.out.println("Here are all the courses available:");
            System.out.println("--------------");
            CourseDao courseDao = new CourseDao();
            courseDao.readAll().forEach(course -> {
                System.out.println(course);
                System.out.println("--------------");
            });
            System.out.println("--------------");
            System.out.println("And all the clients available:");
            System.out.println("--------------");
            ClientDao.readAll().forEach(client -> {
                System.out.println(client);
                System.out.println("--------------");
            });
            while(true) {
                System.out.println("--------------");
                System.out.println("Choose your client by entering his/her email:");
                ClientDao clientDao = new ClientDao();
                String email = scanner.nextLine();
                if (ClientDao.read(email) == null) {
                    System.out.println("Client with email " + email + " not found. Please try again.");
                } else {
                    System.out.println("Client " + Objects.requireNonNull(ClientDao.read(email)).getFirstName() + " " + Objects.requireNonNull(ClientDao.read(email)).getLastName() + " selected.");
                    System.out.println("--------------");
                    while (true) {
                        System.out.println("Would you like to add or remove a course to/from the shopping cart? (add/remove/no)");
                        String response = scanner.nextLine().toLowerCase();
                        if (response.equalsIgnoreCase("add")) {
                            System.out.println("Enter course name:");
                            String courseName = scanner.nextLine();
                            int clientId = clientDao.getIdByEmail(email);
                            System.out.println("Email: " + email);
                            System.out.println("Client ID trouvé: " + clientId);
                            int courseId = courseDao.getIdByName(courseName);
                            System.out.println("Course name: " + courseName);
                            System.out.println("Course ID trouvé: " + courseId);
                            ShoppingCartDao.save(clientId, courseId);
                            System.out.println("Course " + courseName + " added to the shopping cart!");
                            System.out.println("--------------");
                            System.out.println("Your shopping cart contains:");
                            System.out.println("--------------");
                            System.out.println(Objects.requireNonNull(ClientDao.read(email)).getFirstName() + " " + Objects.requireNonNull(ClientDao.read(email)).getLastName());

                            int[] coursesIds = ShoppingCartDao.readAllCoursesIds();
                            for (int id : coursesIds) {
                                Course course = CourseDao.readById(id);
                                    if (course != null) {
                                    System.out.println(course);
                                    System.out.println("--------------");
                                    }
                                }
                        } else if (response.equalsIgnoreCase("remove")) {
                            System.out.println("Enter the name of the course to remove:");
                            String courseNameToRemove = scanner.nextLine();
                            int courseIdToRemove = courseDao.getIdByName(courseNameToRemove);
                            int clientId = clientDao.getIdByEmail(email);
                            ShoppingCartDao.removeFromCart(clientId, courseIdToRemove);
                            System.out.println("Course " + courseNameToRemove + " removed from the shopping cart!");
                            System.out.println("--------------");
                            System.out.println("Your shopping cart now contains:");
                            System.out.println("--------------");
                            System.out.println(Objects.requireNonNull(ClientDao.read(email)).getFirstName() + " " + Objects.requireNonNull(ClientDao.read(email)).getLastName());
                            int[] coursesIds = ShoppingCartDao.readAllCoursesIds();
                            for (int id : coursesIds) {
                                Course course = CourseDao.readById(id);
                                if (course != null) {
                                    System.out.println(course);
                                    System.out.println("--------------");
                                }
                            }
                        } else {
                            break;
                        }
                    }
                    System.out.println("Would you like to choose another client? (yes/no)");
                    String chooseAnotherClientResponse = scanner.nextLine().toLowerCase();
                    if (chooseAnotherClientResponse.equalsIgnoreCase("no")) {
                        System.out.println("Thank you for visiting! Goodbye!");
                        break;
                    } else {
                        System.out.println("--------------");
                    }
                }
            }
        }
    }
}
