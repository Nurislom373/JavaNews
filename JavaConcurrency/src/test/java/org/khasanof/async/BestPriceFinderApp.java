package org.khasanof.async;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * @author Nurislom
 * @see org.khasanof.thread_pools.async
 * @since 8/10/2023 5:51 AM
 */
public class BestPriceFinderApp {

    List<Shop> shops = List.of(new Shop("BestPrice"),
            new Shop("LetsSaveBig"),
            new Shop("MyFavoriteShop"),
            new Shop("BuyItAll"));


    @Test
    void test() {
        Shop shop = new Shop("BestShop");
        double jeck = shop.getPrice("jeck");
        System.out.println("jeck = " + jeck);
    }

    @Test
    void asyncTest() {
        Shop shop = new Shop("BestShop");
        long start = System.nanoTime();
        Future<Double> futurePrice = shop.getPriceAsyncV2("my favorite product");
        long invocationTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Invocation returned after " + invocationTime + " msecs");
        // Do some more tasks, like querying other shops
        doSomethingElse();
        // while the price of the product is being calculated
        try {
            double price = futurePrice.get();
            System.out.printf("Price is %.2f%n", price);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
        System.out.println("Price returned after " + retrievalTime + " msecs");
    }

    public List<String> findPrices(String product) {
        List<CompletableFuture<String>> priceFutures =
                shops.stream()
                        .map(shop -> CompletableFuture.supplyAsync(
                                () -> shop.getName() + " price is " +
                                        shop.getPrice(product)))
                        .toList();
        return priceFutures.stream()
                .map(CompletableFuture::join)
                .toList();
    }

    void doSomethingElse() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Hello World!");
        }
    }

    class Shop {

        private String name;

        public String getName() {
            return name;
        }

        public Shop(String name) {
            this.name = name;
        }

        public double getPrice(String product) {
            return calculatePrice(product);
        }

        public Future<Double> getPriceAsync(String product) {
            CompletableFuture<Double> future = new CompletableFuture<>();
            new Thread(() -> {
                double price = calculatePrice(product);
                future.complete(price);
            });
            return future;
        }

        public Future<Double> getPriceAsyncV2(String product) {
            return CompletableFuture.supplyAsync(() -> calculatePrice(product));
        }

        private void delay() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private double calculatePrice(String s) {
            delay();
            return new Random().nextInt() * s.charAt(0) + s.charAt(1);
        }

    }

    class Discount {

        public enum Code {
            NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
            private final int percentage;

            Code(int percentage) {
                this.percentage = percentage;
            }
        }

        public static String applyDiscount(Quote quote) {
            return quote.getShopName() + " price is " +
                    Discount.apply(quote.getPrice(),
                            quote.getDiscountCode());
        }

        @SneakyThrows
        private static double apply(double price, Code code) {
            Thread.sleep(1000);
            return price * (100 - code.percentage) / 100;
        }

    }

    class Quote {
        private final String shopName;
        private final double price;
        private final Discount.Code discountCode;

        public Quote(String shopName, double price, Discount.Code code) {
            this.shopName = shopName;
            this.price = price;
            this.discountCode = code;
        }

        public Quote parse(String s) {
            String[] split = s.split(":");
            String shopName = split[0];
            double price = Double.parseDouble(split[1]);
            Discount.Code discountCode = Discount.Code.valueOf(split[2]);
            return new Quote(shopName, price, discountCode);
        }

        public String getShopName() {
            return shopName;
        }

        public double getPrice() {
            return price;
        }

        public Discount.Code getDiscountCode() {
            return discountCode;
        }
    }
}
