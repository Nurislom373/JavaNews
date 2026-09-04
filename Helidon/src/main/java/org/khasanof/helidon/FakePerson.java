package org.khasanof.helidon;

public record FakePerson(
        String id,
        String firstName,
        String lastName,
        String email,
        String phone,
        String address,
        String city,
        String country,
        String company,
        String jobTitle
) {
}
