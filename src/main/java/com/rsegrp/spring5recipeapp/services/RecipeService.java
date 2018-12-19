package com.rsegrp.spring5recipeapp.services;

import com.rsegrp.spring5recipeapp.commands.RecipeDTO;
import com.rsegrp.spring5recipeapp.domain.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(String id);

    RecipeDTO saveRecipeDTO(RecipeDTO command);

    RecipeDTO findByIdDTO(String id);

    void deleteById(String id);
}
