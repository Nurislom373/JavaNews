package org.khasanof;

public class Main {

    public static void main(String[] args) {
        Aquarium aquarium = createAquarium();
        AquariumManager aquariumManager = new AquariumManager(aquarium);
        aquariumManager.start();

        int i = 3247 - 2193421;
    }

    private static Aquarium createAquarium() {
        return new Aquarium(5, 50, 50, 20);
    }
}