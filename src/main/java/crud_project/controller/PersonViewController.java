package crud_project.controller;

import crud_project.Dto.PersonDto;
import crud_project.service.PersonService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/persons")
public class PersonViewController {

    private final PersonService personService;

    public PersonViewController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String getAllPersons(Model model) {
        List<PersonDto> personDto = personService.getAll();
        model.addAttribute("persons", personDto);
        return "person/list";

    }

    @GetMapping("/{id}")
    public String getPersonById(@PathVariable Long id, Model model) {
        PersonDto personDto = personService.getById(id);
        model.addAttribute("person", personDto);
        return "person/get";
    }

    @PostMapping
    public String createPerson(@ModelAttribute PersonDto personDto) {
        personService.create(personDto);
        return "redirect:/persons";
    }

    @GetMapping("/new")
    public String showCreatePersonForm(Model model) {
        model.addAttribute("person", new PersonDto(null, "", 0));
        return "person/create";
    }

    @DeleteMapping("/{id}")
    public String deletePerson(@PathVariable Long id) {
        personService.deleteById(id);
        return "redirect:/persons";
    }
    @PutMapping("/{id}")
    public String updatePerson(@PathVariable Long id, @ModelAttribute PersonDto personDto) {
        PersonDto dto = new PersonDto(personDto.id(), personDto.name(), personDto.age());
        personService.updateById(dto,id);
        return "redirect:/persons";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        PersonDto person = personService.getById(id);
        model.addAttribute("person", person);
        return "person/edit";
        }
}
