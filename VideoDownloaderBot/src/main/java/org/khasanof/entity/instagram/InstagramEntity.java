package org.khasanof.entity.instagram;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class InstagramEntity {
    private boolean status;
    private Integer code;
    private String msg;
    private String domain;
    private InstagramData response;
}
