package com.manikandan.shopping.seed;

import com.manikandan.shopping.entity.Product;
import com.manikandan.shopping.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

import java.util.List;

@Component //@Component allows Spring to automatically create and manage an object so it can be injected and reused across the application.
public class ProductSeeder implements CommandLineRunner  //CommandLineRunner is used to execute any custom logic at application startup, such as seeding data or initialization tasks.
{
    @Autowired
    private ProductRepository productRepository;  // Inject repository, access database operations

    @Override
    public void run(String... args) throws Exception{  //Spring Boot automatically calls the run() method of CommandLineRunner at application startup
        if(productRepository.count() == 0)
        {
            List<Product> demoProduct = List.of(
                    new Product(null,"Apple iPhone 15", 799.0,"Smartphone with A16 chip","Electronics", 4.8, "Amazon", 5, 2, List.of("/products/1.webp")),
                    new Product(null,"Samsung Galaxy S23", 749.0,"Flagship Android phone","Electronics", 4.7, "Flipkart", 8, 3, List.of("/products/2.webp")),
                    new Product(null,"OnePlus 12", 699.0,"Fast performance and smooth UI","Electronics", 4.6, "Amazon", 10, 4, List.of("/products/3.webp")),
                    new Product(null,"Realme GT 6", 499.0,"Budget performance phone","Electronics", 4.4 , "Flipkart", 15, 5, List.of("/products/4.webp")),
                    new Product(null,"iQOO Neo 9 Pro", 549.0,"Gaming focused smartphone","Electronics", 4.5, "Amazon", 12, 6, List.of("/products/5.webp")),
                    new Product(null,"Redmi Note 13 Pro", 399.0,"Best value for money","Electronics", 4.3, "Flipkart", 20, 7, List.of("/products/6.webp")),
                    new Product(null,"Google Pixel 8", 799.0,"Pure Android experience","Electronics", 4.7, "Amazon", 6, 3, List.of("/products/7.webp")),
                    new Product(null,"Nothing Phone 2", 599.0,"Unique design with glyph lights","Electronics", 4.5, "Flipkart", 9, 4, List.of("/products/8.webp")),
                    new Product(null,"Vivo V30 Pro", 529.0,"Great camera performance","Electronics", 4.4, "Amazon", 11, 5, List.of("/products/9.webp")),
                    new Product(null,"Oppo Reno 11 Pro", 579.0,"Stylish design with good camera","Electronics", 4.3, "Flipkart", 7, 2, List.of("/products/10.webp"))
            );
            productRepository.saveAll(demoProduct);
            System.out.println("Seeded Demo Products");
        }
        else
        {
            System.out.print("Seeded already exist so skip demo seeding");
        }
    }
}