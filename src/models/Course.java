package models;

public class Course {

    private String name;
    private String description;
    private int duration;
    private String type;
    private int price;

    public Course(String name, String description, int duration, String type, int price) {
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.type = type;
        this.price = price;
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getDuration() {
        return duration;
    }

    public String getType() {
        return type;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Course(" +
                "name: '" + name + '\'' +
                ", description: '" + description + '\'' +
                ", duration: " + duration +
                ", type: '" + type + '\'' +
                ", price: " + price +
                ')';
    }
}
