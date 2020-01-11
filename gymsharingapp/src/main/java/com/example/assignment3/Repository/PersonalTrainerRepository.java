package com.example.assignment3.Repository;

import com.example.assignment3.Entities.PersonalTrainer;

import org.springframework.data.repository.CrudRepository;

public interface PersonalTrainerRepository extends CrudRepository<PersonalTrainer, Long> {
    public PersonalTrainer findById(Long id);
    public PersonalTrainer findByName(String name);
    public PersonalTrainer findByUsername(String username);
}