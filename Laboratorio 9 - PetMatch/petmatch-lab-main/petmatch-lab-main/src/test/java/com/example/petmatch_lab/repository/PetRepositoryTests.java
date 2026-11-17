package com.example.petmatch_lab.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.example.petmatch_lab.model.Pet;

import jakarta.persistence.EntityManager;

@DataJpaTest
class PetRepositoryTests {

    @Autowired
    private PetRepository repository;

    @Autowired
    private EntityManager entityManager;

    @Test
    void shouldGenerateIdentifierWhenSavingNewPet() {
        Pet pet = new Pet();
        pet.setName("Kira");
        pet.setSpecies("Dog");
        pet.setAge(4);
        pet.setDescription("Very playful.");

        Pet saved = repository.saveAndFlush(pet);

        assertNotNull(saved.getId());
        assertEquals("Kira", saved.getName());
    }

    @Test
    void shouldFindPersistedPetByIdentifier() {
        Pet pet = new Pet();
        pet.setName("Luna");
        pet.setSpecies("Dog");
        pet.setAge(3);
        pet.setDescription("Enjoys walks.");

        Pet saved = repository.saveAndFlush(pet);
        Long id = saved.getId();
        entityManager.clear();

        Pet found = repository.findById(id).orElseThrow();

        assertEquals("Luna", found.getName());
        assertEquals(3, found.getAge());
    }

    @Test
    void shouldPersistChangesToExistingPet() {
        Pet pet = new Pet();
        pet.setName("Milo");
        pet.setSpecies("Cat");
        pet.setAge(2);
        pet.setDescription(null);

        Pet saved = repository.saveAndFlush(pet);
        Long id = saved.getId();

        saved.setAge(5);
        repository.saveAndFlush(saved);
        entityManager.clear();

        Pet found = repository.findById(id).orElseThrow();

        assertEquals(5, found.getAge());
    }

    @Test
    void shouldDeletePersistedPet() {
        Pet pet = new Pet();
        pet.setName("Nube");
        pet.setSpecies("Rabbit");
        pet.setAge(1);
        pet.setDescription(null);

        Pet saved = repository.saveAndFlush(pet);
        Long id = saved.getId();

        repository.delete(saved);
        repository.flush();
        entityManager.clear();

        assertFalse(repository.findById(id).isPresent());
    }

    @Test
    void shouldReturnEmptyWhenIdentifierDoesNotExist() {
        assertTrue(repository.findById(Long.MAX_VALUE).isEmpty());
    }
}