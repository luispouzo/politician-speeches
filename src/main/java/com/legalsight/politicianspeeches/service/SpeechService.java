package com.legalsight.politicianspeeches.service;

import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.model.Speech;
import com.legalsight.politicianspeeches.repository.search.SearchOperation;
import com.legalsight.politicianspeeches.repository.search.SpeechSpecificationsBuilder;
import com.legalsight.politicianspeeches.validation.OnCreate;
import com.legalsight.politicianspeeches.validation.OnUpdate;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Validated
public interface SpeechService {
    @Validated(OnCreate.class)
    Speech create(@Valid Speech speech);

    default Specification<Speech> buildSpeechSpecification(String search) {
        SpeechSpecificationsBuilder builder = new SpeechSpecificationsBuilder();
        String operationSetExper = String.join("|", SearchOperation.SIMPLE_OPERATION_SET);
        Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExper + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
        }

        return builder.build();
    }

    List<Speech> findAll(String search);
    List<Speech> findAll(String text, String keywordsName, String subject, String authorName,
                         Date speechStartDate, Date speechEndDate);
    Speech findById(@NotNull Long id) throws PSRecourseNotFoundException;

    @Validated(OnUpdate.class)
    Speech update(@NotNull Long id, @Valid Speech speech) throws PSRecourseNotFoundException;
    void delete(@NotNull Long id);
    List<Speech> findAllByDateBetween(Date startTime, Date endTime);
    List<Speech> findAllByAuthorId(Long id);
    List<Speech> findAllBySubject(String subject);
}
