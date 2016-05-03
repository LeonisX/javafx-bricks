name := "JavaFX sample project for SBT"

version := "1.0"

autoScalaLibrary := false

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems
fork := true