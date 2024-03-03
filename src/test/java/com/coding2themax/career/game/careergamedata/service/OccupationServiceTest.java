package com.coding2themax.career.game.careergamedata.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coding2themax.career.game.careergamedata.model.Occupation;
import com.coding2themax.career.game.careergamedata.repository.OccupationRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class OccupationServiceTest {

  @Mock
  private OccupationRepository repositoryService;

  @InjectMocks
  private OccupationService occupationService = new OccpationRepositoryService();

  @Test
  void testGetAllOccuations() {

    Occupation occupation1 = new Occupation();
    Occupation occupation2 = new Occupation();

    BDDMockito.when(repositoryService.findAll()).thenReturn(Flux.just(occupation1, occupation2));
    StepVerifier.create(occupationService.getAllOccuations()).expectNextCount(2).expectComplete().verify();

  }

  @Test
  void testSaveOccupation() {
    Occupation occupation1 = new Occupation();
    occupation1.setDisplayLevel(0l);
    occupation1.setOccupationText("occupation1 text");
    BDDMockito.when(repositoryService.save(occupation1)).thenReturn(Mono.just(occupation1));
    occupation1.setNewOccupation(true);
    Assertions.assertTrue(occupation1.isNew());
    StepVerifier.create(occupationService.saveOccupation(occupation1))
        .expectNextMatches(c -> c.getOccupationText().equals("occupation1 text"))
        .expectComplete()
        .verify();
    BDDMockito.verify(repositoryService).save(occupation1);

  }

  @Test
  void testUpdateIndustry() {
    Occupation occupation1 = new Occupation();
    occupation1.setCode(0);
    occupation1.setDisplayLevel(0l);
    occupation1.setOccupationText("occupation1 text");
    Assertions.assertEquals(0, occupation1.getId());
    BDDMockito.when(repositoryService.save(occupation1)).thenReturn(Mono.just(occupation1));
    occupation1.setNewOccupation(false);
    BDDMockito.when(repositoryService.findById(0)).thenReturn(Mono.just(occupation1));
    BDDMockito.when(repositoryService.save(occupation1)).thenReturn(Mono.just(occupation1));

    StepVerifier.create(occupationService.updateOccupation("0", occupation1))
        .expectNextMatches(c -> c.getCode().equals(0))
        .expectComplete().verify();

    BDDMockito.verify(repositoryService).findById(0);
  }
}
