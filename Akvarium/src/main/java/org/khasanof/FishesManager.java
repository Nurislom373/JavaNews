package org.khasanof;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.function.Consumer;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/20/2025 12:45 AM
 */
public class FishesManager {

    public void createFishesRandomly() {
        final List<Fish> fishes = new CopyOnWriteArrayList<>();
        int randomNumber = 5;
        System.out.println("randomNumber = " + randomNumber);

        Semaphore semaphore = new Semaphore(randomNumber);

        for (int i = 0; i < randomNumber; i++) {
            Fish fish = new Fish(semaphore, fishes::remove);
            fish.start();
            fishes.add(fish);
        }

        while (true) {
            if (semaphore.availablePermits() == 0) {
                System.out.println("Fishes finished.");

                fishes.removeIf(fish -> !fish.isAlive());

                Map<Integer, Gender> sortedFishes = new HashMap<>();
                List<Integer> matchedValues = new CopyOnWriteArrayList<>();

                for (Fish fish : fishes) {
                    if (sortedFishes.containsKey(fish.getValue()) && !Objects.equals(sortedFishes.get(fish.getValue()), fish.getGender())) {
                        System.out.println("fishes matched = " + fish.getValue());
                        matchedValues.add(fish.getValue());
                        continue;
                    }
                    sortedFishes.put(fish.getValue(), fish.getGender());
                }

                System.out.println("fishes = " + fishes);
                System.out.println("fishes : size = " + fishes.size());
                System.out.println("sortedFishes = " + sortedFishes);
                System.out.println("sortedFishes : size = " + sortedFishes.size());
                System.out.println("matchedValues = " + matchedValues);

                if (fishes.isEmpty()) {
                    System.exit(-1);
                }

                matchedValues.forEach(matchFishNumber -> {
                    System.out.println("Added new fish");
                    Fish fish = new Fish(semaphore, fishes::remove);
                    fish.start();
                    fishes.add(fish);
                });

                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

                for (int i = 0; i < fishes.size(); i++) {
                    semaphore.release();
                }
            }
        }
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
        }
    }

    public static class Fish extends Thread {

        private Integer value;
        private Integer residencePeriod;

        private final Gender gender;
        private final Semaphore semaphore;
        private final Consumer<Fish> fishConsumer;

        public Fish(Semaphore semaphore, Consumer<Fish> fishConsumer) {
            this.semaphore = semaphore;
            this.fishConsumer = fishConsumer;
            this.value = RandomUtils.getRandomNumber();
            this.gender = EnumUtils.getRandomEnumValue(Gender.class);
            this.residencePeriod = RandomUtils.getRandomNumber();
        }

        /**
         *
         */
        @Override
        public void run() {
            while (residencePeriod > 0) {
                residencePeriod--;
                setNewValue();
                System.out.println("ThreadName = " + Thread.currentThread().getName() + ",value = " + value);
                tryAwaitOtherFishes();
            }
            System.out.println(Thread.currentThread().getName() + " Fish already Dead !!!");
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
         * @return
         */
        public Integer getValue() {
            return value;
        }

        /**
         * @return
         */
        public Gender getGender() {
            return gender;
        }

        /**
         *
         */
        private void setNewValue() {
            this.value = RandomUtils.getRandomNumber();
        }

        @Override
        public String toString() {
            return "Fish{" +
                    "value=" + value +
                    ", residencePeriod=" + residencePeriod +
                    ", gender=" + gender +
                    '}';
        }
    }
}
