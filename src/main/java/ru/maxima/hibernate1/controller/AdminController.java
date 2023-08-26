package ru.maxima.hibernate1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.hibernate1.dao.PersonDAO;
import ru.maxima.hibernate1.entity.Person;

/**
 * @author AramaJava 05.08.2023
 */

@Controller
@RequestMapping("/admin")
public class AdminController {

    private final PersonDAO personDAO;
    @Autowired
    public AdminController(PersonDAO personDAO) {
        this.personDAO = personDAO;
    }

    @GetMapping
    public String getForm(Model model, @ModelAttribute("person")Person person) {
        model.addAttribute("allPeople", personDAO.index());
        return "people/adminPage";
    }

    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person")Person person) {
        Person founded = personDAO.show(person.getId());
        System.out.println(founded.getName());
        System.out.println(founded.getAge());
        System.out.println(founded.getEmail());
        return "redirect:/people";
    }
}
