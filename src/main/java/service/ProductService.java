package service;

import database.ProductDao;
import model.Product;
import utils.TrendProduct;

import java.util.ArrayList;

public class ProductService {
    private ProductDao productDao;

    public ProductService() {
        this.productDao = new ProductDao();
    }

    public int insert(Product product) {
        return productDao.insert(product);
    }

    public int update(Product product) {
        return productDao.update(product);
    }

    public int delete(Product product) {
        return productDao.delete(product);
    }

    public ArrayList<Product> selectAll() {
        return productDao.selectAll();
    }

    public Product selectById(int id) {
        return productDao.selectById(id);
    }

    public ArrayList<Product> selectNewestProducts() {
        return productDao.selectNewestProducts();
    }

    public ArrayList<Product> selectProductsByCategoryId(int categoryId) {
        return productDao.selectProductsByCategoryId(categoryId);
    }



    public ArrayList<Product> selectProductByFilter(String[] categories, String priceRange, String discount, String sortBy) {
        return productDao.selectProductByFilter(categories, priceRange, discount, sortBy);
    }



}
