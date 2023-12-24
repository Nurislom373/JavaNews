package org.khasanof.locks

import java.util.Scanner

fun main() {

    val scanner = Scanner(System.`in`)

    while (true) {
        print("Enter your age: ")
        val son1: Int = scanner.nextInt()
        if (son1 > 0) {
            if (18 <= son1) {
                println("Kachok Nurislom")
            } else {
                println("Chichqoq Ibrohim")
            }
        } else {
            println("yoshingiz noldan kichik bo'lishi mumkin emas brattan!")
        }
    }

}