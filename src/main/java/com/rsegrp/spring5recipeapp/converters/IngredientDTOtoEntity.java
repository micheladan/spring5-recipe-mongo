package com.rsegrp.spring5recipeapp.converters;

import com.rsegrp.spring5recipeapp.commands.IngredientDTO;
import com.rsegrp.spring5recipeapp.domain.Ingredient;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class IngredientDTOtoEntity implements Converter<IngredientDTO, Ingredient> {

    private final UnitOfMeasureDTOtoEntity uomConverter;

    public IngredientDTOtoEntity(UnitOfMeasureDTOtoEntity uomConverter) {
        this.uomConverter = uomConverter;
    }

    @Nullable
    @Override
    public Ingredient convert(IngredientDTO source) {
        if (source == null) {
            return null;
        }

        final Ingredient ingredient = new Ingredient();
        ingredient.setId(source.getId());
        ingredient.setAmount(source.getAmount());
        ingredient.setDescription(source.getDescription());
        ingredient.setUnitOfMeasure(uomConverter.convert(source.getUnitOfMeasure()));
        return ingredient;
    }
}