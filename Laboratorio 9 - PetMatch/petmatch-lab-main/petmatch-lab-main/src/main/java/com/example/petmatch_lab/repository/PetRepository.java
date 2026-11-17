package com.example.petmatch_lab.repository;

import com.example.petmatch_lab.model.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<Pet, Long> {
    
}