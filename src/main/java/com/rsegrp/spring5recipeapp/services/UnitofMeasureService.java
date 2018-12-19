package com.rsegrp.spring5recipeapp.services;

import com.rsegrp.spring5recipeapp.commands.UnitOfMeasureDTO;

import java.util.Set;

public interface UnitofMeasureService {

    Set<UnitOfMeasureDTO> listAllUoms();
}
