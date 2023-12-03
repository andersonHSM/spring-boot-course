package com.andersonhsm.services;

import com.andersonhsm.model.Person;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;

@Service
public class PersonService {

    private final AtomicLong counter = new AtomicLong();
    private Logger logger = Logger.getLogger(PersonService.class.getName());

    public Person findById(String id) {
        logger.info("[findById] Searching one person");

        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Anderson");
        person.setLastName("Menezes");
        person.setAddress("Propriá - Sergipe - Brasil");
        person.setGender("Male");

        return person;
    }

    public List<Person> findAll() {
        List<Person> people = new ArrayList<>();

        for (int i = 0; i < 10; ++i) {
            Person person = mockPerson(i);
            people.add(person);
        }

        return people;
    }

    private Person mockPerson(int i) {
        Person person = new Person();
        person.setId(counter.incrementAndGet());
        person.setFirstName("Person name: " + i);
        person.setLastName("Last name: " + i);
        person.setAddress("Propriá - Sergipe - Brasil");
        person.setGender("Male");

        return person;
    }

}
