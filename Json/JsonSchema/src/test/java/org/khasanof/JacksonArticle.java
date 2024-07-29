package org.khasanof;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 7/27/2024 9:50 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JacksonArticle {

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private UUID id;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE, required = true)
    private String title;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE, required = true)
    private String content;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Date createdAt;

    @JsonProperty(access = JsonProperty.Access.READ_WRITE)
    private Area area;
}
