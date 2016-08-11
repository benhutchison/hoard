package hoard.std

import cats._

import hoard.Sequence


trait VectorInstances {

  import cats.instances.vector.{catsStdInstancesForVector => I}

  implicit def vectorIsSequence[A]: Sequence[Vector] = new Sequence[Vector] {

    def front[A](s: Vector[A]): Option[(A, Vector[A])] = s match {
      case (x +: xs) => Option(x, xs)
      case _ => None
    }

    //Boilerplate delegation of Traverse & MonadCombine from here on

    def empty[A]: Vector[A] = I.empty[A]

    def combineK[A](x: Vector[A], y: Vector[A]): Vector[A] = I.combineK(x, y)

    def pure[A](x: A): Vector[A] = I.pure(x)

    override def map[A, B](fa: Vector[A])(f: A => B): Vector[B] = I.map(fa)(f)

    def flatMap[A, B](fa: Vector[A])(f: A => Vector[B]): Vector[B] = I.flatMap(fa)(f)

    override def map2[A, B, Z](fa: Vector[A], fb: Vector[B])(f: (A, B) => Z): Vector[Z] = I.map2(fa, fb)(f)

    def foldLeft[A, B](fa: Vector[A], b: B)(f: (B, A) => B): B = I.foldLeft(fa, b)(f)

    def foldRight[A, B](fa: Vector[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = I.foldRight(fa, lb)(f)

    override def size[A](fa: Vector[A]): Long = I.size(fa)

    def traverse[G[_], A, B](fa: Vector[A])(f: A => G[B])(implicit G: Applicative[G]): G[Vector[B]] = I.traverse(fa)(f)
  }

}
