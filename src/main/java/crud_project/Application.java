package crud_project;

import crud_project.entity.Person;
import crud_project.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application implements CommandLineRunner {
	@Autowired
	private PersonRepository personRepository;

	@Override
	public void run(String... args) throws Exception {
		personRepository.findAll().forEach(System.out::println);

	}

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
}
