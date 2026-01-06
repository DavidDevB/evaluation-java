package app1;

import dao.CourseDao;
import models.Course;
import java.util.Scanner;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        System.out.println("----------------------------");
        System.out.println("Welcome Stranger! Here are all the in-person courses we offer:");
        System.out.println("----------------------------");
        while (true) {
            System.out.println("Would you like to see all the in-person or online courses? Or just search for a keyword?");
            Scanner scanner = new Scanner(System.in);
            String choice = scanner.nextLine().trim().toLowerCase();


            CourseDao courseDao = new CourseDao();
            List<Course> allCourses = courseDao.readAll();

            if (allCourses != null && choice.equals("in-person")) {
                List<Course> coursesPresentiel = allCourses.stream()
                        .filter(course -> course.getType().equals("Présentiel"))
                        .toList();

                for (Course course : coursesPresentiel) {
                    System.out.println(course);
                }
            } else if (allCourses != null && choice.equals("online")) {
                List<Course> coursesEnLigne = allCourses.stream()
                        .filter(course -> course.getType().equals("Distanciel"))
                        .toList();

                for (Course course : coursesEnLigne) {
                    System.out.println(course);
                }
            } else if (allCourses != null && choice.equals("keyword")) {
                System.out.println("Enter a keyword to search for courses:");
                String keyword = scanner.nextLine().trim().toLowerCase();

                List<Course> filteredCourses = allCourses.stream()
                        .filter(course -> course.getName().toLowerCase().contains(keyword) ||
                                course.getDescription().toLowerCase().contains(keyword))
                        .toList();

                for (Course course : filteredCourses) {
                    System.out.println(course);
                }
            } else {
                System.out.println("No courses found.");
            }
        }
    }
}
