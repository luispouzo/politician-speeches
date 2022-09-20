package com.legalsight.politicianspeeches.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

public record SpeechSearchDto(Long id, String text, Date speechStartDate, Date speechEndDate, String subject, Set<String> keywordsName,
                              String authorName) implements Serializable {
}
