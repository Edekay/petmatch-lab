package com.example.petmatch_lab.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private String name;
    private String species;
    private int age;
    private String description;

    // Constructor vacío (requerido por JPA y pruebas)
    public Pet() {}

    // Constructor con parámetros (requerido por PetService)
    public Pet(String name, String species, int age, String description) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.description = description;
    }

    // Método updateDetails (requerido por PetService)
    public void updateDetails(String name, String species, int age, String description) {
        this.name = name;
        this.species = species;
        this.age = age;
        this.description = description;
    }

    // Getters
    public long getId() { return id; }
    public String getName() { return name; }
    public String getSpecies() { return species; }
    public int getAge() { return age; }
    public String getDescription() { return description; }

    // Setters
    public void setId(long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpecies(String species) { this.species = species; }
    public void setAge(int age) { this.age = age; }
    public void setDescription(String description) { this.description = description; }
}