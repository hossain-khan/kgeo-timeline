package dev.hossain.timeline

import java.io.File

fun main() {
  println("Sample app for timeline project.")
//  val parser = Parser()
//  // load "Records.json" file
//  val recordsJson = parser::class.java.classLoader.getResource("Records.json")?.readText()!!
//  val records = parser.parseRecords(recordsJson)
//
//  println("Got records: ${records.locations.size} records.")
  println()

  parseSemanticRecords()
}

fun parseSemanticRecords() {
  val parser = Parser()

  val directory = File("sample/src/main/resources/Location_History/2022/")
  val files = directory.listFiles()
  files?.forEach {
    // List each files of directory
    println("Parsing file: ${it.name}")

    val jsonText: String = it.readText()
    val semanticTimeline = parser.parseSemanticTimeline(jsonText)

    println("Got timeline items: ${semanticTimeline.timelineObjects.size}")
  }
}
