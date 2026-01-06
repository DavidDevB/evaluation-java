package models;

import java.util.List;

public class Cart {

    private Client client;
    private final List<Course> articles;

    public Cart(Client client, List<Course> articles) {
        this.client = client;
        this.articles = articles;
    }

    public void setClient(Client client) {
        this.client = client;
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
