package hoard.std

import cats.data.NonEmptyVector
import hoard.std.all._
import hoard.syntax.all._
import org.scalatest.FunSuite
import org.scalatest.prop.Checkers

import cats.laws.discipline.arbitrary._

class NonEmptyVectorInstancesTest extends FunSuite with Checkers {

  test("Sequence.front") {
    check((s: NonEmptyVector[Int]) => s.front == NonEmptyVector.fromVector(s.tail).map(nev => (s.head, nev)))
  }

}
