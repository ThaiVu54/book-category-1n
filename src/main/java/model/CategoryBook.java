package model;

public class CategoryBook {
    private int id;
    private String name;

    public CategoryBook(int id) {
        this.id = id;
    }

    public CategoryBook() {
    }

    public CategoryBook(String name) {
        this.name = name;
    }

    public CategoryBook(int id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "CategoryBook{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
