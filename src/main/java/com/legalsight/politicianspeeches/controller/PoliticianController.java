package com.legalsight.politicianspeeches.controller;

import com.legalsight.politicianspeeches.dto.PoliticianDto;
import com.legalsight.politicianspeeches.dto.PoliticianMapper;
import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.service.PoliticianService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import java.util.List;

@RestController
@RequestMapping("${apiPrefix}/politicians")
public class PoliticianController {
    private static final Logger logger = LoggerFactory.getLogger(PoliticianController.class);

    private final PoliticianService politicianService;
    private final PoliticianMapper politicianMapper;

    public PoliticianController(PoliticianService politicianService, PoliticianMapper politicianMapper) {
        this.politicianService = politicianService;
        this.politicianMapper = politicianMapper;
    }

    @PostMapping
    public PoliticianDto create(@Valid @RequestBody PoliticianDto politicianDto){
        return politicianMapper.politicianToPoliticianDto(politicianService.create(politicianMapper.politicianDtoToPolitician(politicianDto)));
    }

    @GetMapping
    public List<PoliticianDto> findAll(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {

        return politicianMapper.politiciansToPoliticianDtos(politicianService.findAll(name,email));

    }

    @GetMapping("/{id}")
    PoliticianDto findById(@PathVariable Long id) {
        try {
            logger.info("Looking  for Politician {}", id);
            return politicianMapper.politicianToPoliticianDto(politicianService.findById(id));
        } catch (PSRecourseNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Politician Not Found", e);
        }
    }


    @PutMapping("/{id}")
    public PoliticianDto update(@PathVariable Long id, @RequestBody PoliticianDto speechDto){

        logger.info("Updating the Politician {}", id);
        try {
            return politicianMapper.politicianToPoliticianDto(politicianService.update(id,
                    politicianMapper.politicianDtoToPolitician(speechDto)));
        } catch (PSRecourseNotFoundException e) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Politician Not Found", e);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        logger.info("Removing the Politician {}", id);
        politicianService.delete(id);
    }

}
