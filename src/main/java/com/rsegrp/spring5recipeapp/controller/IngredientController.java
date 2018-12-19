package com.rsegrp.spring5recipeapp.controller;

import com.rsegrp.spring5recipeapp.commands.IngredientDTO;
import com.rsegrp.spring5recipeapp.commands.UnitOfMeasureDTO;
import com.rsegrp.spring5recipeapp.domain.Recipe;
import com.rsegrp.spring5recipeapp.services.IngredientService;
import com.rsegrp.spring5recipeapp.services.RecipeService;
import com.rsegrp.spring5recipeapp.services.UnitofMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/ingredients")
@Controller
@Slf4j
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitofMeasureService unitofMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitofMeasureService unitofMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitofMeasureService = unitofMeasureService;
    }

    @GetMapping("/{recipeId}/show")
    public String listIngredients(@PathVariable String recipeId, Model model){

        model.addAttribute("recipe", recipeService.findByIdDTO(recipeId));
        return "ingredients/list";

    }

    @GetMapping("{recipeId}/{ingredientId}/update")
    public String updateRecipeIngredient(@PathVariable String recipeId,
                                         @PathVariable String ingredientId, Model model){
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(recipeId,ingredientId));

        model.addAttribute("uomList", unitofMeasureService.listAllUoms());

        return "ingredients/ingredientform";

    }

    @GetMapping("/{recipeId}/new")
    public String addNewIngredient(@PathVariable String recipeId, Model model){

        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setRecipeId(recipeId);
        ingredientDTO.setUnitOfMeasure(new UnitOfMeasureDTO());

        model.addAttribute("ingredient", ingredientDTO);
        model.addAttribute("uomList", unitofMeasureService.listAllUoms());
        return "ingredients/ingredientform";
    }

    @PostMapping("/{recipeId}/update")
    public String saveOrUpdate(@ModelAttribute IngredientDTO ingredientDTO){

        IngredientDTO savedIngredientDTO = ingredientService.saveIngredientDTO(ingredientDTO);

        log.debug("saved receipe id:" + savedIngredientDTO.getRecipeId());
        log.debug("saved ingredient id:" + savedIngredientDTO.getId());

        return "redirect:/ingredients/" + savedIngredientDTO.getRecipeId() + "/show";
    }

    @GetMapping("/{recipeId}/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) throws Exception {
        Recipe recipe = recipeService.findById(recipeId);

        if (recipe == null)
        {
            //todo Add Exception treatment
            throw new Exception("Recipe not found!");
        }

        ingredientService.deleteByRecipeIdAndIngredientId(recipeId, ingredientId);

        return "redirect:/ingredients/"+recipeId+"/show" ;
    }


    @GetMapping("/{recipeId}/{ingredientId}/show")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model){

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(recipeId,ingredientId));
        return "ingredients/details";

    }
}
