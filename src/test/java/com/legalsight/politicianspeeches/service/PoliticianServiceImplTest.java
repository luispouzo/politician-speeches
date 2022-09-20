package com.legalsight.politicianspeeches.service;

import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.model.Politician;
import com.legalsight.politicianspeeches.repository.PoliticianRepository;
import com.legalsight.politicianspeeches.util.PSTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class PoliticianServiceImplTest {

    @Mock
    private PoliticianRepository politicianRepository;

    @InjectMocks
    private PoliticianServiceImpl politicianService;

    @Test
    void when_findAllPoliticians_Expect_5() {
        var parameterPolitician = PSTestUtils.newTestPoliticianList5();

        Mockito.when(politicianRepository.searchAll(Mockito.anyString(), Mockito.anyString()))
                .thenReturn(parameterPolitician);

        final List<Politician> politicianList = politicianService.findAll("Name", "email@email.com");
        final int numOPoliticians = politicianList.size();

        Executable numOfPoliticiansChecker = () -> Assertions.assertEquals(5, numOPoliticians,
                String.format("There should be 5 different politicians but there are {} instead", numOPoliticians));

        final String emailsSuffix = "__fakeemail@fake.com";
        Executable politicianEmailChecker = () -> Assertions.assertTrue(politicianList.stream()
                        .map(Politician::getEmail)
                        .anyMatch(e -> e.endsWith(emailsSuffix)),
                String.format("There should exists at least one politician with email ending in {}", emailsSuffix));

        Assertions.assertAll("politicians", numOfPoliticiansChecker, politicianEmailChecker);
    }

    @Test
    void when_updateNonExistentPolitician_Expect_Exception() {
        var parameterPolitician = PSTestUtils.newTestPoliticianWithId99();

        Mockito.when(politicianRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        Assertions.assertThrows(PSRecourseNotFoundException.class,
                () -> politicianService.update(99L, parameterPolitician));
    }

}