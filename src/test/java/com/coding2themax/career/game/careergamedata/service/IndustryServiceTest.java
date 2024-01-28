package com.coding2themax.career.game.careergamedata.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coding2themax.career.game.careergamedata.model.Industry;
import com.coding2themax.career.game.careergamedata.repository.IndustryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class IndustryServiceTest {

  @Mock
  private IndustryRepository repository;

  @InjectMocks
  private IndustryService service = new IndustryRepositoryService();

  @Test
  void testFindAll() {

    Industry industry1 = new Industry();
    Industry industry2 = new Industry();

    BDDMockito.when(repository.findAll()).thenReturn(Flux.just(industry1, industry2));

    StepVerifier.create(service.getAllIndustries()).expectNextCount(2).expectComplete().verify();

  }

  @Test
  void testSave() {
    Industry industry1 = new Industry();
    // industry1.setCode(0);
    industry1.setDisplayLevel(0l);
    industry1.setIndustryText("cat text");
    BDDMockito.when(repository.save(industry1)).thenReturn(Mono.just(industry1));
    industry1.setNewIndustry(true);
    Assertions.assertTrue(industry1.isNew());
    StepVerifier.create(service.saveIndustry(industry1)).expectNextMatches(c -> c.getIndustryText().equals("cat text"))
        .expectComplete()
        .verify();
    BDDMockito.verify(repository).save(industry1);

  }

  @Test
  void testUpdateIndustry() {
    Industry industry1 = new Industry();
    industry1.setCode(0);
    industry1.setDisplayLevel(0l);
    industry1.setIndustryText("cat text");
    industry1.setNewIndustry(false);

    BDDMockito.when(repository.findById(0)).thenReturn(Mono.just(industry1));
    BDDMockito.when(repository.save(industry1)).thenReturn(Mono.just(industry1));

    StepVerifier.create(service.updateIndustry("0", industry1)).expectNextMatches(c -> c.getCode().equals(0))
        .expectComplete().verify();

    BDDMockito.verify(repository).findById(0);
    // BDDMockito.verify(repository).save(industry1);
  }
}
