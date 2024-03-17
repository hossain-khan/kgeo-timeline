package dev.hossain.timeline

import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.BufferedReader
import java.io.File

fun main() {
  println("Sample app for timeline project.")
  parseRecords()
  parseSemanticRecords()
}

private fun parseRecords() {
  val parser = Parser()
  // load "Records.json" file
  val recordsFile = File("sample/src/main/resources/Records.json")
  val buffer: BufferedSource = recordsFile.source().buffer()
  val records = parser.parseRecords(buffer)

  println("Got records: ${records.locations.size} records.")
  println()
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
