package models;

public class ProductModel {
    private Long id_product;
    private String name_product;
    private Long price;
    private CategoryModel categoryModel;
    private ManufacturerModel manufacturerModel;

    public ProductModel(Long id_product, String name_product, Long price, CategoryModel categoryModel, ManufacturerModel manufacturerModel) {
        this.id_product = id_product;
        this.name_product = name_product;
        this.price = price;
        this.categoryModel = categoryModel;
        this.manufacturerModel = manufacturerModel;
    }

    public ProductModel() {

    }

    public ProductModel(String productName, Long productPrice, CategoryModel categoryModel, ManufacturerModel manufacturerModel) {
        this.name_product = productName;
        this.price = productPrice;
        this.categoryModel = categoryModel;
        this.manufacturerModel = manufacturerModel;
    }

    public Long getId_product() {
        return id_product;
    }

    public void setId_product(Long id_product) {
        this.id_product = id_product;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public CategoryModel getCategoryModel() {
        return categoryModel;
    }

    public void setCategoryModel(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public ManufacturerModel getManufacturerModel() {
        return manufacturerModel;
    }

    public void setManufacturerModel(ManufacturerModel manufacturerModel) {
        this.manufacturerModel = manufacturerModel;
    }
}


