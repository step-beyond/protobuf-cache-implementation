package com.step.beyond.examples.person.infrastructure.mapper;

import com.step.beyond.examples.person.domain.model.PersonDomain;
import com.step.beyond.examples.person.infrastructure.repository.model.gen.Person;

public class PersonMapper {
    public Person map(PersonDomain person) {
        return Person.newBuilder()
                .setAge(person.age())
                .setName(person.name())
                .setId(123)
                .build();
    }

    public PersonDomain map(Person person) {
        return new PersonDomain(person.getAge(), person.getName());
    }
}
