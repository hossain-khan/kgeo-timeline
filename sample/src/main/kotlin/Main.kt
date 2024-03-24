package dev.hossain.timeline

import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File

/**
 * Main entry point for the sample application that tests the Google Location History [Parser].
 * Drop your unzipped Google Location History JSON files in `sample/src/main/resources` and run to test app.
 */
fun main() {
  println("Sample app for Google Location History Parser project.")
  val resourcesDir = File("sample/src/main/resources")
  if(!resourcesDir.exists() || !resourcesDir.isDirectory) {
    println("Resources directory not found. Please create `sample/src/main/resources` and put your Google Location History JSON files.")
    return
  }

  parseRecords(resourcesDir)
  parseSemanticRecords(resourcesDir)
}

private fun parseRecords(resourcesDir: File) {
  val parser = Parser()
  val recordsFile = File(resourcesDir, "Records.json")
  val buffer: BufferedSource = recordsFile.source().buffer()
  val records = parser.parseRecords(buffer)

  println("Got records: ${records.locations.size} records.")
  println()
}

fun parseSemanticRecords(resourcesDir: File) {
  val parser = Parser()

  val directory = File(resourcesDir, "Location_History/2022/")
  val files = directory.listFiles()
  files?.forEach {
    // List each files of directory
    println("Parsing file: ${it.name}")

    val jsonText: String = it.readText()
    val semanticTimeline = parser.parseSemanticTimeline(jsonText)

    println("Got timeline items: ${semanticTimeline.timelineObjects.size}")
  }
}
