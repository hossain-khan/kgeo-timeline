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
 * This class provides efficient parsing of Google Takeout Location History data
 * by caching JsonAdapter instances to minimize reflection overhead on repeated parsing calls.
 *
 * ## Performance Characteristics:
 * - JsonAdapter instances are cached and reused for optimal performance
 * - Thread-safe: Multiple threads can safely use the same Parser instance
 * - Memory efficient: Single Moshi instance with pre-configured adapters
 *
 * ## Usage Recommendations:
 * - Reuse Parser instances when parsing multiple files of the same type
 * - Use BufferedSource overloads for better I/O performance with large files
 * - Consider using a single Parser instance across your application
 *
 * ## Exception Handling:
 * - Throws JsonDataException for malformed JSON or missing required fields
 * - Returns non-null objects; null JSON input will throw NullPointerException
 *
 * Sample usages for parsing different JSON types:
 * ```kotlin
 * val parser = Parser()
 *
 * // Efficient for parsing multiple files
 * val bufferedSourceRecords: BufferedSource = recordsFile.source().buffer()
 * val records = parser.parseRecords(bufferedSourceRecords)
 *
 * // Reuse the same parser instance
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

  // Cached JsonAdapter instances to avoid repeated reflection overhead
  private val recordsAdapter: JsonAdapter<Records> by lazy { moshi.adapter(Records::class.java) }
  private val settingsAdapter: JsonAdapter<Settings> by lazy { moshi.adapter(Settings::class.java) }
  private val semanticTimelineAdapter: JsonAdapter<SemanticTimeline> by lazy {
    moshi.adapter(SemanticTimeline::class.java)
  }
  private val timelineEditsAdapter: JsonAdapter<TimelineEdits> by lazy { moshi.adapter(TimelineEdits::class.java) }

  /**
   * Parse JSON string to [Records] object.
   *
   * This method efficiently parses Google Takeout Records.json data using cached adapters
   * for optimal performance on repeated calls.
   *
   * @param json The JSON string containing Records data
   * @return Parsed [Records] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if json parameter is null
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
    return recordsAdapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [Records] object.
   *
   * This method efficiently parses Google Takeout Records.json data using cached adapters
   * and is recommended for large files as it provides better I/O performance.
   *
   * @param bufferedSource The BufferedSource containing Records JSON data
   * @return Parsed [Records] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if bufferedSource parameter is null
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
    return recordsAdapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [Settings] object.
   *
   * This method efficiently parses Google Takeout Settings.json data using cached adapters
   * for optimal performance on repeated calls.
   *
   * @param json The JSON string containing Settings data
   * @return Parsed [Settings] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if json parameter is null
   *
   * ```kotlin
   * // Sample usage of parser to parse settings JSON.
   * val parser = Parser()
   * val json: String = File("your-file.json").bufferedReader().readText()
   * val settings: Settings = parser.parseSettings(json)
   * ```
   */
  fun parseSettings(json: String): Settings {
    return settingsAdapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [Settings] object.
   *
   * This method efficiently parses Google Takeout Settings.json data using cached adapters
   * and is recommended for large files as it provides better I/O performance.
   *
   * @param bufferedSource The BufferedSource containing Settings JSON data
   * @return Parsed [Settings] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if bufferedSource parameter is null
   *
   * ```kotlin
   * // Sample usage of parser to parse settings JSON.
   * val parser = Parser()
   * val bufferedSource: BufferedSource = File("your-file.json").source().buffer()
   * val settings: Settings = parser.parseSettings(bufferedSource)
   * ```
   */
  fun parseSettings(bufferedSource: BufferedSource): Settings {
    return settingsAdapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [SemanticTimeline] object.
   *
   * This method efficiently parses Google Takeout Semantic Location History JSON data using cached adapters
   * for optimal performance on repeated calls.
   *
   * @param json The JSON string containing SemanticTimeline data
   * @return Parsed [SemanticTimeline] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if json parameter is null
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
    return semanticTimelineAdapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [SemanticTimeline] object.
   *
   * This method efficiently parses Google Takeout Semantic Location History JSON data using cached adapters
   * and is recommended for large files as it provides better I/O performance.
   *
   * @param bufferedSource The BufferedSource containing SemanticTimeline JSON data
   * @return Parsed [SemanticTimeline] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if bufferedSource parameter is null
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
    return semanticTimelineAdapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [TimelineEdits] object.
   *
   * This method efficiently parses Google Takeout Timeline Edits JSON data using cached adapters
   * for optimal performance on repeated calls.
   *
   * @param json The JSON string containing TimelineEdits data
   * @return Parsed [TimelineEdits] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if json parameter is null
   *
   * ```kotlin
   * // Sample usage of parser to parse timeline edits JSON.
   * val parser = Parser()
   * val json: String = File("your-file.json").bufferedReader().readText()
   * val edits: TimelineEdits = parser.parseTimelineEdits(json)
   * ```
   */
  fun parseTimelineEdits(json: String): TimelineEdits {
    return timelineEditsAdapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [TimelineEdits] object.
   *
   * This method efficiently parses Google Takeout Timeline Edits JSON data using cached adapters
   * and is recommended for large files as it provides better I/O performance.
   *
   * @param bufferedSource The BufferedSource containing TimelineEdits JSON data
   * @return Parsed [TimelineEdits] object
   * @throws JsonDataException if the JSON is malformed or missing required fields
   * @throws NullPointerException if bufferedSource parameter is null
   *
   * ```kotlin
   * // Sample usage of parser to parse timeline edits JSON.
   * val parser = Parser()
   * val bufferedSource: BufferedSource = File("your-file.json").source().buffer()
   * val edits: TimelineEdits = parser.parseTimelineEdits(bufferedSource)
   * ```
   */
  fun parseTimelineEdits(bufferedSource: BufferedSource): TimelineEdits {
    return timelineEditsAdapter.fromJson(bufferedSource)!!
  }
}
