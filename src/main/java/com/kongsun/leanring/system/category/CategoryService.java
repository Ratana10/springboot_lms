package com.kongsun.leanring.system.category;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


public interface CategoryService {
    Category create(Category category);
    Category getById(Long id);
    Category update(Long id, Category category);
    void deleteById(Long id);
    List<Category> getAll();

}
