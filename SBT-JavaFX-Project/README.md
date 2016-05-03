If project can't see JavaFX library, try this code in `build.sbt`:

Важно: должна быть верно установлена системная переменная `JAVA_HOME`

```
//Add Javafx8 library
unmanagedJars in Compile += Attributed.blank(file(System.getenv("JAVA_HOME") + "/jre/lib/ext/jfxrt.jar"))
```

Так же в сети было найдено более развёрнутое решение:
```
//---
// Note: Wouldn't 'sbt' be able to provide us a nice default for this (the following logic
//      would deserve to be automatic, not in a project build script). AK 4-Jan-2013
//
javaHome := {
  var s = System.getenv("JAVA_HOME")
  if (s==null) {
    // tbd. try to detect JDK location on multiple platforms
    //
    // OS X: "/Library/Java/JavaVirtualMachines/jdk1.xxx.jdk/Contents/Home" with greatest id (i.e. "7.0_10")
    //
    s= "/Library/Java/JavaVirtualMachines/jdk1.7.0_10.jdk/Contents/Home"
  }
  //
  val dir = new File(s)
  if (!dir.exists) {
    throw new RuntimeException( "No JDK found - try setting 'JAVA_HOME'." )
  }
  //
  Some(dir)  // 'sbt' 'javaHome' value is ': Option[java.io.File]'
}
```

```
//---
// JavaFX
//
// Note: We shouldn't even need to say this at all. Part of Java 7 RT (since 7u06) and should come from there (right)
//      The downside is that now this also gets into the 'one-jar' .jar package (where it would not need to be,
//      and takes 15MB of space - of the 17MB package!) AKa 1-Nov-2012
//
unmanagedJars in Compile <+= javaHome map { jh /*: Option[File]*/ =>
  val dir: File = jh.getOrElse(null)    // unSome
  //
  val jfxJar = new File(dir, "/jre/lib/jfxrt.jar")
  if (!jfxJar.exists) {
    throw new RuntimeException( "JavaFX not detected (needs Java runtime 7u06 or later): "+ jfxJar.getPath )  // '.getPath' = full filename
  }
  Attributed.blank(jfxJar)
}
```

[Источник](http://stackoverflow.com/questions/14123749/how-to-detect-javafx-runtime-jar-in-sbt)