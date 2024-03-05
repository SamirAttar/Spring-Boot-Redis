package com.SpringRedies.Spring_Redies.Controller;

import com.SpringRedies.Spring_Redies.DAO.ProductDAO;
import com.SpringRedies.Spring_Redies.Model.Product;
import com.SpringRedies.Spring_Redies.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@EnableCaching
public class ProductController {

    //-------DAO -> Service -> Controller------//
    
    @Autowired
    private ProductService productService;

    //http://localhost:8081/api/product/service/save
    @PostMapping("/service/save")
    public ResponseEntity<Product> saveProduct(@RequestBody Product product) {
        Product product1 = productService.saveProduct(product);
        return new ResponseEntity<>(product1, HttpStatus.CREATED);
    }

    @GetMapping("/service")
    public ResponseEntity<List<Product>> findAllProduct() {
        List<Product> allProduct = productService.getAllProduct();
        return new ResponseEntity<>(allProduct, HttpStatus.OK);
    }

    @GetMapping("/service/get/{id}")
    public ResponseEntity<Product> findById(@PathVariable int id) {
        Product byId = productService.findById(id);
        System.out.println("Controller method executed....");
        return new ResponseEntity<>(byId, HttpStatus.OK);
    }

    @DeleteMapping("/service/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        productService.deleteProductById(id);
        return new ResponseEntity<>("Product is Deleted!!!", HttpStatus.GONE);
    }


    //---------------------------DAO -> Controller---------------//

    @Autowired
    private ProductDAO productDAO;

    @PostMapping("/save")
    public Product save(@RequestBody Product product) {
        Product save = productDAO.save(product);
        return save;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        List<Product> all = productDAO.findAll();
        return all;
    }

    @GetMapping("/{id}")
    @Cacheable(key="#id", value = "Product",unless = "#result.price>1000")// In this casheable we can put certain condition for getting certin kind of data ex. Here we dont want to chech that product who have price is more than 1000. Here we will cashe only that product who has price is lower than 1000
    public Product findProductById(@PathVariable int id) {
        Product productById = productDAO.findProductById(id);
        return productById;
    }

    @DeleteMapping("/delete/{id}")
    @CacheEvict(key="#id", value = "Product")
    public String remove(@PathVariable int id) {
        return productDAO.deleteProduct(id);
    }
}
