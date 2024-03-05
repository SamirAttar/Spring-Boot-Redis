package com.SpringRedies.Spring_Redies.DAO;

import com.SpringRedies.Spring_Redies.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAO {

    public static final String HASH_KEY = "Product";

    @Autowired
    private RedisTemplate template;

    public Product save(Product product) {
        // template.opsForHash().put(HASH_KEY, product.getId(), product);
        template.opsForHash().put(HASH_KEY, String.valueOf(product.getId()), product);

        return product;
    }

    public List<Product> findAll() {
        return template.opsForHash().values(HASH_KEY);
    }

    public Product findProductById(int id) {
        System.out.println("In DAO FindById method is executed...");
        return (Product) template.opsForHash().get(HASH_KEY, String.valueOf(id));
    }

    public String deleteProduct(int id) {
        template.opsForHash().delete(HASH_KEY, id);
        System.out.println("DAO Delete...");
        return "product removed";
    }


}
