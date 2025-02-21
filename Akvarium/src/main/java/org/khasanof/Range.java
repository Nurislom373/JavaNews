package org.khasanof;

import lombok.*;

import java.util.Objects;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/21/2025 3:40 PM
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Range {

    private Integer longitude;
    private Integer latitude;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Range range = (Range) o;
        return Objects.equals(longitude, range.longitude) && Objects.equals(latitude, range.latitude);
    }

    @Override
    public int hashCode() {
        return Objects.hash(longitude, latitude);
    }
}
