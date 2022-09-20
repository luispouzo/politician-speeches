package com.legalsight.politicianspeeches.controller;

import com.legalsight.politicianspeeches.dto.SpeechDto;
import com.legalsight.politicianspeeches.dto.SpeechMapper;
import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.service.SpeechService;
import io.swagger.v3.oas.annotations.Hidden;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/speeches")
public class SpeechController {

    private static final Logger logger = LoggerFactory.getLogger(SpeechController.class);

    private final SpeechService speechService;
    private final SpeechMapper speechMapper;

    public SpeechController(SpeechService speechService, SpeechMapper speechMapper) {
        this.speechService = speechService;
        this.speechMapper = speechMapper;
    }

    @PostMapping
    public SpeechDto create(@Valid @RequestBody SpeechDto speechDto) {
        return speechMapper.speechToSpeechDto(speechService.create(speechMapper.speechDtoToSpeech(speechDto)));
    }

    @GetMapping("/filter")
    public List<SpeechDto> findAll(@RequestParam(value = "search") String search) {

        return speechMapper.speechesToSpeechDtos(speechService.findAll(search));
    }

    @GetMapping
    public List<SpeechDto> findAll(
            @RequestParam(required = false) String text,
            @RequestParam(required = false) String keywordsName,
            @RequestParam(required = false) String subject,
            @RequestParam(required = false) String authorName,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date speechStartDate,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date speechEndDate) {

        return speechMapper.speechesToSpeechDtos(speechService.findAll(text, keywordsName, subject, authorName,
                speechStartDate, speechEndDate));
    }

    @GetMapping("/{id}")
    public SpeechDto findById(@PathVariable Long id) {
        try {
            logger.info("Looking  for Speech {}", id);
            return speechMapper.speechToSpeechDto(speechService.findById(id));
        } catch (PSRecourseNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Speech Not Found", e);
        }
    }

    @PutMapping("/{id}")
    public SpeechDto update(@PathVariable Long id, @RequestBody SpeechDto speechDto){
        logger.info("Updating the Speech {}", id);
        try {
            return speechMapper.speechToSpeechDto(speechService.update(id, speechMapper.speechDtoToSpeech(speechDto)));
        } catch (PSRecourseNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Speech Not Found", e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Removing the Speech {}", id);
        speechService.delete(id);
    }

    @Hidden
    @GetMapping("/between")
    public List<SpeechDto> findAllByDateBetween(
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam Date speechStartDate,
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            @RequestParam Date speechEndDate){

        logger.info("Getting the Speeches between {} and {}", speechStartDate, speechEndDate);
        return speechMapper.speechesToSpeechDtos(speechService.findAllByDateBetween(speechStartDate, speechEndDate));
    }

    @Hidden
    @GetMapping("/byAuthor")
    public List<SpeechDto> findAllByAuthorId(@RequestParam Long authorId) {
        logger.info("Getting all Speeches of author id {}", authorId);
        return speechMapper.speechesToSpeechDtos(speechService.findAllByAuthorId(authorId));
    }

    @Hidden
    @GetMapping("/bySubject")
    public List<SpeechDto> findAllBySubject(@RequestParam String subject) {
        logger.info("Getting all Speeches with subject = {}", subject);
        return speechMapper.speechesToSpeechDtos(speechService.findAllBySubject(subject));
    }
}
