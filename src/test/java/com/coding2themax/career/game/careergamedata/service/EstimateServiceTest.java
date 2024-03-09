package com.coding2themax.career.game.careergamedata.service;

import static org.mockito.Mockito.atLeast;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coding2themax.career.game.careergamedata.model.Estimate;
import com.coding2themax.career.game.careergamedata.repository.EstimateRespository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class EstimateServiceTest {

  @Mock
  private EstimateRespository repository;

  @InjectMocks
  private EstimateService service = new EstimateRepositoryService();

  @Test
  void testFindByID() {
    Estimate e1 = new Estimate();
    e1.setCode(1);
    e1.setDisplayLevel(1l);
    e1.setEstimateText("null");
    BDDMockito.when(repository.findById(1)).thenReturn(Mono.just(e1));

    StepVerifier.create(service.findByID(1)).expectNextMatches(e -> e.getCode().equals(1)).verifyComplete();
    BDDMockito.verify(repository).findById(1);
  }

  @Test
  void testGetAllEstimates() {
    Estimate e1 = new Estimate();
    e1.setCode(1);
    e1.setDisplayLevel(1l);
    e1.setEstimateText("null");

    Estimate e2 = new Estimate();
    e2.setCode(2);
    e2.setDisplayLevel(1l);
    e2.setEstimateText("null");

    BDDMockito.when(repository.findAll()).thenReturn(Flux.just(e1, e2));
    StepVerifier.create(service.getAllEstimates()).expectNextCount(2).expectComplete().verify();
    BDDMockito.verify(repository).findAll();

  }

  @Test
  void testSaveEstimate() {
    Estimate e1 = new Estimate();
    e1.setCode(1);
    e1.setDisplayLevel(1l);
    e1.setEstimateText("text");
    BDDMockito.when(repository.save(e1)).thenReturn(Mono.just(e1));
    BDDMockito.when(repository.findById(1)).thenReturn(Mono.empty());

    StepVerifier.create(service.saveEstimate(e1)).expectNextMatches(c -> c.getCode().equals(1)).expectComplete()
        .verify();
    BDDMockito.verify(repository).save(e1);

  }

  @Test
  void testUpdateEstimate() {

    Estimate e1 = new Estimate();
    e1.setCode(1);
    e1.setDisplayLevel(1l);
    e1.setEstimateText("null");

    BDDMockito.when(repository.findById(1)).thenReturn(Mono.just(e1));

    BDDMockito.when(repository.save(e1)).thenReturn(Mono.just(e1));
    StepVerifier.create(service.updateEstimate("1", e1)).expectNextMatches(c -> c.getCode().equals(1))
        .expectComplete()
        .verify();
    BDDMockito.verify(repository, atLeast(1)).save(e1);
    BDDMockito.verify(repository).findById(1);

  }
}
