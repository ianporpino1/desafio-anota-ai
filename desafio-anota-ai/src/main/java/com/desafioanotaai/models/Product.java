package com.desafioanotaai.models;

import com.desafioanotaai.dtos.CategoryDto;
import com.desafioanotaai.dtos.ProductDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.json.JsonObject;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
@NoArgsConstructor
public class Product {
    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;
    private Integer price;
    private String categoryId;

    public Product(ProductDto productDto){
        this.title = productDto.title();
        this.description = productDto.description();
        this.ownerId = productDto.ownerId();
        this.price = productDto.price();
        this.categoryId = productDto.categoryId();
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", this.title);
        jsonObject.put("description", this.description);
        jsonObject.put("ownerId", this.ownerId);
        jsonObject.put("price", this.price);
        jsonObject.put("categoryId", this.categoryId);
        jsonObject.put("id", this.id);
        jsonObject.put("type", "product");

        return jsonObject.toString();
    }

}
