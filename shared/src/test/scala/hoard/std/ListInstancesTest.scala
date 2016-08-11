package hoard.std

import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

import hoard.syntax.all._
import hoard.std.all._

class ListInstancesTest extends FunSuite with Checkers {

  test("Sequence.front") {
    check((s: List[Int]) => s.front == s.headOption.map(h => (h, s.tail)))
  }

}
