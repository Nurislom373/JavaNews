package org.khasanof.entity.tiktok;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TikTokEntity {
    private List<String> video;
    private List<String> music;
    private List<String> cover;
}
