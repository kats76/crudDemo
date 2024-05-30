package com.demoCrud.demo.controller;

import com.demoCrud.demo.model.Person;
import com.demoCrud.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class PersonController {

    @Autowired
    private PersonRepository personRepository;

    @GetMapping
    public String listPersons(Model model) {
        List<Person> persons = personRepository.findAll();
        model.addAttribute("persons", persons);
        model.addAttribute("person", new Person());
        return "index";
    }

    @PostMapping("/save")
    public String savePerson(@ModelAttribute("person") Person person) {
        personRepository.save(person);
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Person person = personRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        List<Person> persons = personRepository.findAll();
        model.addAttribute("person", person);
        model.addAttribute("persons", persons);
        return "index";
    }

    @PostMapping("/update/{id}")
    public String updatePerson(@PathVariable("id") Long id, @ModelAttribute("person") Person person) {
        person.setId(id);
        personRepository.save(person);
        return "redirect:/";
    }

    @GetMapping("/delete/{id}")
    public String deletePerson(@PathVariable("id") Long id) {
        Person person = personRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid person Id:" + id));
        personRepository.delete(person);
        return "redirect:/";
    }
}