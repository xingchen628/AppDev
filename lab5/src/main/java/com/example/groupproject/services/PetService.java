package com.example.groupproject.services;
import com.example.groupproject.entities.Pet;
import com.example.groupproject.repositories.PetRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import com.example.groupproject.dto.PetSummary;

@Service

public class PetService {
  public static class NotFoundException extends RuntimeException {
    public NotFoundException(String message) {
      super(message);
    }
  }

  public static class DataConflictException extends RuntimeException {
    public DataConflictException(String message) {
      super(message);
    }
  }

  private final PetRepository petRepository;

  public PetService(PetRepository petRepository) {
    this.petRepository = petRepository;
  }

  public Pet createPet(Pet pet) {
    if (petRepository.existsById(pet.getId())) {
      throw new DataConflictException("Pet with the same ID already exists.");
    }
    return petRepository.save(pet);
  }

  public List<Pet> getAllPets() {
    return petRepository.findAll();
  }

  public Pet getPetById(Long id) {
    return petRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Pet not found."));
  }

  public void updatePet(Long id, Pet pet) {
    Pet existingPet = petRepository.findById(id)
        .orElseThrow(() -> new NotFoundException("Pet not found."));
    existingPet.setName(pet.getName());
    existingPet.setAnimalType(pet.getAnimalType());
    existingPet.setBreed(pet.getBreed());
    existingPet.setAge(pet.getAge());
    petRepository.save(existingPet);
  }

  public void deletePetById(Long id) {
    if (!petRepository.existsById(id)) {
      throw new NotFoundException("Pet not found.");
    }
    petRepository.deleteById(id);
  }

  public void deletePetsByName(String name) {
    petRepository.deleteByNameIgnoreCase(name);
  }

  public List<Pet> findPetsByAnimalType(String type) {
    return petRepository.findByAnimalTypeIgnoreCase(type);
  }

  public List<Pet> findPetsByBreed(String breed) {
    return petRepository.findByBreedOrderByAgeAsc(breed);
  }

  public List<PetSummary> getPetSummaries() {
    return petRepository.findPetSummaries();
  }

  public Double getAverageAge() {
    return petRepository.findAverageAge();
  }

  public Integer getOldestAge() {
    return petRepository.findOldestAge();
  }
}

