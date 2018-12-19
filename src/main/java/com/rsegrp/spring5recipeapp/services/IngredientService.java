package com.rsegrp.spring5recipeapp.services;

import com.rsegrp.spring5recipeapp.commands.IngredientDTO;

public interface IngredientService {

    IngredientDTO findByRecipeIdAndIngredientId(String recipeId, String ingredientId);

    IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO);

    void deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId);
}
