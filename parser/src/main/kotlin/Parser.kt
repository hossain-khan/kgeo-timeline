package dev.hossain.timeline

import ZonedDateTimeAdapter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.EnumJsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dev.hossain.timeline.model.ActivityType
import dev.hossain.timeline.model.Records
import dev.hossain.timeline.model.SemanticTimeline
import dev.hossain.timeline.model.Settings
import dev.hossain.timeline.model.timeline.TimelineEdits
import okio.BufferedSource

/**
 * Parser to parse Google Location Timeline JSON to Kotlin objects.
 */
class Parser constructor() {
  // Moshi instance with custom adapter to parse the timeline data.
  private val moshi: Moshi =
    Moshi.Builder()
      .add(KotlinJsonAdapterFactory())
      .add(ZonedDateTimeAdapter())
      .add(
        ActivityType::class.java,
        EnumJsonAdapter.create(ActivityType::class.java).withUnknownFallback(ActivityType.UNKNOWN),
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
