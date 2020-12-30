package converters;

import models.ProductModel;
import views.ProductView;

public class ProductConverter {
    public ProductView modelToView(ProductModel productModel) {
        return new ProductView(
                productModel.getName_product(),
                productModel.getPrice(),
                productModel.getCategoryModel().getName_category(),
                productModel.getManufacturerModel().getName_manufacturer()
        );
    }
}
