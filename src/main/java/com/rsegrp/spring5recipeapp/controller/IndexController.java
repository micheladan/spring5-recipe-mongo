package com.rsegrp.spring5recipeapp.controller;

import com.rsegrp.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }


    @GetMapping(value = {"","/","index"})
    public String getIndexPage(Model model){

        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }


}
