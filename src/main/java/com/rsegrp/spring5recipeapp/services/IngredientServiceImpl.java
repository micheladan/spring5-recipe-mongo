package com.rsegrp.spring5recipeapp.services;

import com.rsegrp.spring5recipeapp.commands.IngredientDTO;
import com.rsegrp.spring5recipeapp.converters.IngredientDTOtoEntity;
import com.rsegrp.spring5recipeapp.converters.IngredientEntityToDTO;
import com.rsegrp.spring5recipeapp.domain.Ingredient;
import com.rsegrp.spring5recipeapp.domain.Recipe;
import com.rsegrp.spring5recipeapp.repositories.RecipeRepository;
import com.rsegrp.spring5recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@Slf4j
public class IngredientServiceImpl implements IngredientService {

    private final IngredientEntityToDTO ingredientEntityToDTO;
    private final IngredientDTOtoEntity ingredientDTOtoEntity;
    private final RecipeRepository recipeRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;


    public IngredientServiceImpl(IngredientEntityToDTO ingredientEntityToDTO, IngredientDTOtoEntity ingredientDTOtoEntity,
                                 RecipeRepository recipeRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.ingredientEntityToDTO = ingredientEntityToDTO;
        this.ingredientDTOtoEntity = ingredientDTOtoEntity;
        this.recipeRepository = recipeRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    public IngredientDTO findByRecipeIdAndIngredientId(String recipeId, String ingredientId) {
        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if (!recipeOptional.isPresent())
        {
            log.error("eecipe id not found. Id: "+recipeId);
            return null;
        }

        Recipe recipe = recipeOptional.get();

        Optional<IngredientDTO> optionalIngredientDTO = recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientId))
                .map(ingredient -> ingredientEntityToDTO.convert(ingredient))
                .findFirst();

        if (!optionalIngredientDTO.isPresent())
        {
            log.error("Ingredient id not Found...");
            return null;
        }

        return optionalIngredientDTO.get();
    }

    @Override
    @Transactional
    public IngredientDTO saveIngredientDTO(IngredientDTO ingredientDTO ){

        Ingredient savedIngredient;
        Optional<Recipe> recipeOptional = recipeRepository.findById(ingredientDTO.getRecipeId());

        if (!recipeOptional.isPresent()){
            log.error("No recipe Found...");
            return new IngredientDTO();
        }

        Recipe recipe = recipeOptional.get();

        Optional<Ingredient> optionalIngredient = recipe
                .getIngredients()
                .stream()
                .filter(ingredient -> ingredient.getId().equals(ingredientDTO.getId()))
                .findFirst();

        if(optionalIngredient.isPresent()){
            //update the ingredient
            savedIngredient = optionalIngredient.get();
            savedIngredient.setDescription(ingredientDTO.getDescription());
            savedIngredient.setAmount(ingredientDTO.getAmount());
            savedIngredient.setUnitOfMeasure(unitOfMeasureRepository
                    .findById(ingredientDTO.getUnitOfMeasure().getId())
                    .orElseThrow(() -> new RuntimeException("UOM NOT FOUND"))); //todo address this
        } else
            {
            //add new Ingredient
            savedIngredient = ingredientDTOtoEntity.convert(ingredientDTO);
            savedIngredient.setId(UUID.randomUUID().toString());
            recipe.addIngredient(savedIngredient);
        }

        Recipe savedRecipe = recipeRepository.save(recipe);


        //to do check for fail
        return ingredientEntityToDTO.convert(savedIngredient);
    }

    @Override
    @Transactional
    public void deleteByRecipeIdAndIngredientId(String recipeId, String ingredientId) {

        log.debug("Deleting ingredient: " + recipeId + ":" + ingredientId);

        Optional<Recipe> recipeOptional = recipeRepository.findById(recipeId);

        if(recipeOptional.isPresent()){
            Recipe recipe = recipeOptional.get();
            log.debug("found recipe");

            Optional<Ingredient> ingredientOptional = recipe
                    .getIngredients()
                    .stream()
                    .filter(ingredient -> ingredient.getId().equals(ingredientId))
                    .findFirst();

            if(ingredientOptional.isPresent()){
                log.debug("found Ingredient");
                Ingredient ingredientToDelete = ingredientOptional.get();
                recipe.getIngredients().remove(ingredientOptional.get());
                recipeRepository.save(recipe);
            }
        } else {
            log.debug("Recipe Id Not found. Id:" + recipeId);
        }
    }
}
