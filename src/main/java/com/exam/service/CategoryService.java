package com.exam.service;

import com.exam.model.entity.Category;
import com.exam.model.entity.enums.CategoryName;

public interface CategoryService {
    void initCategories();

    Category findByCategoryName(CategoryName category);
}
