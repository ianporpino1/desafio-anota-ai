package com.desafioanotaai.services;

import com.desafioanotaai.dtos.CategoryDto;
import com.desafioanotaai.dtos.MessageDto;
import com.desafioanotaai.exceptions.CategoryNotFoundException;
import com.desafioanotaai.models.Category;
import com.desafioanotaai.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    AwsSnsService awsSnsService;

    public Category insert(CategoryDto categoryDto){
        var category = new Category(categoryDto);
        categoryRepository.save(category);
        awsSnsService.publish(new MessageDto(category.toString()));
        return category;
    }

    public Category update(String id,CategoryDto categoryDto) throws CategoryNotFoundException {

        var category = categoryRepository.findById(id).orElseThrow(CategoryNotFoundException::new);


        if(!categoryDto.title().isEmpty()) category.setTitle(categoryDto.title());
        if(!categoryDto.description().isEmpty()) category.setDescription(categoryDto.description());

        categoryRepository.save(category);
        awsSnsService.publish(new MessageDto(category.toString()));
        return category;

    }

    public List<Category> getAll(){
        return categoryRepository.findAll();
    }

    public Optional<Category> getById(String id){
        return categoryRepository.findById(id);
    }

    public void delete(String id) throws CategoryNotFoundException {

        var category = categoryRepository.findById(id);//.orElseThrow(CategoryNotFoundException::new)
        if(category.isEmpty()){
            throw new CategoryNotFoundException();
        }

        categoryRepository.delete(category.get());
    }
}
