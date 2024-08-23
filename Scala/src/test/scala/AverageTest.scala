package uz.khasanof

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

/**
 * 
 */
@Test
def whenCalledWithSumAndSquare_thenCorrectValue(): Unit = {
  def square(x: Int) = x * x
  def sum(x: Int, y: Int) = x + y

  def sumSquares(a: Int, b: Int) =
    mapReduce(sum, 0, square, a, b)

  assertEquals(385, sumSquares(1, 10))
}