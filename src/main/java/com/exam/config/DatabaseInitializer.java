package com.exam.config;

import com.exam.service.CategoryService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class DatabaseInitializer implements CommandLineRunner {

    private final CategoryService categoryService;

    public DatabaseInitializer(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @Override
    public void run(String... args) throws Exception {
        categoryService.initCategories();
    }
//      в инит категорийс
    //if(categoryrepo.count()=0){
//    Arrays.stream(Enum.values()).forEach(item->{
//        Item item = new Item(pravim constructor);
//        itemrepository.save(item)
//    });
    //}
}
