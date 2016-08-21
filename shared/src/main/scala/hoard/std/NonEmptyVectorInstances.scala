package hoard.std

import cats._
import cats.data.NonEmptyVector
import hoard.Sequence


trait NonEmptyVectorInstances extends cats.instances.VectorInstances {

  val catsNEVector = NonEmptyVector.catsDataInstancesForNonEmptyVector

  implicit def nevectorIsSequence[A]: Sequence[NonEmptyVector] = new Sequence[NonEmptyVector] {

    def front[A](s: NonEmptyVector[A]): Option[(A, NonEmptyVector[A])] = s match {
      case NonEmptyVector(h, vec) => NonEmptyVector.fromVector(vec).map(nev => (h, nev))
    }

    //Boilerplate delegation of Traverse & Monad from here on

    def pure[A](x: A): NonEmptyVector[A] = catsNEVector.pure(x)

    override def map[A, B](fa: NonEmptyVector[A])(f: A => B): NonEmptyVector[B] = catsNEVector.map(fa)(f)

    def flatMap[A, B](fa: NonEmptyVector[A])(f: A => NonEmptyVector[B]): NonEmptyVector[B] = catsNEVector.flatMap(fa)(f)

    def tailRecM[A, B](a: A)(f: A => NonEmptyVector[Either[A, B]]): NonEmptyVector[B] = catsNEVector.tailRecM(a)(f)

    override def map2[A, B, Z](fa: NonEmptyVector[A], fb: NonEmptyVector[B])(f: (A, B) => Z): NonEmptyVector[Z] = catsNEVector.map2(fa, fb)(f)

    def foldLeft[A, B](fa: NonEmptyVector[A], b: B)(f: (B, A) => B): B = catsNEVector.foldLeft(fa, b)(f)

    def foldRight[A, B](fa: NonEmptyVector[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = catsNEVector.foldRight(fa, lb)(f)

    override def size[A](fa: NonEmptyVector[A]): Long = catsNEVector.size(fa)

    def traverse[G[_], A, B](fa: NonEmptyVector[A])(f: A => G[B])(implicit G: Applicative[G]): G[NonEmptyVector[B]] = catsNEVector.traverse(fa)(f)
  }

}
