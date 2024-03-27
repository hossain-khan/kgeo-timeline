plugins {
  kotlin("jvm") version "1.9.23"

  // Publishing plugin
  // https://docs.gradle.org/current/userguide/publishing_maven.html
  `maven-publish`
}

group = "dev.hossain.timeline"
version = "0.2-SNAPSHOT"

repositories {
  mavenCentral()
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
      groupId = group.toString()
      artifactId = rootProject.name
      version = version

      from(components["java"])
    }
  }
}
