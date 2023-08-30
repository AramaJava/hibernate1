package ru.maxima.hibernate1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

/**
 * @author AramaJava 30.08.2023
 */
@Entity
@Table(name="item")
public class Item {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Поле не должно быть пустым")
    @Column(name="item_name")
    private String itemName;

    @ManyToOne
    @JoinColumn(name = "person_id", referencedColumnName = "id")
    private Person owner;

    public Item() {
    }

    public Item(String itemName, Person owner) {
        this.itemName = itemName;
        this.owner = owner;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", itemName='" + itemName + '\'' +
                ", owner=" + owner +
                '}';
    }
}
