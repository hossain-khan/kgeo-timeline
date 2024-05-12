package dev.hossain.timeline

import ZonedDateTimeAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.hossain.timeline.model.edits.TimelineEdits
import dev.hossain.timeline.model.record.ActivityType
import dev.hossain.timeline.model.record.LocationRecordSource
import dev.hossain.timeline.model.record.Records
import dev.hossain.timeline.model.semantic.SemanticTimeline
import dev.hossain.timeline.model.settings.Settings
import dev.hossain.timeline.moshi.EnumCustomJsonAdapter
import okio.BufferedSource

/**
 * Parser to parse Google Location Timeline JSON to Kotlin objects.
 *
 * Sample usages for parsing different JSON types:
 * ```kotlin
 * val parser = Parser()
 *
 * // ...
 * val bufferedSourceRecords: BufferedSource = recordsFile.source().buffer()
 * val records = parser.parseRecords(bufferedSourceRecords)
 *
 * // ...
 * val bufferedSourceSemantic: BufferedSource = semanticMonthFile.source().buffer()
 * val semanticTimeline = parser.parseSemanticTimeline(bufferedSourceSemantic)
 * ```
 */
class Parser constructor() {
  // Moshi instance with custom adapter to parse the timeline data.
  private val moshi: Moshi =
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(ZonedDateTimeAdapter())
      .add(
        ActivityType::class.java,
        EnumCustomJsonAdapter.create(ActivityType::class.java).withUnknownFallback(ActivityType.UNKNOWN),
      )
      .add(
        LocationRecordSource::class.java,
        EnumCustomJsonAdapter.create(LocationRecordSource::class.java).withUnknownFallback(
          fallbackValue = LocationRecordSource.UNKNOWN,
          useCaseInsensitiveName = true,
        ),
      )
      .build()

  /**
   * Parse JSON string to [Records] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse records JSON.
   * val parser = Parser()
   * val json: String = File("your-file.json").bufferedReader().readText()
   * val records: Records = parser.parseRecords(json)
   *
   * println("Got records: ${records.locations.size} locations.")
   * ```
   */
  fun parseRecords(json: String): Records {
    val adapter: JsonAdapter<Records> = moshi.adapter(Records::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [Records] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse records JSON.
   * val parser = Parser()
   * val bufferedSource: BufferedSource = File("your-file.json").source().buffer()
   * val records: Records = parser.parseRecords(bufferedSource)
   *
   * println("Got records: ${records.locations.size} locations.")
   * ```
   */
  fun parseRecords(bufferedSource: BufferedSource): Records {
    val adapter: JsonAdapter<Records> = moshi.adapter(Records::class.java)
    return adapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [Settings] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse settings JSON.
   * val parser = Parser()
   * val json: String = File("your-file.json").bufferedReader().readText()
   * val settings: Settings = parser.parseSettings(json)
   * ```
   */
  fun parseSettings(json: String): Settings {
    val adapter: JsonAdapter<Settings> = moshi.adapter(Settings::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [Settings] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse settings JSON.
   * val parser = Parser()
   * val bufferedSource: BufferedSource = File("your-file.json").source().buffer()
   * val settings: Settings = parser.parseSettings(bufferedSource)
   * ```
   */
  fun parseSettings(bufferedSource: BufferedSource): Settings {
    val adapter: JsonAdapter<Settings> = moshi.adapter(Settings::class.java)
    return adapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [SemanticTimeline] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse semantic timeline JSON.
   * val parser = Parser()
   * val json: String = File("your-file.json").bufferedReader().readText()
   * val semanticTimeline: SemanticTimeline = parser.parseSemanticTimeline(json)
   *
   * println("Got semantic timeline: ${semanticTimeline.timelineObjects.size} objects.")
   * ```
   */
  fun parseSemanticTimeline(json: String): SemanticTimeline {
    val adapter: JsonAdapter<SemanticTimeline> = moshi.adapter(SemanticTimeline::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [SemanticTimeline] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse semantic timeline JSON.
   * val parser = Parser()
   * val bufferedSource: BufferedSource = File("your-file.json").source().buffer()
   * val semanticTimeline: SemanticTimeline = parser.parseSemanticTimeline(bufferedSource)
   *
   * println("Got semantic timeline: ${semanticTimeline.timelineObjects.size} objects.")
   * ```
   */
  fun parseSemanticTimeline(bufferedSource: BufferedSource): SemanticTimeline {
    val adapter: JsonAdapter<SemanticTimeline> = moshi.adapter(SemanticTimeline::class.java)
    return adapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [TimelineEdits] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse timeline edits JSON.
   * val parser = Parser()
   * val json: String = File("your-file.json").bufferedReader().readText()
   * val edits: TimelineEdits = parser.parseTimelineEdits(json)
   * ```
   */
  fun parseTimelineEdits(json: String): TimelineEdits {
    val adapter: JsonAdapter<TimelineEdits> = moshi.adapter(TimelineEdits::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [TimelineEdits] object.
   *
   * ```kotlin
   * // Sample usage of parser to parse timeline edits JSON.
   * val parser = Parser()
   * val bufferedSource: BufferedSource = File("your-file.json").source().buffer()
   * val edits: TimelineEdits = parser.parseTimelineEdits(bufferedSource)
   * ```
   */
  fun parseTimelineEdits(bufferedSource: BufferedSource): TimelineEdits {
    val adapter: JsonAdapter<TimelineEdits> = moshi.adapter(TimelineEdits::class.java)
    return adapter.fromJson(bufferedSource)!!
  }
}
