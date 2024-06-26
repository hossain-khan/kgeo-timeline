plugins {
  kotlin("jvm")

  // Ktlint plugin
  // https://github.com/pinterest/ktlint
  id("org.jlleitschuh.gradle.ktlint") version "12.1.1"

  // KSP plugin
  // https://github.com/google/ksp/releases
  id("com.google.devtools.ksp").version("1.9.23-1.0.20")
}

group = "dev.hossain.timeline"
version = "0.3-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
  ksp("com.squareup.moshi:moshi-kotlin-codegen:1.15.1")
  implementation("com.squareup.okio:okio:3.9.0")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
  // https://github.com/google/truth
  testImplementation("com.google.truth:truth:1.4.3")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}

ktlint {
  debug = true
  filter {
    exclude { projectDir.toURI().relativize(it.file.toURI()).path.contains("/generated/") }
  }
}
