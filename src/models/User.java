package models;

import java.util.Scanner;

public class User {

    private String username;

    public User(String username) {
        this.username = username;
    }

    public String authentify() {
        // Implementation for making an order
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    // Getter
    public String getUsername() {
        return username;
    }


}
