## scala3-bcrypt

scala3-bcrypt is a scala friendly wrapper of [Password4j](https://github.com/Password4j/password4j)

### Setup
```
libraryDependencies += "dev.roundrop" %% "scala3-bcrypt" % "0.1.0"
```

### Usage

#### Hash password
```scala
scala> import dev.roundrop.bcrypt._
                                                                                                                             
scala> "password".bcrypt()
val res0: scala.util.Try[String] = Success($2a$10$KH/WkRKeqCYJXbR2IcheRO9BORWMRw5SaY0YTRiIjb2u2uhsY/AH.)
```

#### Validate password

```scala                                                                                                                             
scala> "password".isBcrypted("$2a$10$KH/WkRKeqCYJXbR2IcheRO9BORWMRw5SaY0YTRiIjb2u2uhsY/AH.")
val res1: scala.util.Try[Boolean] = Success(true)
```

#### Advanced usage
By default, the salt generated internally, and developer does not need to generate and store salt. But if you decide that you need to use an existing salt, you can use bcrypt in the following way:

```scala
scala> val salt = "$2a$13$8K1p/a0dL1LXMIgoEDFrwO"
val salt: String = $2a$13$8K1p/a0dL1LXMIgoEDFrwO
                                                                                                                                                   
scala> val hash = "my password".bcrypt(salt)
val hash: scala.util.Try[String] = Success($2a$13$U9hCpYnHwj2LwOy49rwQFeymCsLvvlvrIm.dNf9moxn0DxJPrQsIK)

scala> "my password".isBcrypted(hash.get)
val res2: scala.util.Try[Boolean] = Success(true)
```