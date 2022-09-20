package com.legalsight.politicianspeeches.dto;

import com.legalsight.politicianspeeches.model.Speech;
import org.mapstruct.Condition;
import org.mapstruct.DecoratedWith;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(
        uses = {KeywordMapper.class, PoliticianMapper.class},
        componentModel = "spring",
        nullValueMappingStrategy =  NullValueMappingStrategy.RETURN_DEFAULT )
@DecoratedWith(SpeechMapperDecorator.class)
public interface SpeechMapper {
    SpeechMapper INSTANCE = Mappers.getMapper( SpeechMapper.class );

    Speech speechDtoToSpeech(SpeechDto speechDto);

    SpeechDto speechToSpeechDto(Speech speech);
    List<SpeechDto> speechesToSpeechDtos(List<Speech> speeches);

    Speech speechToSpeech(Speech speech, @MappingTarget Speech updatableSpeech);

    @Condition
    default boolean isNotEmpty(String value) {
        return value != null && !value.isEmpty();
    }
}
