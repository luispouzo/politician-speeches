package com.legalsight.politicianspeeches.util;

import com.legalsight.politicianspeeches.model.Keyword;
import com.legalsight.politicianspeeches.model.Politician;
import com.legalsight.politicianspeeches.model.Speech;

import java.util.Date;
import java.util.List;
import java.util.Random;

public class PSTestUtils {
    public static Politician newTestPoliticianWithId99() {
        Politician politician = new Politician();
        politician.setEmail("fakeemail@fake.com");
        politician.setId(99l);
        politician.setName("Random Politician");
        return politician;
    }

    public static Politician newTestPoliticianWithRandomId() {
        Random rand = new Random(); //instance of random class
        final long randomId = rand.nextLong(99);
        Politician politician = new Politician();
        politician.setEmail(randomId + "__fakeemail@fake.com");
        politician.setId(randomId);
        politician.setName("Random Politician " + randomId);
        return politician;
    }

    public static List<Politician> newTestPoliticianList5(){
        return List.of(
                newTestPoliticianWithRandomId(),
                newTestPoliticianWithRandomId(),
                newTestPoliticianWithRandomId(),
                newTestPoliticianWithRandomId(),
                newTestPoliticianWithRandomId());
    }

    public static Speech newTestSpeech() {
        Speech speech = new Speech();
        speech.setId(99l);
        speech.setAuthor(newTestPoliticianWithId99());
        speech.setSpeechDate(new Date());
        speech.setKeywords(newTestKeywordList5());
        speech.setText("This speech is about something cool!");
        speech.setSubject("Interesting Speech");

        return speech;
    }

    private static List<Keyword> newTestKeywordList5() {
        return List.of(
                newTestKeyword(),
                newTestKeyword(),
                newTestKeyword(),
                newTestKeyword(),
                newTestKeyword()
        );
    }

    private static Keyword newTestKeyword() {
        final Keyword keyword = new Keyword();
        Random rand = new Random();
        final long randomId = rand.nextLong(99);
        keyword.setId(randomId);
        keyword.setName("cool" + randomId);
        return keyword;
    }
}
