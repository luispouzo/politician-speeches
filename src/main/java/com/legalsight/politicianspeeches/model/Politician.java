package com.legalsight.politicianspeeches.model;

import com.legalsight.politicianspeeches.validation.OnCreate;
import com.legalsight.politicianspeeches.validation.OnUpdate;
import com.legalsight.politicianspeeches.validation.ValidEmail;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "politician", indexes = {
        @Index(name = "politician_name_index", columnList = "name", unique = true),
        @Index(name = "politician_email_index", columnList = "email", unique = true)
})
public class Politician implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Null(groups = OnCreate.class)
    @NotNull(groups = OnUpdate.class)
    private Long id;

    @Column(nullable = false, length = 100, unique = true)
    @NotBlank(message = "Name is mandatory", groups = OnCreate.class )
    private String name;

    @Column(unique=true, nullable = false)
    @ValidEmail(message = "Email is mandatory", groups = OnCreate.class)
    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL)
    private Set<Speech> speeches = new HashSet<>();

    public boolean add(Speech speech) {
        final boolean added = speeches.add(speech);
        speech.setAuthor(this);
        return added;
    }

    public boolean remove(Speech speech) {
        final boolean removed = speeches.remove(speech);
        speech.setAuthor(null);
        return removed;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Speech> getSpeeches() {
        return speeches;
    }

    public void setSpeeches(Set<Speech> speeches) {
        this.speeches = speeches;
    }

    @Override
    public String toString() {
        return "Politician{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", speeches=" + speeches +
                '}';
    }
}
