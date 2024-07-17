package org.khasanof;

import org.instancio.Instancio;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 7/17/2024 4:56 PM
 */
public class InstancioTest {

    /**
     *
     */
    @Test
    void firstTestPopulatedPersonObject() {
        Person person = Instancio.create(Person.class);

        assertNotNull(person);
        assertNotNull(person.getFirstname());
        assertNotNull(person.getLastname());
        assertNotNull(person.getAge());
    }

    /**
     *
     */
    @Test
    void secondTestPopulatedPersonObjectsOfList() {
        List<Person> persons = Instancio.ofList(Person.class)
                .size(10)
                .create();

        assertNotNull(persons);
        assertFalse(persons.isEmpty());
        assertEquals(persons.size(), 10);

        persons.forEach(person -> {
            assertNotNull(person);
            assertNotNull(person.getFirstname());
            assertNotNull(person.getLastname());
            assertNotNull(person.getAge());
        });
    }
}
