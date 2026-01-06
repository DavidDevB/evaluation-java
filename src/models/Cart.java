package models;

import java.util.List;
import java.util.ArrayList;

public class Cart {

    private final List<Course> articles;

    public Cart(List<Course> articles) {
        this.articles = articles;
    }

    public void addArticle(Course course) {
        articles.add(course);
    }

    public void removeArticle(String courseName) {
        articles.removeIf(course -> course.getName().equalsIgnoreCase(courseName));
    }

    // Getter
    public List<Course> getArticles() {
        return articles;
    }
}
