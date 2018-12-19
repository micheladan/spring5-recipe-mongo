package com.rsegrp.spring5recipeapp.converters;


import com.rsegrp.spring5recipeapp.commands.IngredientDTO;
import com.rsegrp.spring5recipeapp.domain.Ingredient;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class IngredientEntityToDTO implements Converter<Ingredient, IngredientDTO> {

    private final UnitOfMeasureEntityToDTO uomConverter;

    public IngredientEntityToDTO(UnitOfMeasureEntityToDTO uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Synchronized
    @Nullable
    @Override
    public IngredientDTO convert(Ingredient ingredient) {
        if (ingredient == null) {
            return null;
        }

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setId(ingredient.getId());

        ingredientDTO.setAmount(ingredient.getAmount());
        ingredientDTO.setDescription(ingredient.getDescription());
        ingredientDTO.setUnitOfMeasure(uomConverter.convert(ingredient.getUnitOfMeasure()));
        return ingredientDTO;
    }
}