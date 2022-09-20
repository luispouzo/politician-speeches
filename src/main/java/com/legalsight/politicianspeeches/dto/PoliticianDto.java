package com.legalsight.politicianspeeches.dto;

import java.io.Serializable;

public record PoliticianDto(Long id, String name, String email) implements Serializable {
}
