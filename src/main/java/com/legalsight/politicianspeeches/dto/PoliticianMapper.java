package com.legalsight.politicianspeeches.dto;

import com.legalsight.politicianspeeches.model.Politician;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper( componentModel = "spring", nullValueMappingStrategy =  NullValueMappingStrategy.RETURN_DEFAULT )
public interface PoliticianMapper {
    PoliticianDto politicianToPoliticianDto(Politician politician);
    Politician politicianDtoToPolitician(PoliticianDto politicianDto);
    List<PoliticianDto> politiciansToPoliticianDtos(List<Politician> politicians);

    Politician politicianToPolitician(Politician politician, @MappingTarget Politician updatablePolitician);
}
