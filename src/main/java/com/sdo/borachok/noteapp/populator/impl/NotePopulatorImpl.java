package com.sdo.borachok.noteapp.populator.impl;

import com.sdo.borachok.noteapp.dto.note.NoteUpdateRequest;
import com.sdo.borachok.noteapp.model.note.Note;
import com.sdo.borachok.noteapp.model.note.Note.Type;
import com.sdo.borachok.noteapp.populator.Populator;

import static com.sdo.borachok.noteapp.model.note.Note.Type.UNSPECIFIED;
import static java.util.Objects.nonNull;
import static org.apache.commons.lang3.EnumUtils.isValidEnum;
import static org.apache.commons.lang3.StringUtils.isNotEmpty;

public class NotePopulatorImpl implements Populator<NoteUpdateRequest, Note>
{
    @Override
    public void populate(final NoteUpdateRequest source, final Note target)
    {
        final String title = source.getTitle();
        final String text = source.getText();
        final String type = source.getType();
        final String expireDate = source.getExpireDate();

        if (isNotEmpty(title)) {
            target.setTitle(title);
        }

        if (nonNull((text))) {
            target.setText(text);
        }

        if (isValidEnum(Type.class, type)) {
            target.setType(Type.valueOf(type));
        } else {
            target.setType(UNSPECIFIED);
        }

        if (nonNull(expireDate)) {
            // TODO: create DateFormatterUtils
            //target.setExpireDate();
        }
    }
}
