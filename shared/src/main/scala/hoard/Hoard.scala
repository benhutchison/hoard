package hoard

import cats.{MonadCombine, Traverse}
import simulacrum.typeclass

@typeclass trait Sequence[S[_]] extends Traverse[S] with MonadCombine[S] {

  def front[A](s: S[A]): Option[(A, S[A])]
}

object Sequence {
}