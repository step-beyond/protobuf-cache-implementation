package com.step.beyond.examples.person.infrastructure;

import com.step.beyond.examples.cache.Cache;
import com.step.beyond.examples.person.domain.model.PersonDomain;
import com.step.beyond.examples.person.infrastructure.mapper.PersonMapper;
import com.step.beyond.examples.person.infrastructure.repository.model.gen.Person;
import java.util.Optional;

public class PersonCacheRepository {

    private static String KEY_PATTERN = "%s.bin";

    private final Cache<Person> cache = Cache.withParser(Person.parser());
    private final PersonMapper mapper;

    public PersonCacheRepository(PersonMapper mapper) {
        this.mapper = mapper;
    }

    public void writePerson(PersonDomain person) {
        Person personProto = mapper.map(person);
        cache.write(String.format(KEY_PATTERN, "Person"), personProto);
    }

    public Optional<PersonDomain> findPerson() {
        return Optional.ofNullable(cache.read(String.format(KEY_PATTERN, "Person")))
                .map(mapper::map);
    }
}
