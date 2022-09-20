package com.legalsight.politicianspeeches.model;

import com.legalsight.politicianspeeches.validation.OnCreate;
import com.legalsight.politicianspeeches.validation.OnUpdate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "speech", indexes = {
        @Index(name = "speech_date_index", columnList = "speech_date"),
        @Index(name = "speech_subject_index", columnList = "subject")
})
public class Speech implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "speech_date")
    private Date speechDate;

    @Column(name = "subject")
    private String subject;

    @OneToMany(mappedBy = "speech", cascade = CascadeType.ALL)
    private List<Keyword> keywords = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="politician_id", nullable=false)
    private Politician author;

    public boolean addKeyword(Keyword keyword) {
        final boolean added = keywords.add(keyword);
        keyword.setSpeech(this);
        return added;
    }

    public boolean removeKeyword(Keyword keyword) {
        final boolean removed = keywords.remove(keyword);
        keyword.setSpeech(null);
        return removed;
    }

    public boolean addAll(Collection<? extends Keyword> c) {
        final boolean allAdded = keywords.addAll(c);
        c.forEach(keyword -> keyword.setSpeech(this));
        return allAdded;
    }

    public boolean removeAll(Collection<?> c) {
        return keywords.removeAll(c);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getSpeechDate() {
        return speechDate;
    }

    public void setSpeechDate(Date speechDate) {
        this.speechDate = speechDate;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Keyword> getKeywords() {
        return keywords;
    }

    public void setKeywords(List<Keyword> keywords) {
        this.keywords = keywords;
    }

    public Politician getAuthor() {
        return author;
    }

    public void setAuthor(Politician author) {
        this.author = author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Speech speech = (Speech) o;
        return id.equals(speech.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Speech{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", speechDate=" + speechDate +
                ", subject='" + subject + '\'' +
                ", keywords=" + keywords +
                ", author=" + author +
                '}';
    }
}
