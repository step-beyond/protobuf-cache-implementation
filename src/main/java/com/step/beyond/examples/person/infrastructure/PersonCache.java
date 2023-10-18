package com.step.beyond.examples.person.infrastructure;

import com.step.beyond.examples.cache.Cache;
import com.step.beyond.examples.cache.ProtoFileStorage;
import com.step.beyond.examples.person.domain.model.PersonDomain;
import com.step.beyond.examples.person.infrastructure.mapper.PersonMapper;
import com.step.beyond.examples.person.infrastructure.repository.model.gen.Person;
import java.util.Optional;

public class PersonCache {

    private static final String KEY_PATTERN = "%s.bin";

    private final Cache<Person> cache = ProtoFileStorage.withParser(Person.parser());
    private final PersonMapper mapper;

    public PersonCache(PersonMapper mapper) {
        this.mapper = mapper;
    }

    public boolean putPerson(PersonDomain person) {
        Person personProto = mapper.map(person);
        return cache.put(String.format(KEY_PATTERN, "Person"), personProto);
    }

    public Optional<PersonDomain> findPerson() {
        return Optional.ofNullable(cache.get(String.format(KEY_PATTERN, "Person")))
                .map(mapper::map);
    }
}
