package com.SpringRedies.Spring_Redies.Service;

import com.SpringRedies.Spring_Redies.DAO.ProductDAO;
import com.SpringRedies.Spring_Redies.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableCaching
public class ProductService {

    @Autowired
    private ProductDAO productDAO;
    
    public Product saveProduct(Product product){
        Product newProduct = productDAO.save(product);
        return newProduct;
    }

    public List<Product>getAllProduct(){
        List<Product> all = productDAO.findAll();
        return all;
    }

    @Cacheable(key="#id", value = "Product")
    public Product findById(int id){
        Product productById = productDAO.findProductById(id);
        System.out.println("In Service findById is executed....");
        return productById;
    }

    public void deleteProductById(int id){
        productDAO.deleteProduct(id);
    }

}
