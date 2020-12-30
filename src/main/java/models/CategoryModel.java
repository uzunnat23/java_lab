package models;

public class CategoryModel {
    private Long id_category;
    private String name_category;

    public CategoryModel(Long id_category, String name_category) {
        this.id_category = id_category;
        this.name_category = name_category;
    }

    public CategoryModel(String name_category) {
        this.name_category = name_category;
    }

    public CategoryModel() {

    }

    public Long getId_category() {
        return id_category;
    }

    public void setId_category(Long id_category) {
        this.id_category = id_category;
    }

    public String getName_category() {
        return name_category;
    }

    public void setName_category(String name_category) {
        this.name_category = name_category;
    }
}
