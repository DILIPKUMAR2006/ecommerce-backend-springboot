package com.manikandan.shopping.controller;

import com.manikandan.shopping.entity.Product;
import com.manikandan.shopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController
{
    @Autowired
    private ProductService productService;

    @GetMapping
    public Map<String, Object> getAllProducts(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "5") int size)
    // → Method to get products with pagination (page number + size)
    {
        return productService.getAllProduct(page, size);   //this goes to ProductService and returns Product data as Map...
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id)
    {
        return productService.getProductbyId(id); //this goes to ProductService and returns the specific product by id..
    }

    //Controller gets request → Service builds conditions (Specification) → Repository runs query → DB filters data → Result goes back to client
    @GetMapping("/search")
    public List<Product> searchProduct(@RequestParam(required = false) String category, @RequestParam(required = false) Double minPrice, @RequestParam(required = false) Double maxPrice, @RequestParam(required = false) String keyword, @RequestParam(required = false) Double ratings)
    {
        return productService.searchProducts(category, minPrice, maxPrice, keyword, ratings);
    }
}
