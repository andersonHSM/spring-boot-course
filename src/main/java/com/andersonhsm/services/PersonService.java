package com.andersonhsm.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonhsm.exceptions.ResourceNotFoundException;
import com.andersonhsm.model.Person;
import com.andersonhsm.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public Person findById(Long id) {
        logger.info("[findById] Searching one person");

        return personRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No record found."));
    }

    public List<Person> findAll() {
        return personRepository.findAll();
    }

    public Person create(Person person) {

        return personRepository.save(person);
    }

    public Person update(Person person) {

        logger.info("Updating one person!");
        Person personEntity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No record found."));

        Optional.ofNullable(person).map(i -> i.getFirstName()).ifPresent(i -> personEntity.setFirstName(i));
        Optional.ofNullable(person).map(i -> i.getLastName()).ifPresent(i -> personEntity.setLastName(i));
        Optional.ofNullable(person).map(i -> i.getAddress()).ifPresent(i -> personEntity.setAddress(i));
        Optional.ofNullable(person).map(i -> i.getGender()).ifPresent(i -> personEntity.setGender(i));

        return personRepository.save(personEntity);
    }

    public void delete(Long id) {

        Person entity = findById(id);

        personRepository.delete(entity);
    }

}
