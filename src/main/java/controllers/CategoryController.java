package controllers;

import Database.Database;
import models.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryController {
    private final Database database = new Database();

    public CategoryModel addNameCategory(CategoryModel categoryModel) throws SQLException {
        String selectRequest = "SELECT * FROM categories WHERE name_category = '" + categoryModel.getName_category() + "'";
        String insertRequest = "INSERT INTO `categories` (`name_category`) VALUES ('" + categoryModel.getName_category() + "');";
        CategoryModel insertedCategory = new CategoryModel();
        database.connect();
        if (!database.execRequest(selectRequest).next()) {
            database.execUpdate(insertRequest);

        }
        ResultSet res = database.execRequest(selectRequest);
        res.next();
        insertedCategory.setId_category(res.getLong("id_category"));
        insertedCategory.setName_category(res.getString("name_category"));
        database.close();
        return insertedCategory;
    }
}
