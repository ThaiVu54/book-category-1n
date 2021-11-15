package model;

public class Book {
    private int id;
    private String name;
    private String description;
    private double price;
    private String producer;
    private CategoryBook categoryBook;

    public Book(String name, String description, double price, String producer, CategoryBook categoryBook) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.producer = producer;
        this.categoryBook = categoryBook;
    }

    public Book() {
    }

    public Book(int id, String name, String description, double price, String producer, CategoryBook categoryBook) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.producer = producer;
        this.categoryBook = categoryBook;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public CategoryBook getCategoryBook() {
        return categoryBook;
    }

    public void setCategoryBook(CategoryBook categoryBook) {
        this.categoryBook = categoryBook;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", producer='" + producer + '\'' +
                ", categoryBook=" + categoryBook +
                '}';
    }
}
