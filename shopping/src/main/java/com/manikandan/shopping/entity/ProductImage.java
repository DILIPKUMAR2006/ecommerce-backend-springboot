package com.manikandan.shopping.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "product_image")
public class ProductImage
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String publicid;
    private String url;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "product_id")
    private Product product;

//    public ProductImage(Long id, String publicid, String url){
//        this.id = id;
//        this.publicid = publicid;
//        this.url = url;
//    }

    public ProductImage(String url, Product product) {
        this.url = "/uploads"+url;      //this is product image full details
        this.publicid = url;    //it is related to file details
        this.product = product;
    }

    public ProductImage() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPublicid() {
        return publicid;
    }

    public void setPublicid(String publicid) {
        this.publicid = publicid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}