package com.andersonhsm.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.andersonhsm.model.entities.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
