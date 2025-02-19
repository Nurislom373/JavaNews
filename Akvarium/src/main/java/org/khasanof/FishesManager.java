package org.khasanof;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.stream.Collectors;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/20/2025 12:45 AM
 */
public class FishesManager {

    public void createFishesRandomly() {
        final List<Fish> fishes = new CopyOnWriteArrayList<>();
        int randomNumber = RandomUtils.getRandomNumber();

        Semaphore semaphore = new Semaphore(randomNumber);

        for (int i = 0; i < randomNumber; i++) {
            Fish fish = createFish(semaphore);
            fish.start();
            fishes.add(fish);
        }

        FishWorker fishWorker = new FishWorker(fishes, semaphore);
        fishWorker.start();
    }

    private Fish createFish(Semaphore semaphore) {
        return new Fish(semaphore);
    }

    public static class FishWorker extends Thread {

        private final List<Fish> fishes;
        private final Semaphore semaphore;

        public FishWorker(List<Fish> fishes, Semaphore semaphore) {
            this.fishes = fishes;
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            System.out.println("Started Fish Worker");
            while (true) {
                if (semaphore.availablePermits() == 0) {
                    System.out.println("Fished finished.");
                    fishes.stream()
                            .collect(Collectors.groupingBy(Fish::getValue, Collectors.counting())) // Har bir elementni sanaymiz
                            .entrySet().stream()
                            .filter(entry -> entry.getValue() > 1) // Faqat takrorlanganlarni olamiz
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toSet())
                            .forEach(num -> {
                                System.out.println("num = " + num);
                                System.out.println("Added new fish");
                                fishes.add(new Fish(semaphore));
                            });
                    semaphore.release(fishes.size());
                }
            }
        }
    }

    public static class Fish extends Thread {

        private Integer value;
        private Integer residencePeriod;

        private final Semaphore semaphore;

        public Fish(Semaphore semaphore) {
            this(semaphore, RandomUtils.getRandomNumber(), RandomUtils.getRandomNumber());
        }

        public Fish(Semaphore semaphore, Integer value, Integer residencePeriod) {
            this.value = value;
            this.semaphore = semaphore;
            this.residencePeriod = residencePeriod;
        }

        /**
         *
         */
        @Override
        public void run() {
            while (residencePeriod != 0) {
                residencePeriod--;
                setNewValue();
                System.out.println("value = " + value);
                tryAwaitOtherFishes();
            }
            System.out.println("Fish already Dead !!!");
        }

        /**
         *
         */
        private void tryAwaitOtherFishes() {
            try {
                System.out.println("Awaiting");
                semaphore.acquire();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        /**
         *
         * @return
         */
        public Integer getValue() {
            return value;
        }

        /**
         *
         */
        private void setNewValue() {
            this.value = RandomUtils.getRandomNumber();
        }
    }
}
