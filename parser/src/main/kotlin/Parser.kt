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
   */
  fun parseRecords(json: String): Records {
    val adapter: JsonAdapter<Records> = moshi.adapter(Records::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [Records] object.
   */
  fun parseRecords(bufferedSource: BufferedSource): Records {
    val adapter: JsonAdapter<Records> = moshi.adapter(Records::class.java)
    return adapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [Settings] object.
   */
  fun parseSettings(json: String): Settings {
    val adapter: JsonAdapter<Settings> = moshi.adapter(Settings::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [Settings] object.
   */
  fun parseSettings(bufferedSource: BufferedSource): Settings {
    val adapter: JsonAdapter<Settings> = moshi.adapter(Settings::class.java)
    return adapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [SemanticTimeline] object.
   */
  fun parseSemanticTimeline(json: String): SemanticTimeline {
    val adapter: JsonAdapter<SemanticTimeline> = moshi.adapter(SemanticTimeline::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [SemanticTimeline] object.
   */
  fun parseSemanticTimeline(bufferedSource: BufferedSource): SemanticTimeline {
    val adapter: JsonAdapter<SemanticTimeline> = moshi.adapter(SemanticTimeline::class.java)
    return adapter.fromJson(bufferedSource)!!
  }

  /**
   * Parse JSON string to [TimelineEdits] object.
   */
  fun parseTimelineEdits(json: String): TimelineEdits {
    val adapter: JsonAdapter<TimelineEdits> = moshi.adapter(TimelineEdits::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON buffered source to [TimelineEdits] object.
   */
  fun parseTimelineEdits(bufferedSource: BufferedSource): TimelineEdits {
    val adapter: JsonAdapter<TimelineEdits> = moshi.adapter(TimelineEdits::class.java)
    return adapter.fromJson(bufferedSource)!!
  }
}
