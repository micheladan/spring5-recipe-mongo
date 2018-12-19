package com.rsegrp.spring5recipeapp.services;

import com.rsegrp.spring5recipeapp.commands.UnitOfMeasureDTO;
import com.rsegrp.spring5recipeapp.converters.UnitOfMeasureEntityToDTO;
import com.rsegrp.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UnitOfMeasureServiceImpl implements UnitofMeasureService {

    private UnitOfMeasureRepository unitOfMeasureRepository;
    private UnitOfMeasureEntityToDTO unitOfMeasureEntityToDTO;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository unitOfMeasureRepository,
                                    UnitOfMeasureEntityToDTO unitOfMeasureEntityToDTO) {
        this.unitOfMeasureRepository = unitOfMeasureRepository;
        this.unitOfMeasureEntityToDTO = unitOfMeasureEntityToDTO;
    }

    @Override
    public Set<UnitOfMeasureDTO> listAllUoms() {
        return StreamSupport.stream(unitOfMeasureRepository.findAll()
                .spliterator(),false)
                .map(unitOfMeasureEntityToDTO::convert)
                .collect(Collectors.toSet());
    }
}
