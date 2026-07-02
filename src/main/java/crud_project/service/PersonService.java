package crud_project.service;

import crud_project.Dto.PersonDto;
import crud_project.entity.Person;
import crud_project.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PersonService {
    private final PersonRepository repo;
    private static final Logger log = LoggerFactory.getLogger(PersonService.class);


    public PersonService(PersonRepository personRepository) {
        this.repo = personRepository;
    }

    public void create(PersonDto dto) {
        Person person = dto.toEntity();
        repo.save(person);

    }

    public void updateById(PersonDto dto,Long id) {
       Person person = repo.findById(id).orElseThrow(EntityNotFoundException::new);
       person.setAge(dto.age());
       person.setName(dto.name());
       repo.save(person);
    }

    public void deleteById(Long id) {
        repo.deleteById(id);
    }

    public PersonDto getById(Long id) {
        return repo.findById(id)
                .map(PersonDto::toDto)
                .orElseThrow(()-> new EntityNotFoundException("Person not found"));
    }

    public List<PersonDto> getAll() {
        return repo.findAll().stream()
                .map(PersonDto::toDto)
                .toList();

        }
}
