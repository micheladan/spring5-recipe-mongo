package com.rsegrp.spring5recipeapp.repositories;

import com.rsegrp.spring5recipeapp.domain.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe,String> {
}
