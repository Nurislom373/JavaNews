package org.khasanof.entity.instagram;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstagramData {
    private String title;
    private String thumbnail;
    private List<Links> links;
}
