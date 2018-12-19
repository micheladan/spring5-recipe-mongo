package com.rsegrp.spring5recipeapp.services;

import com.rsegrp.spring5recipeapp.commands.RecipeDTO;
import com.rsegrp.spring5recipeapp.converters.RecipeDTOToEntity;
import com.rsegrp.spring5recipeapp.converters.RecipeEntityToDTO;
import com.rsegrp.spring5recipeapp.domain.Recipe;
import com.rsegrp.spring5recipeapp.exceptions.NotFoundException;
import com.rsegrp.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeDTOToEntity recipeDTOToEntity;
    private final RecipeEntityToDTO recipeEntityToDTO;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeDTOToEntity recipeDTOToEntity,
                             RecipeEntityToDTO recipeEntityToDTO) {
        this.recipeRepository = recipeRepository;
        this.recipeDTOToEntity = recipeDTOToEntity;
        this.recipeEntityToDTO = recipeEntityToDTO;
    }

    @Override
    public Set<Recipe> getRecipes() {
        Set<Recipe> recipes = new HashSet<>();

        recipeRepository.findAll().iterator().forEachRemaining(recipes::add);
        return recipes;
    }

    @Override
    public Recipe findById(String id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);

        if (!recipe.isPresent()) {
            throw new NotFoundException("Recipe Not Found for Id "+id.toString());
        }

        return recipe.get();
    }

    @Transactional
    @Override
    public RecipeDTO saveRecipeDTO(RecipeDTO recipeDTO) {
        Recipe detachedRecipe = recipeDTOToEntity.convert(recipeDTO);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);

        return recipeEntityToDTO.convert(savedRecipe);
    }

    @Override
    @Transactional
    public RecipeDTO findByIdDTO(String id) {
        return recipeEntityToDTO.convert(findById(id));
    }

    @Override
    public void deleteById(String id){
        recipeRepository.deleteById(id);
    }

}
