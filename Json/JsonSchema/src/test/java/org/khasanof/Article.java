package org.khasanof;

import lombok.*;

import java.util.Date;
import java.util.UUID;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 7/27/2024 9:35 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Article {

    private UUID id;
    private String title;
    private String content;
    private Date createdAt;
    private Area area;
}
