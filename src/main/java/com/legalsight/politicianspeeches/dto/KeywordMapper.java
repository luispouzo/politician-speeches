package com.legalsight.politicianspeeches.dto;

import com.legalsight.politicianspeeches.model.Keyword;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper( componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE )
public interface KeywordMapper {
    KeywordDto keywordToKeywordDto(Keyword keyword);
    Keyword keywordDtoToKeyword(KeywordDto keywordDto);
}
