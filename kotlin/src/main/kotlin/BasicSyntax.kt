package org.khasanof

fun main(args: Array<String>) {
    println(args.contentToString())
    printsExample()
}

fun printsExample() {

    print("Hello ")
    print("World!")

    println("Hello world!")
    println(42)

    println(sum(10, 10))
    println(sumV2(20, 10))
    println(printSum(10, 29))

}

fun sum(a: Int, b: Int): Int {
    return a + b;
}

fun sumV2(a: Int, b: Int) = a + b

fun printSum(a: Int, b: Int): Unit {
    println("sum of $a and $b is ${a + b}")
}

fun variables() {

    val a: Int = 1
    val b = 2
    val c: Int
    c = 3

}