package com.example.assignment3.Repository;

import com.example.assignment3.Entities.Gym;

import org.springframework.data.repository.CrudRepository;

public interface GymRepository extends CrudRepository<Gym, Long> {
    public Gym findById(Long id);
    public Gym findByName(String name);
}