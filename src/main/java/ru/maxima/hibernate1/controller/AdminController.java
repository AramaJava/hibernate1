package ru.maxima.hibernate1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.maxima.hibernate1.entity.Person;
import ru.maxima.hibernate1.services.PeopleService;



@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private final PeopleService peopleService;

    public AdminController(PeopleService peopleService) {
        this.peopleService = peopleService;
    }

    @GetMapping
    public String getForm(Model model, @ModelAttribute("person")Person person) {
        model.addAttribute("allPeople", peopleService.findAll());
        return "people/adminPage";
    }

    @PostMapping("/add")
    public String makeAdmin(@ModelAttribute("person")Person person) {
        Person founded = peopleService.findOne(person.getId());
        System.out.println(founded.getName());
        System.out.println(founded.getAge());
        System.out.println(founded.getEmail());
        return "redirect:/people";
    }
}
