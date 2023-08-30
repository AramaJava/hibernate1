package ru.maxima.hibernate1.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.hibernate1.entity.Person;
import ru.maxima.hibernate1.repositories.PeopleRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 29.08.2023
 */
@Service
@Transactional(readOnly = true)
public class PeopleService {

    @Autowired
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return  peopleRepository.findAll();
    }

    public Person findOne(int id) {
        Optional<Person>  foundPerson = peopleRepository.findById(id);
        return foundPerson.orElse(null);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void update(int id, Person updatedPerson) {
        updatedPerson.setId(id);
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void delete(int id) {
        peopleRepository.deleteById(id);
    }

    @Transactional
    public List<Person> findAnyByName(String keyword) {
        Optional<List<Person>> resultSearch = peopleRepository.findPersonByNameContaining(keyword);
        return resultSearch.orElse(null);
    }

    @Transactional
    public Person findByEmail(String email) {
        Optional<Person> foundPerson = peopleRepository.findPersonByEmail(email);
        return foundPerson.orElse(null);
    }

}
