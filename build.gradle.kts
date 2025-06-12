plugins {
  kotlin("jvm") version "2.1.10"

  // Publishing plugin
  // https://docs.gradle.org/current/userguide/publishing_maven.html
  `maven-publish`

  // Dokka - Documentation Engine for Kotlin
  // https://github.com/Kotlin/dokka
  id("org.jetbrains.dokka") version "2.0.0"
}

group = "dev.hossain.timeline"
version = "0.5-SNAPSHOT"

repositories {
  mavenCentral()
}

subprojects {
  apply(plugin = "org.jetbrains.dokka")
}

dependencies {
  testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}

// https://docs.gradle.org/current/userguide/publishing_maven.html#sec:identity_values_in_the_generated_pom
publishing {
  publications {
    create<MavenPublication>("mavenJava") {
      println("Publishing to Maven Central")
      groupId = group.toString()
      artifactId = "parser"
      version = version

      from(components["java"])
    }
  }
}
