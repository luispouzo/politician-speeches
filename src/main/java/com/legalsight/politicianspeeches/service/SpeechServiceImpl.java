package com.legalsight.politicianspeeches.service;

import com.legalsight.politicianspeeches.dto.SpeechMapper;
import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.model.Speech;
import com.legalsight.politicianspeeches.repository.PoliticianRepository;
import com.legalsight.politicianspeeches.repository.SpeechRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class SpeechServiceImpl implements SpeechService{

    private static final Logger logger = LoggerFactory.getLogger(SpeechServiceImpl.class);

    private final SpeechRepository speechRepository;
    private final SpeechMapper speechMapper;
    private final PoliticianRepository politicianRepository;

    public SpeechServiceImpl(SpeechRepository speechRepository, SpeechMapper speechMapper, PoliticianRepository politicianRepository) {
        this.speechRepository = speechRepository;
        this.speechMapper = speechMapper;
        this.politicianRepository = politicianRepository;
    }

    @Override
    public Speech create(Speech speech) {
        logger.info("Creating the speech {}", speech);
        politicianRepository.save(speech.getAuthor());
        return speechRepository.save(speech);
    }

    @Override
    public List<Speech> findAll(String search) {
        logger.info("Retrieving all Speeches");
        return speechRepository.findAll(buildSpeechSpecification(search));
    }

    @Override
    public List<Speech> findAll(String text, String keywordsName, String subject, String authorName,
                                Date speechStartDate, Date speechEndDate) {

        logger.info("Retrieving all Speeches");

        final List<Speech> speeches = speechRepository.searchAll(
                subject,
                authorName,
                text,
                keywordsName,
                speechStartDate,
                speechEndDate
        );
        return speeches;
    }

    @Override
    public Speech findById(Long id) throws PSRecourseNotFoundException {
        logger.info("Finding the Speech {}", id);
        return speechRepository.findById(id)
                .orElseThrow(() -> new PSRecourseNotFoundException(String.format("Speech %d not founded", id)));
    }

    @Override
    public Speech update(Long id, Speech speech) throws PSRecourseNotFoundException {
        logger.info("Updating the Speech {}", id);
        var updatableSpeech = findById(id);
        var speechUpdated = speechMapper.speechToSpeech(speech, updatableSpeech);

        return speechRepository.save(speechUpdated);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting the Speech {}", id);
        speechRepository.deleteById(id);
    }

    @Override
    public List<Speech> findAllByDateBetween(Date startTime, Date endTime) {
        logger.info("Retrieving all Speeches between {} and {}", startTime, endTime);
        return speechRepository.findAllBySpeechDateBetween(startTime, endTime);
    }

    @Override
    public List<Speech> findAllByAuthorId(Long authorId) {
        logger.info("Retrieving all Speeches of the author {}", authorId);
        return speechRepository.findAllByAuthorId(authorId);
    }

    @Override
    public List<Speech> findAllBySubject(String subject) {
        logger.info("Retrieving all Speeches with subject {}", subject);
        return speechRepository.findAllBySubject(subject);
    }
}
