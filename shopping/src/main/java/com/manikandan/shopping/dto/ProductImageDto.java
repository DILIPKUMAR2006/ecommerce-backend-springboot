package com.manikandan.shopping.dto;

public class ProductImageDto
{
    private String url;

    public ProductImageDto() {
    }

    public ProductImageDto(String url) {  //this is used when that getAllProducts() --> convertToDto()  runs..
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
