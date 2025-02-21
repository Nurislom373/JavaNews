package org.khasanof;

import lombok.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/21/2025 11:52 AM
 */
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Aquarium {

    private Integer fishSize;
    private Integer longitude;
    private Integer latitude;
    private Integer capacity;
    private Integer count;

    public Aquarium(Integer fishSize, Integer longitude, Integer latitude) {
        this(fishSize, longitude, latitude, 10);
    }

    public Aquarium(Integer fishSize, Integer longitude, Integer latitude, Integer count) {
        this.fishSize = fishSize;
        this.longitude = longitude;
        this.latitude = latitude;
        this.count = count;
        calculateCapacity();
    }

    private void calculateCapacity() {
        capacity = (longitude * latitude) / fishSize;
    }
}
