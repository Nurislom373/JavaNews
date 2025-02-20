package org.khasanof.domain;

import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Nurislom
 * @see org.khasanof.domain
 * @since 9/25/2024 5:54 PM
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class LimitedTotp {

    private final String code;
    private final AtomicInteger limit;

    public LimitedTotp(String code) {
        this.code = code;
        this.limit = new AtomicInteger(1);
    }

    public LimitedTotp(String code, int limit) {
        this.code = code;
        this.limit = new AtomicInteger(limit);
    }
}
