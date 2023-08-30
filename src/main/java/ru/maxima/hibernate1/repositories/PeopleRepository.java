package ru.maxima.hibernate1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.hibernate1.entity.Person;

import java.util.List;
import java.util.Optional;

/**
 * @author AramaJava 29.08.2023
 */
@Repository
public interface PeopleRepository extends JpaRepository<Person, Integer> {
    Optional<List<Person>> findPersonByNameContainsIgnoreCase(String keyword);

    Optional<Person> findPersonByEmailIgnoreCase(String email);
}
