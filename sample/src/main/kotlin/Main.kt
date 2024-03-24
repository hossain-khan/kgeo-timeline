package dev.hossain.timeline

import okio.BufferedSource
import okio.buffer
import okio.source
import java.io.File

/**
 * Main entry point for the sample application that tests the Google Location History [Parser].
 * Drop your unzipped Google Location History JSON files from `Takeout\Location History (Timeline)`
 * into `sample/src/main/resources` and run to test app.
 *
 * Sample directory structure for `Takeout\Location History (Timeline)`:
 * ```
 * |- Semantic Location History
 * |  |- 2022
 * |  |  |- 2022_January.json
 * |- Records.json
 * |- Settings.json
 * |- Timeline Edits.json
 * ```
 */
fun main() {
  println("Sample app for Google Location History Parser project.")
  val resourcesDir = File("sample/src/main/resources")
  if(!resourcesDir.exists() || !resourcesDir.isDirectory) {
    println("Resources directory not found. Please create `sample/src/main/resources` and put your Google Location History JSON files.")
    return
  }
  val parser = Parser()
  parseRecords(resourcesDir, parser)
  parseSemanticRecords(resourcesDir, parser)
}

private fun parseRecords(resourcesDir: File, parser: Parser) {

  val recordsFile = File(resourcesDir, "Records.json")
  val bufferedSource: BufferedSource = recordsFile.source().buffer()
  val records = parser.parseRecords(bufferedSource)

  println("Got records: ${records.locations.size} records.")
  println()
}

fun parseSemanticRecords(resourcesDir: File, parser: Parser) {
  val directory = File(resourcesDir, "Semantic Location History")

  val semanticYears = directory.listFiles()
  semanticYears?.forEach { yearDirectory ->
    val files = yearDirectory.listFiles()
    files?.forEach {
      // List each files of directory
      println("Parsing file: ${it.name}")
      val bufferedSource: BufferedSource = it.source().buffer()
      val semanticTimeline = parser.parseSemanticTimeline(bufferedSource)

      println("Got timeline items: ${semanticTimeline.timelineObjects.size}")
    }
  }
}
