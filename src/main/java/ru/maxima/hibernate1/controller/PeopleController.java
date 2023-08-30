package ru.maxima.hibernate1.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.maxima.hibernate1.entity.Person;
import ru.maxima.hibernate1.services.PeopleService;
import ru.maxima.hibernate1.util.PersonValidator;

import java.util.List;


/**
 * @author AramaJava 26.07.2023
 */

@Controller
@RequestMapping("/people")
public class PeopleController {

    @Autowired
    private final PeopleService peopleService;

    @Autowired
    private final PersonValidator personValidator;


    @Autowired
    public PeopleController(PeopleService peopleService, PersonValidator personValidator) {
        this.peopleService = peopleService;
        this.personValidator = personValidator;
    }

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("people", peopleService.findAll());
        return "people/index";
    }

    @GetMapping("/{id}")
    private String show(@PathVariable("id") int id, Model model) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/show";
    }

    @GetMapping("/new")
    public String newPerson(@ModelAttribute("person") Person person) {
        return "/people/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) return "people/new";

        peopleService.save(person);
        return "redirect:/people";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("person", peopleService.findOne(id));
        return "people/edit";
    }

    @PostMapping("/{id}")
    private String update(@ModelAttribute("person") @Valid Person person, BindingResult bindingResult,
                          @PathVariable("id") int id) {

        personValidator.validate(person, bindingResult);

        if (bindingResult.hasErrors()) {
            return "people/edit";
        }

        peopleService.update(id, person);
        return "redirect:/people";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        peopleService.delete(id);
        return "redirect:/people";
    }


    @GetMapping("/search-by-name")
    public String search(Model model, @RequestParam("keyword") String keyword) {
        List<Person> searchResult;
        if (keyword != null && !keyword.isEmpty()) {
            searchResult = peopleService.findAnyByName(keyword);
        } else {
            searchResult = null;
        }
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("keyword", keyword);
        return "people/searchName";
    }

    @GetMapping("/search-by-id")
    public String search(Model model, @RequestParam("id") int id) {
        Person searchResult;
        if (id > 0) {
            searchResult = peopleService.findOne(id);
        } else {
            searchResult = null;
        }
        model.addAttribute("searchResult", searchResult);
        model.addAttribute("id", id);
        return "people/searchId";
    }

}
