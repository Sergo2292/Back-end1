package org.example.HW6;

import lombok.SneakyThrows;
import org.example.HW6.db.model.Categories;
import org.example.HW6.db.model.Products;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class ProductTest extends AbstractTest {
    Products product, productInDB, modifyProduct;
    List<Products> productsList;
    List<Categories> categoriesList;
    Long idProduct;
    int productCountBefore;

    @SneakyThrows
    @Test
    void productTest() {
        // Count products before changes:
        productsList = getProductsMapper().selectByExample(getProductsExample());
        productCountBefore = productsList.size();

        // 1. Check categories
        categoriesList = getCategoriesMapper().selectByExample(getCategoriesExample());
        assertThat(categoriesList.size(), greaterThan(0));

        // 2. Check selected category
        getCategoriesExample().createCriteria().andIdEqualTo(1);
        categoriesList = getCategoriesMapper().selectByExample(getCategoriesExample());
        assertThat(categoriesList.size(), equalTo(1));
        getCategoriesExample().clear();
        getCategoriesExample().createCriteria().andTitleLike("Food");
        categoriesList = getCategoriesMapper().selectByExample(getCategoriesExample());
        assertThat(categoriesList.size(), equalTo(1));
        getCategoriesExample().clear();

        // 3. Check selected product title
        getProductsExample().createCriteria().andTitleLike("Kimpab");
        productsList = getProductsMapper().selectByExample(getProductsExample());
        assertThat(productsList.size(), equalTo(0));
        getProductsExample().clear();

        // 4. Create new product:
        product = new Products();
        product.setTitle("Kimpab");
        product.setCategory_id(1L);
        product.setPrice((int) (Math.random() * 1000));
        getProductsMapper().insert(product);
        getSqlSession().commit();

        idProduct = product.getId();

        productsList = getProductsMapper().selectByExample(getProductsExample());
        assertThat(productsList.size(), equalTo(productCountBefore + 1));

        // 5. Get new product:
        productInDB = getProductsMapper().selectByPrimaryKey(idProduct);
        assertThat(productInDB.getTitle(), containsStringIgnoringCase(product.getTitle()));
        assertThat(productInDB.getCategory_id(), equalTo(product.getCategory_id()));
        assertThat(productInDB.getPrice(), equalTo(product.getPrice()));
    }
}