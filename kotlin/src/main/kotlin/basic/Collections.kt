package org.khasanof.basic

/**
 * @see org.khasanof.basic
 * @author Nurislom
 * @since 1/4/2024 8:18 PM
 */

fun main() {

    val fruits = listOf("banana", "avocado", "apple", "kiwifruit")
    fruits
        .filter { it.startsWith("a") }
        .sortedBy { it }
        .map { it.uppercase() }
        .forEach { println(it) }

    repeat((1..10).count()) {
        println("Hello World!")
    }

    (1..10).forEach { i ->
        println(i)
    }
}