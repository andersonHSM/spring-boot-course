package com.andersonhsm.services;

import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.andersonhsm.data.dto.v1.PersonDTO;
import com.andersonhsm.exceptions.ResourceNotFoundException;
import com.andersonhsm.mapper.Mapper;
import com.andersonhsm.model.entities.Person;
import com.andersonhsm.repositories.PersonRepository;

@Service
public class PersonService {

    private Logger logger = Logger.getLogger(PersonService.class.getName());

    @Autowired
    PersonRepository personRepository;

    public PersonDTO findById(Long id) {
        logger.info("[findById] Searching one person");

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No resource found"));

        return Mapper.parseObject(entity, PersonDTO.class);
    }

    public List<PersonDTO> findAll() {
        return Mapper.parseListObjects(personRepository.findAll(), PersonDTO.class);
    }

    public PersonDTO create(PersonDTO person) {
        Person entityToCreate = Mapper.parseObject(person, Person.class);

        Person entityCreated = personRepository.save(entityToCreate);

        return Mapper.parseObject(entityCreated, PersonDTO.class);
    }

    public PersonDTO update(PersonDTO person) {

        logger.info("Updating one person!");
        Person personEntity = personRepository.findById(person.getId())
                .orElseThrow(() -> new ResourceNotFoundException("No resource found"));

        Optional.ofNullable(person).map(i -> i.getFirstName()).ifPresent(i -> personEntity.setFirstName(i));
        Optional.ofNullable(person).map(i -> i.getLastName()).ifPresent(i -> personEntity.setLastName(i));
        Optional.ofNullable(person).map(i -> i.getAddress()).ifPresent(i -> personEntity.setAddress(i));
        Optional.ofNullable(person).map(i -> i.getGender()).ifPresent(i -> personEntity.setGender(i));

        Person entityUpdated = personRepository.save(personEntity);

        return Mapper.parseObject(entityUpdated, PersonDTO.class);
    }

    public void delete(Long id) {

        Person entity = personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No resource found"));

        personRepository.delete(entity);
    }

}
