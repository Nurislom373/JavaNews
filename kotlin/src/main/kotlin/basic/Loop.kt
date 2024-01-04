package org.khasanof.basic

/**
 * @see org.khasanof.basic
 * @author Nurislom
 * @since 1/4/2024 8:11 PM
 */

fun main() {
    rangesLoop()
}

private fun forLoop() {

    val items = listOf("apple", "banana", "kiwi")
    for (item in items) {
        println(item)
    }

}

private fun whileLoop() {

    val items = listOf("apple", "banana", "kiwifruit")
    var index = 0
    while (index < items.size) {
        println("item at $index is ${items[index]}")
        index++
    }

}

private fun rangesLoop() {

    for (x in 1..5) {
        print(x)
    }
    println()
    for (x in 1..10 step 2) {
        print(x)
    }
    println()
    for (x in 9 downTo 0 step 3) {
        print(x)
    }

}