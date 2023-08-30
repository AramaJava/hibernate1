package ru.maxima.hibernate1.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.maxima.hibernate1.entity.Item;
import ru.maxima.hibernate1.entity.Person;
import ru.maxima.hibernate1.repositories.ItemsRepository;

import java.util.List;

/**
 * @author AramaJava 30.08.2023
 */
@Service
@Transactional(readOnly = true)
public class ItemService {

    private final ItemsRepository itemsRepository;
    @Autowired
    public ItemService(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> findByItemName(String itemName) {
        return itemsRepository.findByItemName(itemName);
    }

    public List<Item> findByOwner(Person owner) {
        return itemsRepository.findByOwner(owner);
    }


}
