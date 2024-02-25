package com.desafioanotaai.services;

import com.desafioanotaai.dtos.CategoryDto;
import com.desafioanotaai.dtos.MessageDto;
import com.desafioanotaai.dtos.ProductDto;
import com.desafioanotaai.exceptions.CategoryNotFoundException;
import com.desafioanotaai.exceptions.ProductNotFoundException;
import com.desafioanotaai.models.Category;
import com.desafioanotaai.models.Product;
import com.desafioanotaai.repositories.CategoryRepository;
import com.desafioanotaai.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    AwsSnsService awsSnsService;

    public Product insert(ProductDto productDto) throws CategoryNotFoundException {
        Category category = categoryService.getById(productDto.categoryId()).orElseThrow(CategoryNotFoundException::new);
        var product = new Product(productDto);

        productRepository.save(product);
        awsSnsService.publish(new MessageDto(product.toString()));
        return product;
    }

    public Product update(String id,ProductDto productDto) throws ProductNotFoundException, CategoryNotFoundException {

        var product = productRepository.findById(id).orElseThrow(ProductNotFoundException::new);


        if(productDto.categoryId() != null){
            categoryService.getById(productDto.categoryId()).orElseThrow(CategoryNotFoundException::new);
            product.setCategoryId(productDto.categoryId());
        }

        if(!productDto.title().isEmpty()) product.setTitle(productDto.title());
        if(!productDto.description().isEmpty()) product.setDescription(productDto.description());
        if(!(productDto.price() == null)) product.setPrice(productDto.price());

        productRepository.save(product);
        awsSnsService.publish(new MessageDto(product.toString()));
        return product;

    }

    public List<Product> getAll(){
        return productRepository.findAll();
    }


    public void delete(String id) throws ProductNotFoundException {

        var product = productRepository.findById(id);//.orElseThrow(CategoryNotFoundException::new)
        if(product.isEmpty()){
            throw new ProductNotFoundException();
        }

        productRepository.delete(product.get());
    }
}
