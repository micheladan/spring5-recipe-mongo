package com.rsegrp.spring5recipeapp.converters;

import com.rsegrp.spring5recipeapp.commands.NotesDTO;
import com.rsegrp.spring5recipeapp.domain.Notes;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class EntityToNotesDTO implements Converter<Notes, NotesDTO> {

    @Synchronized
    @Nullable
    @Override
    public NotesDTO convert(Notes source) {
        if (source == null) {
            return null;
        }

        final NotesDTO notesDTO = new NotesDTO();
        notesDTO.setId(source.getId());
        notesDTO.setRecipeNotes(source.getRecipeNotes());
        return notesDTO;
    }
}