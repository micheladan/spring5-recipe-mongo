package com.rsegrp.spring5recipeapp.converters;

import com.rsegrp.spring5recipeapp.commands.UnitOfMeasureDTO;
import com.rsegrp.spring5recipeapp.domain.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class UnitOfMeasureEntityToDTO implements Converter<UnitOfMeasure, UnitOfMeasureDTO> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureDTO convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure != null) {
            final UnitOfMeasureDTO uomc = new UnitOfMeasureDTO();
            uomc.setId(unitOfMeasure.getId());
            uomc.setDescription(unitOfMeasure.getDescription());
            return uomc;
        }
        return null;
    }
}