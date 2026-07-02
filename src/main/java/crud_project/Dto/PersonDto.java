package crud_project.Dto;

import crud_project.entity.Person;

public record PersonDto(
        Long id,String name,int age
) {
    public static PersonDto toDto(Person person) {
        return new PersonDto(person.getId(),person.getName(),person.getAge());
    }

    public  Person toEntity() {
        return new Person(id,name,age);
    }
}
