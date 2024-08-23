package uz.khasanof

/**
 *
 * @param x
 * @param y
 * @return
 */
def avg(x: Double, y: Double): Double = {
  (x + y) / 2
}

/**
 *
 * @param a
 * @param b
 * @return
 */
def rangeSum(a: Int, b: Int) = {
  var sum = 0
  for (i <- a to b) {
    sum += i
  }
  sum
}

/**
 *
 * @param x
 * @param y
 * @return
 */
def power(x: Int, y:Int): Int = {
  def powNested(i: Int,
                accumulator: Int): Int = {
    if (i <= 0) accumulator
    else powNested(i - 1, x * accumulator)
  }
  powNested(y, 1)
}

/**
 * 
 * @param r
 * @param i
 * @param m
 * @param a
 * @param b
 * @return
 */
def mapReduce(r: (Int, Int) => Int,
              i: Int,
              m: Int => Int,
              a: Int, b: Int) = {
  def iter(a: Int, result: Int): Int = {
    if (a > b) {
      result
    } else {
      iter(a + 1, r(m(a), result))
    }
  }
  iter(a, i)
}