package com.legalsight.politicianspeeches.service;

import com.legalsight.politicianspeeches.dto.PoliticianMapper;
import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.model.Politician;
import com.legalsight.politicianspeeches.repository.PoliticianRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PoliticianServiceImpl implements PoliticianService{

    private static final Logger logger = LoggerFactory.getLogger(PoliticianServiceImpl.class);
    private final PoliticianRepository politicianRepository;
    private final PoliticianMapper politicianMapper;

    public PoliticianServiceImpl(PoliticianRepository politicianRepository, PoliticianMapper politicianMapper) {
        this.politicianRepository = politicianRepository;
        this.politicianMapper = politicianMapper;
    }

    @Override
    public Politician create(Politician politician) {
        logger.info("Creating the politician {}", politician);
        return politicianRepository.save(politician);
    }

    @Override
    public List<Politician> findAll(String name, String email) {
        logger.info("Retrieving all Politicians");
        return politicianRepository.searchAll(name, email);
    }

    @Override
    public Politician findById(Long id) throws PSRecourseNotFoundException {
        logger.info("Finding the Politician {}", id);
        return politicianRepository.findById(id)
                .orElseThrow(() -> new PSRecourseNotFoundException(String.format("Politician %d not founded", id)));
    }

    @Override
    public Politician update(Long id, Politician politician) throws PSRecourseNotFoundException {
        logger.info("Updating the Politician {}", politician.getId());
        var updatablePolitician = findById(id);
        politicianMapper.politicianToPolitician(politician, updatablePolitician);
        return politicianRepository.save(updatablePolitician);
    }

    @Override
    public void delete(Long id) {
        logger.info("Deleting the Politician {}", id);
        politicianRepository.deleteById(id);
    }
}
