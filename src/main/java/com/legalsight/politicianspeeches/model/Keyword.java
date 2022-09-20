package com.legalsight.politicianspeeches.model;

import com.legalsight.politicianspeeches.validation.OnCreate;
import com.legalsight.politicianspeeches.validation.OnUpdate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.util.Objects;

@Table(name = "keyword", indexes = {
        @Index(name = "keyword_name_index", columnList = "name")
})
@Entity
public class Keyword {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "speech_id", nullable=false)
    private Speech speech;

    @Column(nullable = false)
    private String name;

    public Speech getSpeech() {
        return speech;
    }

    public void setSpeech(Speech speech) {
        this.speech = speech;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Keyword keyword = (Keyword) o;
        return id.equals(keyword.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}