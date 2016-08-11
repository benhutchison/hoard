package hoard.std

import cats.{Applicative, Eval}
import hoard.Sequence

trait ListInstances {

  import cats.instances.list.{catsStdInstancesForList => I}


  implicit def listIsSequence[A]: Sequence[List] = new Sequence[List] {

    def front[A](s: List[A]): Option[(A, List[A])] = s match {
      case (x :: xs) => Option(x, xs)
      case _ => None
    }

    //Boilerplate delegation of Traverse & MonadCombine from here on

    def empty[A]: List[A] = I.empty[A]

    def combineK[A](x: List[A], y: List[A]): List[A] = I.combineK(x, y)

    def pure[A](x: A): List[A] = I.pure(x)

    override def map[A, B](fa: List[A])(f: A => B): List[B] = I.map(fa)(f)

    def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = I.flatMap(fa)(f)

    override def map2[A, B, Z](fa: List[A], fb: List[B])(f: (A, B) => Z): List[Z] = I.map2(fa, fb)(f)

    def foldLeft[A, B](fa: List[A], b: B)(f: (B, A) => B): B = I.foldLeft(fa, b)(f)

    def foldRight[A, B](fa: List[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = I.foldRight(fa, lb)(f)

    override def size[A](fa: List[A]): Long = I.size(fa)

    def traverse[G[_], A, B](fa: List[A])(f: A => G[B])(implicit G: Applicative[G]): G[List[B]] = I.traverse(fa)(f)

  }
}
