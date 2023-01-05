package org.khasanof.Relations.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Author: Nurislom
 * <br/>
 * Date: 12/23/2022
 * <br/>
 * Time: 6:19 PM
 * <br/>
 * Package: org.khasanof.Relations.model
 * <br/>
 * <br/>
 * JPA provides the @Embeddable annotation to declare that a class will be embedded by other entities.
 *
 */
@Embeddable
public class ContactEmbeddable {

    @Column(name = "contact_first_name")
    private String firstName;

    @Column(name = "contact_last_name")
    private String lastName;

    public ContactEmbeddable(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
