package org.khasanof;

import org.khasanof.enums.Gender;
import org.khasanof.utils.Color;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;

import static org.khasanof.utils.Print.print;
import static org.khasanof.utils.Print.println;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 2/20/2025 12:45 AM
 */
public class AquariumManager {

    private final Aquarium aquarium;
    private final List<Fish> fishes = new CopyOnWriteArrayList<>();

    public AquariumManager(Aquarium aquarium) {
        this.aquarium = aquarium;
    }

    public void start() {
        var count = aquarium.getCount();
        println(Color.GREEN, "Created fishes size: " + count);
        Semaphore semaphore = new Semaphore(count);

        createNewFishes(count, semaphore);
        checkMatchedPositionFishes(semaphore);
    }

    private void createNewFishes(int randomNumber, Semaphore semaphore) {
        for (int i = 0; i < randomNumber; i++) {
            createNewFish(semaphore, aquarium);
        }
    }

    private void checkMatchedPositionFishes(Semaphore semaphore) {
        while (true) {
            checkMatchedPositionFishesInternal(semaphore);
        }
    }

    private void checkMatchedPositionFishesInternal(Semaphore semaphore) {
        if (semaphore.availablePermits() == 0) {
            checkFishesAllDied();
            checkAquariumCapacity();

            println(Color.CYAN, "Started check matched fishes...");
            fishes.removeIf(fish -> !fish.isAlive());

            Map<Range, Gender> sortedFishesByRange = new HashMap<>();
            List<Range> matchedFishesRange = new CopyOnWriteArrayList<>();
            checkMatchRangeFishes(sortedFishesByRange, matchedFishesRange);

            println(Color.CYAN, "Fishes : " + fishes);
            println(Color.CYAN, "Fishes size : " + fishes.size());
            println(Color.CYAN, "Found matched fishes : " + matchedFishesRange);

            matchedFishesRange.forEach(matchFishNumber -> createNewFish(semaphore, aquarium));
            trySleepSecondMainThread();

            semaphore.release(fishes.size());
            println(Color.CYAN, "Finished check matched fishes...");
        }
    }

    private void checkFishesAllDied() {
        if (fishes.isEmpty()) {
            println(Color.RED, "All fishes died. System shut down !!!");
            Runtime.getRuntime().exit(-1);
        }
    }

    private void checkAquariumCapacity() {
        if (fishes.size() == aquarium.getCapacity()) {
            println(Color.RED, "Aquarium filled. System shut down !!!");
            Runtime.getRuntime().exit(-1);
        }
    }

    private void checkMatchRangeFishes(Map<Range, Gender> sortedFishesByRange, List<Range> matchedFishesRange) {
        for (Fish fish : fishes) {
            if (sortedFishesByRange.containsKey(fish.getRange()) && !Objects.equals(sortedFishesByRange.get(fish.getRange()), fish.getGender())) {
                matchedFishesRange.add(fish.getRange());
                continue;
            }
            sortedFishesByRange.put(fish.getRange(), fish.getGender());
        }
    }

    private void createNewFish(Semaphore semaphore, Aquarium aquarium) {
        Fish fish = new Fish(semaphore, aquarium);
        fish.start();

        fishes.add(fish);
        println(Color.YELLOW, "Added new fish");
    }

    private void trySleepSecondMainThread() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
