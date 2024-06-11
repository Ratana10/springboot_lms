package com.kongsun.leanring.system.category;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category toCategory(CategoryDTO dto);
    CategoryDTO toCategoryDTO(Category category);
}
