name := "social_networking_kata"

version := "1.0"

scalaVersion := "2.10.3"

libraryDependencies ++= Seq(
							"org.scalatest" % "scalatest_2.10" % "2.2.1" % "test",
							"org.scalamock" %% "scalamock-scalatest-support" % "3.2" % "test",
              "com.github.nikolavp" % "approval-core" % "0.3",
							"org.mockito" % "mockito-all" % "1.9.5" % "test")
