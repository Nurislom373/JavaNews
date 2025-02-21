package org.khasanof;

public class Main {

    public static void main(String[] args) {
        Aquarium aquarium = createAquarium();
        AquariumManager aquariumManager = new AquariumManager(aquarium);
        aquariumManager.start();
    }

    private static Aquarium createAquarium() {
        return new Aquarium(5, 50, 50, 2);
    }
}