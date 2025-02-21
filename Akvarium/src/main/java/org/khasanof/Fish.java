package org.khasanof;

import lombok.Getter;
import lombok.ToString;
import org.khasanof.enums.Gender;
import org.khasanof.utils.Color;
import org.khasanof.utils.EnumUtils;
import org.khasanof.utils.RandomUtils;

import java.util.concurrent.Semaphore;

import static org.khasanof.utils.Print.println;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/21/2025 3:40 PM
 */
@Getter
@ToString
public class Fish extends Thread {

    private Integer residencePeriod;

    private final Range range;
    private final Gender gender;
    private final Semaphore semaphore;
    private final Aquarium aquarium;

    public Fish(Semaphore semaphore, Aquarium aquarium) {
        this.semaphore = semaphore;
        this.aquarium = aquarium;
        this.gender = EnumUtils.getRandomEnumValue(Gender.class);
        this.residencePeriod = RandomUtils.getRandomNumber(2, 10);
        this.range = new Range();
        setNewPosition();
    }

    @Override
    public void run() {
        while (residencePeriod > 0) {
            residencePeriod--;
            setNewPosition();
            printNewPosition();
            tryAwaitOtherFishes();
        }
        printFishDie();
    }

    private void printNewPosition() {
        println(Color.GREEN, "Fish:" + Thread.currentThread().getName()
                + ",longitude:" + range.getLongitude()
                + ",latitude:" + range.getLatitude());
    }

    private void setNewPosition() {
        range.setLatitude(RandomUtils.getRandomNumber(1, aquarium.getLatitude()));
        range.setLongitude(RandomUtils.getRandomNumber(1, aquarium.getLongitude()));
    }

    private void tryAwaitOtherFishes() {
        try {
            semaphore.acquire();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void printFishDie() {
        println(Color.RED, Thread.currentThread().getName() + " Fish Die !!!");
    }
}
