package hoard

import cats.{Monad, Traverse}
import simulacrum.typeclass

@typeclass trait Sequence[S[_]] extends Traverse[S] with Monad[S] {

  def front[A](s: S[A]): Option[(A, S[A])]
}

object Sequence {
}