package com.rsegrp.spring5recipeapp.converters;

import com.rsegrp.spring5recipeapp.commands.NotesDTO;
import com.rsegrp.spring5recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class NotesDTOtoEntity implements Converter<NotesDTO, Notes> {

    @Synchronized
    @Nullable
    @Override
    public Notes convert(NotesDTO source) {
        if(source == null) {
            return null;
        }

        final Notes notes = new Notes();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}