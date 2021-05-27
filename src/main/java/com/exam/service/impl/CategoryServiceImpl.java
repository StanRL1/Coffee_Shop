package com.exam.service.impl;

import com.exam.model.entity.Category;
import com.exam.model.entity.enums.CategoryName;
import com.exam.repository.CategoryRepository;
import com.exam.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public void initCategories() {
        if(this.categoryRepository.count()==0){
        Arrays.stream(CategoryName.values()).forEach(categoryName->{
            if(categoryName.name().equals("DRINK")) {
                Category category = new Category(categoryName,1);
                categoryRepository.save(category);
            }else if(categoryName.name().equals("COFFEE")){
                Category category = new Category(categoryName,2);
                categoryRepository.save(category);
            }else if(categoryName.name().equals("Other")){
                Category category = new Category(categoryName,5);
                categoryRepository.save(category);
            }else{
                Category category = new Category(categoryName,10);
                categoryRepository.save(category);
            }
    });
        }
    }

    @Override
    public Category findByCategoryName(CategoryName category) {
        return this.categoryRepository.findByName(category);
    }
}
