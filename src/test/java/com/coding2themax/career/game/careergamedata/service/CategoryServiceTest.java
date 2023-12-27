package com.coding2themax.career.game.careergamedata.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.coding2themax.career.game.careergamedata.model.Category;
import com.coding2themax.career.game.careergamedata.repository.CategoryRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@ExtendWith(MockitoExtension.class)
public class CategoryServiceTest {

  @Mock
  private CategoryRepository repository;

  @InjectMocks
  private CategoryService service = new CategoryRepositoryService();

  @Test
  void testFindByID() {

    Category cat = new Category();
    cat.setCode(1);
    cat.setDisplayLevel(0l);
    cat.setCategoryText("cat text");
    BDDMockito.when(repository.findById(1)).thenReturn(Mono.just(cat));

    StepVerifier.create(service.findByID(1)).expectNextMatches(c -> c.getCode().equals(1)).expectComplete().verify();

    BDDMockito.verify(repository).findById(1);
  }

  @Test
  void testGetAllCategories() {

    Category cat = new Category();
    cat.setCode(0);
    cat.setDisplayLevel(0l);
    cat.setCategoryText("cat text");

    Category cat1 = new Category();
    cat1.setCode(1);
    cat1.setDisplayLevel(0l);
    cat1.setCategoryText("cat text");

    BDDMockito.when(repository.findAll()).thenReturn(Flux.just(cat, cat1));

    StepVerifier.create(service.getAllCategories()).expectNextCount(2).expectComplete().verify();

    BDDMockito.verify(repository).findAll();

  }

  @Test
  void testSaveCategory() {
    Category cat = new Category();
    cat.setCode(0);
    cat.setDisplayLevel(0l);
    cat.setCategoryText("cat text");

    BDDMockito.when(repository.save(cat)).thenReturn(Mono.just(cat));

    StepVerifier.create(service.saveCategory(cat)).expectNextMatches(c -> c.getCode().equals(0)).expectComplete()
        .verify();
    BDDMockito.verify(repository).save(cat);

  }

  @Test
  void testUpdateCategory() {

    Category cat = new Category();
    cat.setCode(0);
    cat.setDisplayLevel(0l);
    cat.setCategoryText("cat text");

    BDDMockito.when(repository.findById(0)).thenReturn(Mono.just(cat));

    BDDMockito.when(repository.save(cat)).thenReturn(Mono.just(cat));

    StepVerifier.create(service.updateCategory("0", cat)).expectNextMatches(c -> c.getCode().equals(0))
        .expectComplete().verify();

    BDDMockito.verify(repository).findById(0);
    // BDDMockito.verify(repository).save(cat);

  }

  @Test
  void testNewUpdate() {

    Category cat = new Category();
    cat.setCode(0);
    cat.setDisplayLevel(0l);
    cat.setCategoryText("cat text");

    BDDMockito.when(repository.findById(0)).thenReturn(Mono.empty());

    BDDMockito.when(repository.save(cat)).thenReturn(Mono.just(cat));

    StepVerifier.create(service.updateCategory("0", cat)).expectNextMatches(c -> c.getCode().equals(0))
        .expectComplete().verify();

  }
}
