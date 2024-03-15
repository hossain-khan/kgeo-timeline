package dev.hossain.timeline

import java.io.File

fun main() {
  println("Sample app for timeline project.")
  val parser = Parser()
  // load "Records.json" file
  val recordsJson = parser::class.java.classLoader.getResource("Records.json")?.readText()!!
  val records = parser.parseRecords(recordsJson)

  println("Got records: ${records.locations.size} records.")
  println()
}
