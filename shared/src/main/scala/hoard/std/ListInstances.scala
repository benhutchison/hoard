package hoard.std

import cats.{Applicative, Eval}
import hoard.Sequence

trait ListInstances extends cats.instances.ListInstances {

  val catsList = catsStdInstancesForList


  implicit def listIsSequence[A]: Sequence[List] = new Sequence[List] {

    def front[A](s: List[A]): Option[(A, List[A])] = s match {
      case (x :: xs) => Option(x, xs)
      case _ => None
    }

    //Boilerplate delegation of Traverse & Monad from here on

    def pure[A](x: A): List[A] = catsList.pure(x)

    override def map[A, B](fa: List[A])(f: A => B): List[B] = catsList.map(fa)(f)

    def flatMap[A, B](fa: List[A])(f: A => List[B]): List[B] = catsList.flatMap(fa)(f)

    def tailRecM[A, B](a: A)(f: A => List[Either[A, B]]): List[B] = catsList.tailRecM(a)(f)

    override def map2[A, B, Z](fa: List[A], fb: List[B])(f: (A, B) => Z): List[Z] = catsList.map2(fa, fb)(f)

    def foldLeft[A, B](fa: List[A], b: B)(f: (B, A) => B): B = catsList.foldLeft(fa, b)(f)

    def foldRight[A, B](fa: List[A], lb: Eval[B])(f: (A, Eval[B]) => Eval[B]): Eval[B] = catsList.foldRight(fa, lb)(f)

    override def size[A](fa: List[A]): Long = catsList.size(fa)

    def traverse[G[_], A, B](fa: List[A])(f: A => G[B])(implicit G: Applicative[G]): G[List[B]] = catsList.traverse(fa)(f)

  }
}
