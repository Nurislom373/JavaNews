package org.khasanof.entity.auth;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthUser {
    private String username;
    private String language;
    private String firstName;
    private String lastName;
    private String chatId;
    private String state;
}
