package hoard.std

import hoard.std.all._
import hoard.syntax.all._
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

class VectorInstancesTest extends FunSuite with Checkers {

  test("Sequence.front") {
    check((s: Vector[Int]) => s.front == s.headOption.map(h => (h, s.tail)))
  }

}
