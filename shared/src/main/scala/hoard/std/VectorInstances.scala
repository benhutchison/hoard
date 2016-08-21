package hoard.std

import cats._

import hoard.Sequence


trait VectorInstances extends cats.instances.VectorInstances {

  val catsVector = catsStdInstancesForVector

  implicit def vectorIsSequence[A]: Sequence[Vector] = new Sequence[Vector] {

    def front[A](s: Vector[A]): Option[(A, Vector[A])] = s match {
      case (x +: xs) => Option(x, xs)
      case _ => None
    }

    //Boilerplate delegation of Traverse & Monad from here on

    def pure[A](x: A): Vector[A] = catsVector.pure(x)

    override def map[A, B](fa: Vector[A])(f: A => B): Vector[B] = catsVector.map(fa)(f)

    def flatMap[A, B](fa: Vector[A])(f: A => Vector[B]): Vector[B] = catsVector.flatMap(fa)(f)

    def tailRecM[A, B](a: A)(f: A => Vector[Either[A, B]]): Vector[B] = catsVector.tailRecM(a)(f)

    override def map2[A, B, Z](fa: Vector[A], fb: Vector[B])(f: (A, B) => Z): Vector[Z] = catsVector.map2(fa, fb)(f)

    def foldLeft[A, B](fa: Vector[A], b: B)(f: (B, A) => B): B = catsVector.foldLeft(fa, b)(f)

    def foldRight[A, B](fa: Vector[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = catsVector.foldRight(fa, lb)(f)

    override def size[A](fa: Vector[A]): Long = catsVector.size(fa)

    def traverse[G[_], A, B](fa: Vector[A])(f: A => G[B])(implicit G: Applicative[G]): G[Vector[B]] = catsVector.traverse(fa)(f)
  }

}
