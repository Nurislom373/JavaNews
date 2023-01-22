package org.khasanof.introduction.entity;

import jakarta.persistence.*;
import org.khasanof.introduction.enums.LanguageEnums;
import org.khasanof.introduction.enums.StatusEnum;

/**
 * Author: Nurislom
 * <br/>
 * Date: 1/22/2023
 * <br/>
 * Time: 6:04 PM
 * <br/>
 * Package: org.khasanof.introduction.entity
 */
@Entity
@Table(name = "auth_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "id"),
        @UniqueConstraint(columnNames = "email")
})
public class AuthUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 120)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 120)
    private String lastName;

    @Column(name = "email", nullable = false, length = 250)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "language")
    private LanguageEnums language;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private StatusEnum status;

    public AuthUserEntity() {
    }

    public AuthUserEntity(String firstName, String lastName, String email, Integer age, String username, String password, LanguageEnums language, StatusEnum status) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.age = age;
        this.username = username;
        this.password = password;
        this.language = language;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LanguageEnums getLanguage() {
        return language;
    }

    public void setLanguage(LanguageEnums language) {
        this.language = language;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AuthUserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", language=" + language +
                ", status=" + status +
                '}';
    }
}
