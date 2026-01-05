package models;

public class Cart {

    private String[] articles;

    public Cart(String[] articles) {
        this.articles = articles;
    }

    public void addArticle(String courseName) {
        String[] newArticles = new String[articles.length + 1];
        System.arraycopy(articles, 0, newArticles, 0, articles.length);
        newArticles[articles.length] = courseName;
        articles = newArticles;
    }

    public void removeArticle(String courseName) {
        int index = -1;
        for (int i = 0; i < articles.length; i++) {
            if (articles[i].equals(courseName)) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            String[] newArticles = new String[articles.length - 1];
            System.arraycopy(articles, 0, newArticles, 0, index);
            System.arraycopy(articles, index + 1, newArticles, index, articles.length - index - 1);
            articles = newArticles;
        }
    }

    // Getter
    public String[] getArticles() {
        return articles;
    }
}
