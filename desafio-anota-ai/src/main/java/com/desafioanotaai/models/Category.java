package com.desafioanotaai.models;

import com.desafioanotaai.dtos.CategoryDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "categories")
@Getter
@Setter
@NoArgsConstructor
public class Category {

    @Id
    private String id;
    private String title;
    private String description;
    private String ownerId;

    public Category(CategoryDto categoryDto){
        this.title = categoryDto.title();
        this.description = categoryDto.description();
        this.ownerId = categoryDto.ownerId();
    }

    @Override
    public String toString(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", this.title);
        jsonObject.put("description", this.description);
        jsonObject.put("ownerId", this.ownerId);
        jsonObject.put("id", this.id);
        jsonObject.put("type", "category");
        System.out.println(jsonObject);
        return jsonObject.toString();
    }

}
