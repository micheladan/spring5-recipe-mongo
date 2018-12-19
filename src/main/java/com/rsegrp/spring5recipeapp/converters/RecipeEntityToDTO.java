package com.rsegrp.spring5recipeapp.converters;

import com.rsegrp.spring5recipeapp.commands.RecipeDTO;
import com.rsegrp.spring5recipeapp.domain.Category;
import com.rsegrp.spring5recipeapp.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeEntityToDTO implements Converter<Recipe, RecipeDTO> {

    private final CategoryEntityToDTO categoryConveter;
    private final IngredientEntityToDTO ingredientConverter;
    private final EntityToNotesDTO notesConverter;

    public RecipeEntityToDTO(CategoryEntityToDTO categoryConveter, IngredientEntityToDTO ingredientConverter,
                             EntityToNotesDTO notesConverter) {
        this.categoryConveter = categoryConveter;
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeDTO convert(Recipe source) {
        if (source == null) {
            return null;
        }

        final RecipeDTO command = new RecipeDTO();
        command.setId(source.getId());
        command.setCookTime(source.getCookTime());
        command.setPrepTime(source.getPrepTime());
        command.setDescription(source.getDescription());
        command.setDifficulty(source.getDifficulty());
        command.setDirections(source.getDirections());
        command.setServings(source.getServings());
        command.setSource(source.getSource());
        command.setImage(source.getImage());
        command.setUrl(source.getUrl());
        command.setNotes(notesConverter.convert(source.getNotes()));

        if (source.getCategories() != null && source.getCategories().size() > 0){
            source.getCategories()
                    .forEach((Category category) -> command.getCategories().add(categoryConveter.convert(category)));
        }

        if (source.getIngredients() != null && source.getIngredients().size() > 0){
            source.getIngredients()
                    .forEach(ingredient -> command.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        return command;
    }
}