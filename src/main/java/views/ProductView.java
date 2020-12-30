package views;

public class ProductView {
    private String productName;
    private Long productPrice;
    private String categoryName;
    private String manufacturerName;

    public ProductView(String productName, Long productPrice, String categoryName, String manufacturerName) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.categoryName = categoryName;
        this.manufacturerName = manufacturerName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Long getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Long productPrice) {
        this.productPrice = productPrice;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }
}
