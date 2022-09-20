package com.legalsight.politicianspeeches.dto;

import com.legalsight.politicianspeeches.model.Speech;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public abstract class SpeechMapperDecorator implements SpeechMapper{

    @Autowired
    @Qualifier("delegate")
    private SpeechMapper delegate;

    @Override
    public Speech speechDtoToSpeech(SpeechDto speechDto) {
        final Speech speechDtoToSpeech = delegate.speechDtoToSpeech(speechDto);
        if (speechDtoToSpeech != null && speechDtoToSpeech.getKeywords() != null) {
            speechDtoToSpeech.getKeywords().stream().forEach(k -> k.setSpeech(speechDtoToSpeech));
        }
        return speechDtoToSpeech;
    }
}
