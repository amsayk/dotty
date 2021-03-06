object testObjectInstance:
  trait Zip[F[_]]
  trait Traverse[F[_]] {
    extension [A, B, G[_] : Zip](fa: F[A]) def traverse(f: A => G[B]): G[F[B]]
  }

  object instances {
    given zipOption as Zip[Option] = ???
    given traverseList as Traverse[List] = ???
    extension [T](xs: List[T])
      def second: T = xs.tail.head
    extension [T](xs: List[T]) def first: T = xs.head
  }

  def ff(using xs: Zip[Option]) = ???

  ff // error

  List(1, 2, 3).traverse(x => Option(x)) // error

  locally {
    import instances.traverseList
    List(1, 2, 3).traverse(x => Option(x)) // error
  }
end testObjectInstance
