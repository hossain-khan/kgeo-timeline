package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import kotlin.test.Test

/**
 * Test cases for [Settings]
 */
class SemanticTimelineTest {
  private val moshi: Moshi = Moshi.Builder().build()

  @Test
  fun `given records json should parse all records`() {
    val json = javaClass.getResourceAsStream("/semantic-2021-august.json")!!.bufferedReader().readText()
    val timeline = moshi.adapter(SemanticTimeline::class.java).fromJson(json)!!

    assertThat(timeline.timelineObjects).hasSize(125)
  }
}
