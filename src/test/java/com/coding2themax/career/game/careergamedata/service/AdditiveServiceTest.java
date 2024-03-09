package com.coding2themax.career.game.careergamedata.service;

import static org.mockito.Mockito.atLeast;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coding2themax.career.game.careergamedata.model.Additive;
import com.coding2themax.career.game.careergamedata.repository.AdditiveRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class AdditiveServiceTest {

  @Mock
  private AdditiveRepository repository;

  @InjectMocks
  private AdditiveService service = new AdditiveRepositoryService();

  @Test
  void testFindByID() {

    Additive additive = new Additive();
    additive.setCode(1);
    additive.setDisplayLevel(0l);
    additive.setAdditiveText("additive text");
    BDDMockito.when(repository.findById(1)).thenReturn(Mono.just(additive));

    StepVerifier.create(service.findByID(1)).expectNextMatches(a -> a.getCode().equals(1)).verifyComplete();

    BDDMockito.verify(repository).findById(1);

  }

  @Test
  void testGetAll() {

    Additive additive1 = new Additive();
    additive1.setCode(1);
    additive1.setDisplayLevel(0l);
    additive1.setAdditiveText("additive text");

    Additive additive2 = new Additive();
    additive2.setCode(2);
    additive2.setDisplayLevel(0l);
    additive2.setAdditiveText("additive text 2");

    BDDMockito.when(repository.findAll()).thenReturn(Flux.just(additive1, additive2));

    StepVerifier.create(service.getAll()).expectNextCount(2).expectComplete().verify();
    BDDMockito.verify(repository).findAll();

  }

  @Test
  void testSaveAdditive() {
    Additive additive1 = new Additive();
    additive1.setCode(1);
    additive1.setDisplayLevel(0l);
    additive1.setAdditiveText("additive text");

    BDDMockito.when(repository.save(additive1)).thenReturn(Mono.just(additive1));
    BDDMockito.when(repository.findById(1)).thenReturn(Mono.empty());

    StepVerifier.create(service.saveAdditive(additive1)).expectNextMatches(c -> c.isNew()).expectComplete()
        .verify();
    BDDMockito.verify(repository).save(additive1);
  }

  @Test
  void testUpdateAdditive() {
    Additive additive1 = new Additive();

    additive1.setNewAdditive(false);
    Assertions.assertTrue(additive1.isNew());
    additive1.setNewAdditive(true);
    Assertions.assertTrue(additive1.isNew());

    additive1.setCode(0);
    additive1.setDisplayLevel(0l);
    additive1.setAdditiveText("additive text");

    BDDMockito.when(repository.save(additive1)).thenReturn(Mono.just(additive1));
    // additive1.setAsNew(true)
    additive1.setNewAdditive(false);
    BDDMockito.when(repository.findById(0)).thenReturn(Mono.just(additive1));
    BDDMockito.when(repository.save(additive1)).thenReturn(Mono.just(additive1));

    StepVerifier.create(service.updateAdditive("0", additive1)).expectNextMatches(c -> c.getCode().equals(0))
        .expectComplete()
        .verify();
    BDDMockito.verify(repository, atLeast(1)).save(additive1);
    BDDMockito.verify(repository).findById(0);
  }
}
