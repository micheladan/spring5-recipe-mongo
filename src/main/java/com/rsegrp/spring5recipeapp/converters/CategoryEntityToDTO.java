package com.rsegrp.spring5recipeapp.converters;

import com.rsegrp.spring5recipeapp.commands.CategoryDTO;
import com.rsegrp.spring5recipeapp.domain.Category;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryEntityToDTO implements Converter<Category, CategoryDTO> {

    @Synchronized
    @Nullable
    @Override
    public CategoryDTO convert(Category source) {
        if (source == null) {
            return null;
        }

        final CategoryDTO categoryDTO = new CategoryDTO();

        categoryDTO.setId(source.getId());
        categoryDTO.setDescription(source.getDescription());

        return categoryDTO;
    }
}