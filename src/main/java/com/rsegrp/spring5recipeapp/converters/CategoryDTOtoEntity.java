package com.rsegrp.spring5recipeapp.converters;

import com.rsegrp.spring5recipeapp.commands.CategoryDTO;
import com.rsegrp.spring5recipeapp.domain.Category;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class CategoryDTOtoEntity implements Converter<CategoryDTO, Category> {

    @Nullable
    @Override
    public Category convert(CategoryDTO source) {
        if (source == null) {
            return null;
        }

        final Category category = new Category();
        category.setId(source.getId());
        category.setDescription(source.getDescription());
        return category;
    }
}