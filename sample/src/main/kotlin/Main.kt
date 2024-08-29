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
  if (!resourcesDir.exists() || !resourcesDir.isDirectory) {
    println("Resources directory not found. Please create `sample/src/main/resources` and put your Google Location History JSON files.")
    return
  }
  val parser = Parser()
  parseRecords(resourcesDir, parser)
  parseSemanticRecords(resourcesDir, parser)
  parseTimelineEdits(resourcesDir, parser)
}

private fun parseRecords(resourcesDir: File, parser: Parser) {

  val recordsFile = File(resourcesDir, "Records.json")
  if (recordsFile.exists().not()) {
    println("ℹ\uFE0F Records file not found.")
    return
  }
  val bufferedSource: BufferedSource = recordsFile.source().buffer()
  val records = parser.parseRecords(bufferedSource)

  println("\n\uD83D\uDFE2 Got records: ${records.locations.size} records.")
  println()
}

/**
 * Parse semantic location history files.
 */
fun parseSemanticRecords(resourcesDir: File, parser: Parser) {
  val directory = File(resourcesDir, "Semantic Location History")

  val dataMap = mutableMapOf<String, List<String>>()

  val semanticYears = directory.listFiles()

  if (semanticYears.isNullOrEmpty()) {
    println("ℹ\uFE0F No semantic location history data found.")
    return
  }

  // Loop through each year directory
  semanticYears.forEach { yearDirectory ->
    val files = yearDirectory.listFiles()
    files?.forEach {
      // List each files of directory
      val bufferedSource: BufferedSource = it.source().buffer()
      val semanticTimeline = parser.parseSemanticTimeline(bufferedSource)

      if (dataMap.containsKey(yearDirectory.name)) {
        dataMap[yearDirectory.name] = dataMap[yearDirectory.name]!!.plus(
          "${
            it.name.removeSurrounding(
              "${yearDirectory.name}_",
              ".json"
            )
          }: ${semanticTimeline.timelineObjects.size}"
        )
      } else {
        dataMap[yearDirectory.name] = listOf(
          "${
            it.name.removeSurrounding(
              "${yearDirectory.name}_",
              ".json"
            )
          }: ${semanticTimeline.timelineObjects.size}"
        )
      }
    }
  }

  println("\n\uD83D\uDFE2 Got semantic location history data:\n${dataMap.entries.joinToString("\n")}")
}

fun parseTimelineEdits(resourcesDir: File, parser: Parser) {
  val timelineEditsFile = File(resourcesDir, "Timeline Edits.json")
  val bufferedSource: BufferedSource = timelineEditsFile.source().buffer()
  val timelineEdits = parser.parseTimelineEdits(bufferedSource)

  println("\n\uD83D\uDFE2 Got timeline edits: ${timelineEdits.items.size} items edited.")
}
