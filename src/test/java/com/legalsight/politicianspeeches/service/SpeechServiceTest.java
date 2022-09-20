package com.legalsight.politicianspeeches.service;

import com.legalsight.politicianspeeches.dto.SpeechMapper;
import com.legalsight.politicianspeeches.exception.PSRecourseNotFoundException;
import com.legalsight.politicianspeeches.model.Speech;
import com.legalsight.politicianspeeches.repository.SpeechRepository;
import com.legalsight.politicianspeeches.util.PSTestUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class SpeechServiceTest {

    @Mock
    private SpeechRepository speechRepository;
    @Mock
    private SpeechMapper speechMapper;

    @InjectMocks
    private SpeechServiceImpl speechService;

    @Test
    public void when_updateSpeech_Expect_speechUpdated() throws PSRecourseNotFoundException {
        var speechMockEntity = PSTestUtils.newTestSpeech();
        Mockito.when(speechRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(speechMockEntity));
        Mockito.when(speechMapper.speechToSpeech(Mockito.any(Speech.class),Mockito.any(Speech.class)))
                .thenReturn(speechMockEntity);
        Mockito.when(speechRepository.save(Mockito.any(Speech.class))).thenReturn(speechMockEntity);
        final Speech updated = speechService.update(99l, PSTestUtils.newTestSpeech());

        Assertions.assertEquals(speechMockEntity, updated);
    }
}