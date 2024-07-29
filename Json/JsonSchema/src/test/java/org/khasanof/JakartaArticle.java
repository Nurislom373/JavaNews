package org.khasanof;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 7/27/2024 9:51 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class JakartaArticle {

    @NotNull
    private UUID id;

    @NotBlank
    @Size(min = 1, max = 9000)
    private String title;

    @NotBlank
    private String content;

    @NotNull
    private Date createdAt;

    @NotNull
    private Area area;
}
