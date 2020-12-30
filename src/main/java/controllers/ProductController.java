package controllers;

import Database.Database;
import converters.ProductConverter;
import models.CategoryModel;
import models.ManufacturerModel;
import models.ProductModel;
import views.ProductView;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private final Database database = new Database();
    private final ProductConverter productConverter = new ProductConverter();

    private ProductModel rowToProduct(ResultSet row) throws SQLException {
        //Category model
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setId_category(row.getLong("category_id"));
        categoryModel.setName_category(row.getString("category_name"));

        //Manufacturer model
        ManufacturerModel manufacturerModel = new ManufacturerModel();
        manufacturerModel.setId_manufacturer(row.getLong("manufacturer_id"));
        manufacturerModel.setName_manufacturer(row.getString("manufacturer_name"));

        //Product model
        ProductModel productModel = new ProductModel();
        productModel.setId_product(row.getLong("product_id"));
        productModel.setName_product(row.getString("product_name"));
        productModel.setPrice(row.getLong("product_price"));
        productModel.setCategoryModel(categoryModel);
        productModel.setManufacturerModel(manufacturerModel);

        return productModel;
    }

    public List<ProductView> search(
            String orderBy,
            String manufacturerName,
            String productName
    ) throws SQLException {
        List<ProductView> productViews = new ArrayList<>();
        database.connect();
        String request = "SELECT p.id_product AS product_id,\n" +
                "c.id_category AS category_id,\n" +
                "m.id_manufacturer AS manufacturer_id,\n" +
                "p.name_product AS product_name,\n" +
                "p.price AS product_price,\n" +
                "c.name_category AS category_name,\n" +
                "m.name_manufacturer AS manufacturer_name\n" +
                "FROM products p\n" +
                "INNER JOIN categories c ON p.id_category = c.id_category\n" +
                "INNER JOIN manufacturers m ON p.id_manufacturer = m.id_manufacturer\n";

        boolean productNameFilter = productName != null && !productName.equals("");
        boolean manufacturerNameFilter = manufacturerName != null && !manufacturerName.equals("");

        // Filters
        if (manufacturerNameFilter && productNameFilter)
            request += "WHERE m.name_manufacturer = '" + manufacturerName + "' AND p.name_product = '" + productName + "'\n";
        else if (manufacturerNameFilter)
            request += "WHERE m.name_manufacturer = '" + manufacturerName + "'\n";
        else if (productNameFilter)
            request += "WHERE p.name_product = '" + productName + "'\n";

        // Orders
        if (orderBy != null && !orderBy.equals(""))
            request += "ORDER BY " + orderBy + "\n";

        ResultSet res = database.execRequest(request);
        while (res.next()) {
            ProductModel productModel = rowToProduct(res);
            productViews.add(productConverter.modelToView(productModel));
        }
        database.close();
        return productViews;
    }

    public void addProduct(ProductModel productModel) throws SQLException {
        String insertRequest = "INSERT INTO `products` (`name_product`,`price`,`id_category`,`id_manufacturer`) " +
                "VALUES ('" + productModel.getName_product() + "', '" + productModel.getPrice() + "', '" + productModel.getCategoryModel().getId_category() + "', '" + productModel.getManufacturerModel().getId_manufacturer() + "');";
        database.connect();
        database.execUpdate(insertRequest);
        database.close();
    }
}
