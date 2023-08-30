package ru.maxima.hibernate1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maxima.hibernate1.entity.Item;
import ru.maxima.hibernate1.entity.Person;

import java.util.List;

/**
 * @author AramaJava 30.08.2023
 */
@Repository
public interface ItemsRepository extends JpaRepository <Item, Integer>{
    List<Item> findByItemName(String itemName);

    //person.getItems()
    List<Item> findByOwner(Person person);

    List<Item> findByItemNameStartingWith(String startingWith);

    List<Item> findByItemNameEndingWith(String endingWith);

    List<Item> findByOwnerOrderById(Person person);

    List<Item> findByItemNameOrderByItemName(String itemName);


}
