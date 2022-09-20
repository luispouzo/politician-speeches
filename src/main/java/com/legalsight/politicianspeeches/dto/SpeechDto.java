package com.legalsight.politicianspeeches.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public record SpeechDto(Long id, String text, Date speechDate, String subject, List<KeywordDto> keywords,
                        PoliticianDto author) implements Serializable {
}
