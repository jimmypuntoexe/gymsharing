package com.example.assignment3.Repository;

import com.example.assignment3.Entities.User;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    public User findById(Long id);
}