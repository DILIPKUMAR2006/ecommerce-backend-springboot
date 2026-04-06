# 🛒 Spring Boot Shopping REST API

> A RESTful backend for an e-commerce shopping platform — built with Spring Boot and tested via Postman.

---

## 🧰 Tech Stack

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Data JPA](https://img.shields.io/badge/Spring_Data_JPA-6DB33F?style=for-the-badge&logo=spring&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-4479A1?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)

---

## 📌 Features

- Product listing with pagination
- Search & filter by category, price, keyword, and ratings
- Add and retrieve product reviews
- Create orders and fetch by reference ID
- Auto seeds demo products on first run

---

## 📁 Project Structure

```
com.manikandan.shopping
├── controller      → OrderController, ProductController, ProductReviewController
├── dto             → Request/Response data transfer objects
├── entity          → JPA entities (Product, Order, OrderItem, etc.)
├── repository      → Spring Data JPA interfaces
├── seed            → ProductSeeder (auto-runs on startup)
├── service         → Business logic (OrderService, ProductService)
├── spec            → ProductSpecification (dynamic filtering)
└── ShoppingApplication.java
```

---

## ▶️ How to Run

1. Clone the repo and open in your IDE
2. Set your DB credentials in `application.properties`
3. Run `ShoppingApplication.java`
4. Server starts at `http://localhost:8080`
   
---

## 🧪 Testing

All endpoints are tested using **Postman**.

**Base URL:** `http://localhost:8080`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/api/products` | Get all products |
| `GET` | `/api/products/{id}` | Get product by ID |
| `GET` | `/api/products/search` | Search & filter |
| `POST` | `/api/products/reviews` | Add a review |
| `POST` | `/api/orders` | Create an order |
| `GET` | `/api/orders/{reference_id}` | Get order by reference ID |

---

## 📐 Architecture
```
[Request]
Postman → Controller → Service → Repository → MySQL

[Response]
MySQL → Repository → Service → DTO → Controller → Postman
```

---

## 👤 Author

**Dilipkumar**

- GitHub: [@dilipkumar](https://github.com/DILIPKUMAR2006)
- LinkedIn: [dilipkumar](https://linkedin.com/in/dilipkumar2410)
