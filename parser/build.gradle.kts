plugins {
  kotlin("jvm")
  id("org.jlleitschuh.gradle.ktlint") version "12.1.0"
}

group = "dev.hossain.timeline"
version = "1.0-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.squareup.moshi:moshi:1.15.1")
  implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
  implementation("com.squareup.okio:okio:3.9.0")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}
