package com.legalsight.politicianspeeches.service;

import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.model.Politician;
import com.legalsight.politicianspeeches.validation.OnCreate;
import com.legalsight.politicianspeeches.validation.OnUpdate;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Validated
public interface PoliticianService {
     @Validated(OnCreate.class)
     Politician create(@Valid Politician politician);

     List<Politician> findAll(String name, String email);
     Politician findById(@NotNull Long id) throws PSRecourseNotFoundException;

     @Validated(OnUpdate.class)
     Politician update(@NotNull Long id, @Valid Politician politician) throws PSRecourseNotFoundException;
     void delete(@NotNull Long id);
}
