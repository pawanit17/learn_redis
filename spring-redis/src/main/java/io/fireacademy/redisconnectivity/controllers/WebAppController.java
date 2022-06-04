package io.fireacademy.redisconnectivity.controllers;

import io.fireacademy.redisconnectivity.model.Product;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/products")
public class WebAppController {

    private static final Logger logger = LoggerFactory.getLogger(WebAppController.class);

    // This will serve as the database
    private Map<String, Product> m_productDatabase = new HashMap<String, Product>();

    @Cacheable(value="my-product-cache", key="#productId")
    private Product getProductFromCacheOrDB(String productId)
    {
        logger.info("Loading the Product " + productId + " from the cache!.");
        return m_productDatabase.get(productId);
    }

    @CacheEvict(value="my-product-cache", key="#productId")
    private Product deleteFromCache(String productId)
    {
        logger.info("Remove the Product " + productId + " from the cache!.");
        return m_productDatabase.get(productId);
    }

    @GetMapping(path="/")
    public ResponseEntity<List<Product>> getProducts()
    {
        Collection<Product> allProducts = m_productDatabase.values();

        List<Product> allProductsAsList = allProducts.stream().collect(Collectors.toList());

        return new ResponseEntity<List<Product>>(allProductsAsList, HttpStatus.OK);
    }

    @GetMapping(path="/{productId}")
    public ResponseEntity<Product> getProducts(@PathVariable String productId)
    {
        // Either from the Cache or from the DB.
        Product product = getProductFromCacheOrDB(productId);

        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @PostMapping(consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> createProduct(@RequestBody Product product)
    {
        m_productDatabase.put(product.getId(), product);

        return new ResponseEntity<Product>(product, HttpStatus.CREATED);
    }

    @PutMapping(path="/{productId}",
                consumes = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE},
                produces = {MediaType.APPLICATION_XML_VALUE,MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Product> updateProduct(@PathVariable String productId, @RequestBody Product product)
    {
        m_productDatabase.put(productId, product);
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }

    @DeleteMapping(path="/{productId}")
    public ResponseEntity<Product> deleteProduct(@PathVariable String productId)
    {
        Product deletedProduct = getProductFromCacheOrDB(productId);
        deleteFromCache(productId);

        return new ResponseEntity<Product>(deletedProduct, HttpStatus.OK);
    }
}
