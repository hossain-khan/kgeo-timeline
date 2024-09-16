plugins {
  kotlin("jvm")
}

group = "dev.hossain.timeline"
version = "0.3-SNAPSHOT"

repositories {
  mavenCentral()
}

dependencies {
  implementation(project(":parser"))
  implementation("com.squareup.moshi:moshi-kotlin:1.15.1")
  implementation("com.squareup.okio:okio:3.9.1")
  implementation("com.jakewharton.picnic:picnic:0.7.0")
  testImplementation("org.jetbrains.kotlin:kotlin-test")
}

tasks.test {
  useJUnitPlatform()
}
kotlin {
  jvmToolchain(21)
}
