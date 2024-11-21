package com.project.events_api.mapper;

import org.springframework.stereotype.Component;

import com.project.events_api.dto.CategoryDTO;
import com.project.events_api.model.Category;

@Component
public class CategoryMapper {
    
    public Category toModel(CategoryDTO dto){
        Category category = Category.builder()
            .name(dto.name())
            .description(dto.description())
            .build();

        return category;
    }

    public CategoryDTO toDTO(Category model){
        CategoryDTO dto = new CategoryDTO(
            model.getId(),
            model.getName(),
            model.getDescription()
        );

        return dto;
    }
}
