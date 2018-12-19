package com.rsegrp.spring5recipeapp.commands;

import com.rsegrp.spring5recipeapp.enums.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeDTO {

    private String id;

    @NotBlank
    @Size(min = 3, max =255)
    private String description;

    @Min(1)
    @Max(300)
    private Integer prepTime;

    @Min(1)
    @Max(300)
    private Integer cookTime;

    @Max(100)
    private Integer servings;

    private String source;

    private String url;

    private String directions;
    private List<IngredientDTO> ingredients = new ArrayList<>();
    private Byte[] image;
    private Difficulty difficulty;
    private NotesDTO notes;
    private List<CategoryDTO> categories = new ArrayList<>();
}