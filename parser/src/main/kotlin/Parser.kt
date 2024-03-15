package dev.hossain.timeline

import ZonedDateTimeAdapter
import com.squareup.moshi.Moshi
import dev.hossain.timeline.model.Records
import dev.hossain.timeline.model.SemanticTimeline
import dev.hossain.timeline.model.Settings

/**
 * Parser to parse Google Location Timeline JSON to Kotlin objects.
 */
class Parser constructor() {
  // Moshi instance with custom adapter to parse the timeline data.
  private val moshi: Moshi =
    Moshi.Builder()
      .add(ZonedDateTimeAdapter())
      .build()

  /**
   * Parse JSON string to [Records] object.
   */
  fun parseRecords(json: String): Records {
    val adapter = moshi.adapter(Records::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON string to [Settings] object.
   */
  fun parseSettings(json: String): Settings {
    val adapter = moshi.adapter(Settings::class.java)
    return adapter.fromJson(json)!!
  }

  /**
   * Parse JSON string to [SemanticTimeline] object.
   */
  fun parseSemanticTimeline(json: String): SemanticTimeline {
    val adapter = moshi.adapter(SemanticTimeline::class.java)
    return adapter.fromJson(json)!!
  }
}
