## hoard

### Overview

Typeclass-based general purpose collections API for Scala. Extends from Cats. The focus is on defining good abstractions 
not new data-structure implementations.
Instances are based upon core Scala collections and [Dogs](https://github.com/stew/dogs)

Inspired by:

- Okasaki's *Edison* http://www.usma.edu/eecs/SiteAssets/SitePages/Faculty%20Publication%20Documents/Okasaki/hw00edison.pdf

- Simon Peyton-Jones *Bulk Types with Class* http://citeseerx.ist.psu.edu/viewdoc/download?doi=10.1.1.144.1863&rep=rep1&type=pdf

### SBT Coordinates

[![Maven Central](https://img.shields.io/maven-central/v/com.github.benhutchison/hoard_2.12.svg)][search.maven]

Scala.jvm: `"com.github.benhutchison" %% "hoard" % version`

Scala.js: `"com.github.benhutchison" %%% "hoard" % version`

Published for Scala 2.11, 2.12

[![Build Status](https://travis-ci.org/benhutchison/hoard.svg?branch=master)](https://travis-ci.org/benhutchison/hoard)

### Example

```
scala> import hoard._, implicits._
import hoard._
import implicits._

scala> List(1, 2, 3).front
res0: Option[(Int, List[Int])] = Some((1,List(2, 3)))

scala> Vector.empty[Int].front
res3: Option[(Int, scala.collection.immutable.Vector[Int])] = None
```

### Version History 

| Version | When   | Changes |
| --------| -------| --------|
| 0.1     | Aug 16 | Defined `front` to de-structure Sequences, implement `List` and `Vector`|
| 0.2     | na | skipped |
| 0.3     | Nov 16 | List/Vector instances inherit from cats instances to avoid ambiguity. Scala 2.12, cats 0.8.1 |

## Contibuting

Issues and pull requests are welcome. Code contributions should be aligned with the library scope to be included, and include unit tests.

This project supports the Typelevel [code of conduct](http://typelevel.org/conduct.html) and aims that its channels (mailing list, Gitter, github, etc.) to be welcoming environments for everyone.
