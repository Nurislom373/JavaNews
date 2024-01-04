package org.khasanof

/**
 * @see org.khasanof
 * @author Nurislom
 * @since 1/4/2024 8:05 PM
 */

fun main() {
    val rectangle = Rectangle(5.0, 2.0)
    println("The perimeter is ${rectangle.perimeter}")
    stringTemplates()
}

open class Shape

class Rectangle(val height: Double, val length: Double) : Shape() {
    val perimeter = (height + length) * 2
}

private fun stringTemplates() {

    var a = 1
    // simple name in template:
    val s1 = "a is $a"

    a = 2
    // arbitrary expression in template:
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)

}