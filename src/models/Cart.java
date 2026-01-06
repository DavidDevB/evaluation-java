package models;

import java.util.List;
import dao.ClientDao;

public class Cart {

    private Client client;
    private final List<Course> articles;

    public Cart(String email, List<Course> articles) {
        this.client = ClientDao.read(email);
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
