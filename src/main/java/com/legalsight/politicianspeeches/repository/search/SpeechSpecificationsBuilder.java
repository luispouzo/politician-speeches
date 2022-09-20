package com.legalsight.politicianspeeches.repository.search;

import com.legalsight.politicianspeeches.model.Speech;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class SpeechSpecificationsBuilder {
    private final List<SpecSearchCriteria> params;

    public SpeechSpecificationsBuilder() {
        params = new ArrayList<>();
    }

    public SpeechSpecificationsBuilder with(
            String key, String operation, Object value, String prefix, String suffix) {

        SearchOperation op = SearchOperation.getSimpleOperation(operation.charAt(0));
        if (op != null) {
            if (op == SearchOperation.EQUALITY) {
                boolean startWithAsterisk = prefix.contains("*");
                boolean endWithAsterisk = suffix.contains("*");

                if (startWithAsterisk && endWithAsterisk) {
                    op = SearchOperation.CONTAINS;
                } else if (startWithAsterisk) {
                    op = SearchOperation.ENDS_WITH;
                } else if (endWithAsterisk) {
                    op = SearchOperation.STARTS_WITH;
                }
            }
            params.add(new SpecSearchCriteria(key, op, value));
        }
        return this;
    }

    public Specification<Speech> build() {
        if (params.size() == 0)
            return null;

        Specification<Speech> result = new SpeechSpecification(params.get(0));

        for (int i = 1; i < params.size(); i++) {
            result = params.get(i).isOrPredicate()
                    ? Specification.where(result).or(new SpeechSpecification(params.get(i)))
                    : Specification.where(result).and(new SpeechSpecification(params.get(i)));
        }

        return result;
    }
}
