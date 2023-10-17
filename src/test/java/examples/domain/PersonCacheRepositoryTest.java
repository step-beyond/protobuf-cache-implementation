package examples.domain;

import com.step.beyond.examples.person.domain.model.PersonDomain;
import com.step.beyond.examples.person.infrastructure.PersonCacheRepository;
import com.step.beyond.examples.person.infrastructure.mapper.PersonMapper;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatCode;

public class PersonCacheRepositoryTest {

    private final PersonMapper mapper = new PersonMapper();

    @Test
    void shouldWriteFile() {
        // GIVEN
        var objectUnderTest = new PersonCacheRepository(mapper);
        var person = new PersonDomain(32, "Karsten");

        // WHEN
        assertThatCode(() -> objectUnderTest.writePerson(person))
                .doesNotThrowAnyException();
    }

    @Test
    void shouldWriteAndReadFile() {
        // GIVEN
        var objectUnderTest = new PersonCacheRepository(mapper);
        var person = new PersonDomain(32, "Karsten");

        // WHEN
        objectUnderTest.writePerson(person);
        var result = objectUnderTest.findPerson();

        assertThat(result).isPresent()
                .get()
                .extracting(PersonDomain::age, PersonDomain::name)
                .contains(32, "Karsten");
    }
}
