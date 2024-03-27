val scala3Version = "3.4.0"

ThisBuild / semanticdbEnabled := true
ThisBuild / semanticdbVersion := scalafixSemanticdb.revision

scalacOptions ++= Seq(
  "-deprecation",
  "-feature"
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3-bcrypt",
    organization := "dev.roundrop",
    version := "0.1.0-SNAPSHOT",
    licenses := Seq("Apache-2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0")),

    scalaVersion := scala3Version,

    libraryDependencies ++= Seq(
      "com.password4j" % "password4j" % "1.8.1",
      "org.scalameta" %% "munit" % "0.7.29" % Test
    )
  )

pomExtra in Global := {
  <scm>
    <url>git@github.com:roundrop/scala3-bcrypt.git</url>
    <connection>scm:git:git@github.com:roundrop/scala3-bcrypt.git</connection>
    <developerConnection>scm:git:git@github.com:roundrop/scala3-bcrypt.git</developerConnection>
  </scm>
    <developers>
      <developer>
        <id>roundrop</id>
        <name>Ryuji Yamashita</name>
        <email>roundrop@gmail.com</email>
      </developer>
    </developers>
}
