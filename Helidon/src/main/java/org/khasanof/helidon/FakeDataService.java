package org.khasanof.helidon;

import net.datafaker.Faker;

import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.stream.IntStream;

public class FakeDataService {

    private final Faker faker = new Faker(Locale.ENGLISH);

    public List<FakePerson> generate(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> new FakePerson(
                        UUID.randomUUID().toString(),
                        faker.name().firstName(),
                        faker.name().lastName(),
                        faker.internet().emailAddress(),
                        faker.phoneNumber().phoneNumber(),
                        faker.address().streetAddress(),
                        faker.address().city(),
                        faker.address().country(),
                        faker.company().name(),
                        faker.job().title()
                ))
                .toList();
    }
}
