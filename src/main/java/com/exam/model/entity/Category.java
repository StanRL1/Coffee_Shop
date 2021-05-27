package com.exam.model.entity;

import com.exam.model.entity.enums.CategoryName;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category extends BaseEntity{
    @Enumerated(EnumType.STRING)
    private CategoryName name;
    @Column(name = "needed_time",nullable = false)
    private int neededTime;

    public Category() {
    }

    public Category(CategoryName categoryName, int i) {
        this.name=categoryName;
        this.neededTime=i;
    }

    public CategoryName getName() {
        return name;
    }

    public void setName(CategoryName name) {
        this.name = name;
    }

    public int getNeededTime() {
        return neededTime;
    }

    public void setNeededTime(int neededTime) {
        this.neededTime = neededTime;
    }
}
