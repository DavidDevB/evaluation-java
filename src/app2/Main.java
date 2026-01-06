package app2;

import java.util.Objects;
import java.util.Scanner;

import dao.ClientDao;
import dao.CourseDao;
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
        System.out.println("Hello " + username + ", welcome to our application!");
        System.out.println("--------------");
        System.out.println("Please enter your password");
        String password = scanner.nextLine();
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
                System.out.println("Choose your client by entering your email:");
                String email = scanner.nextLine();
                System.out.println("Would you like to add courses to the shopping cart? (yes/no)");
                System.out.println("--------------");

                String addToCartResponse = scanner.nextLine();
                if (addToCartResponse.equalsIgnoreCase("yes")) {
                    Cart cart = new Cart(email, new ArrayList<>());
                    while (true) {
                        System.out.println("Choose your course by entering its name:");
                        String courseName = scanner.nextLine();
                        CourseDao courseDao1 = new CourseDao();
                        Course courseToAdd = courseDao1.read(courseName);
                        cart.addArticle(courseToAdd);
                        System.out.println("Course " + courseName + " added to the shopping cart!");
                        System.out.println("--------------");
                        System.out.println("Your shopping cart contains:");
                        System.out.println("--------------");
                        System.out.println(Objects.requireNonNull(ClientDao.read(email)).getFirstName() + " " + Objects.requireNonNull(ClientDao.read(email)).getLastName());
                        cart.getArticles().forEach(course -> {
                            System.out.println(course);
                            System.out.println("--------------");
                        });
                        System.out.println("Would you like to add another course to the shopping cart? (yes/no)");
                        String addAnotherResponse = scanner.nextLine().toLowerCase();
                        if (addAnotherResponse.equalsIgnoreCase("no")) {
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
