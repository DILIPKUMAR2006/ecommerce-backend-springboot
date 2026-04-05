package com.manikandan.shopping.service;

import com.manikandan.shopping.dto.ProductDto;
import com.manikandan.shopping.dto.ProductImageDto;
import com.manikandan.shopping.dto.ProductReviewDto;
import com.manikandan.shopping.entity.Product;
import com.manikandan.shopping.entity.ProductImage;
import com.manikandan.shopping.entity.ProductReview;
import com.manikandan.shopping.repository.ProductRepository;
import com.manikandan.shopping.repository.ProductReviewRepository;
import com.manikandan.shopping.spec.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductService
{
    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private ProductReviewRepository productReviewRepos;

    public Map<String, Object> getAllProduct(int page, int size)
    // Method to get products with pagination (page number + size)
    {
        Pageable pageable = PageRequest.of(page,size);
        // Create pagination rule to tell database which page number and how many records to fetch
        Page<Product> products = productRepo.findAll(pageable);
        // Fetch only the required page of products from database instead of full data
        Map<String, Object> response = new HashMap<>();
        List<ProductDto> productDtos = products.getContent()
                .stream()
                .map(this::convertToDto)
                .toList();

        response.put("products", productDtos);
        // products.getContent(); --- Get the list of products for the current page only
        response.put("totalProducts", products.getTotalElements());
        // products.getTotalElements(); --- Get total number of products available in the database
        return response;
    }

    public Product getProductbyId(Long id)
    {
        return productRepo.findById(id).orElseThrow(() -> new RuntimeException("Product not found with this id" + id));
    }

    public List<Product> searchProducts(String category, Double minPrice, Double maxPrice, String keyword, Double ratings)
    {
        // Create a Specification object to hold all filter conditions
        Specification<Product> specification = Specification.where(ProductSpecification.hasCategory(category))
                .and(ProductSpecification.priceBetween(minPrice,maxPrice)
                        .and(ProductSpecification.hasNameOrDescription(keyword))
                        .and(ProductSpecification.ratingGreaterThan(ratings))
                );

        // Execute query using above conditions → fetch filtered data
        return productRepo.findAll(specification);
    }

    public ProductDto convertToDto(Product product) {

        ProductDto dto = new ProductDto();

        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        dto.setDescription(product.getDescription());
        dto.setCategory(product.getCategory());
        dto.setRatings(product.getRatings());
        dto.setSeller(product.getSeller());
        dto.setStock(product.getStock());
        dto.setNumOfReviews(product.getNumOfReviews());

        List<ProductImageDto> imageDto = product.getImages() //this returns the List of images because we already mapped by OnetoMany so, we can get by product.getImages();
                .stream()                //loops one by one
                .map(this::convertImageToDto)   //each Image object goes to convertImageToDto() method and returns the new ProductImageDto(image.getPublicid())
                .toList();           //finally all convert to list.
        dto.setImages(imageDto);

        List<ProductReviewDto> reviewDto = product.getReviews()   //same concept implemented in dto.setImages(imageDto);
                .stream()
                .map(this::convertReviewToDto)
                .toList();
        dto.setReviews(reviewDto);

        return dto;
    }

    public ProductReviewDto convertReviewToDto(ProductReview r) {
        ProductReviewDto dto = new ProductReviewDto();
        dto.setProductId(r.getId());
        dto.setRating(r.getRating());
        dto.setComment(r.getComment());
        return dto;
    }

    public ProductImageDto convertImageToDto(ProductImage image) {
        return new ProductImageDto(image.getPublicid());
    }

    public void addReview(ProductReviewDto reviewDto)
    {
        Product product = productRepo.findById(reviewDto.getProductId()).orElseThrow(() -> new RuntimeException("Product not found"));

        ProductReview productReview = new ProductReview();

        productReview.setComment(reviewDto.getComment());
        productReview.setRating(reviewDto.getRating());
        productReview.setProduct(product);

        productReviewRepos.save(productReview);
    }
}