package org.khasanof.helidon;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class FakeDataServiceTest {

    @Test
    void generateReturnsRequestedCount() {
        FakeDataService service = new FakeDataService();
        List<FakePerson> people = service.generate(7);

        assertEquals(7, people.size());
        people.forEach(person -> {
            assertFalse(person.firstName().isBlank());
            assertFalse(person.email().isBlank());
        });
    }
}
