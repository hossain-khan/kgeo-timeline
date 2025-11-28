package dev.hossain.timeline

import com.google.common.truth.Truth.assertThat
import okio.buffer
import okio.source
import kotlin.test.Test

/**
 * Tests for [Parser] methods that accept [okio.BufferedSource] for efficient I/O parsing.
 * These tests validate that BufferedSource overloads produce the same results as String overloads.
 */
class ParserBufferedSourceTest {
  private val parser = Parser()

  @Test
  fun `parseRecords with BufferedSource should parse all records`() {
    val bufferedSource = javaClass.getResourceAsStream("/records.json")!!.source().buffer()

    val records = parser.parseRecords(bufferedSource)

    assertThat(records.locations).hasSize(12)
  }

  @Test
  fun `parseRecords with BufferedSource should match String parsing result`() {
    val json = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val bufferedSource = javaClass.getResourceAsStream("/records.json")!!.source().buffer()

    val recordsFromString = parser.parseRecords(json)
    val recordsFromSource = parser.parseRecords(bufferedSource)

    assertThat(recordsFromSource.locations).hasSize(recordsFromString.locations.size)
    assertThat(recordsFromSource.locations.first().timestamp)
      .isEqualTo(recordsFromString.locations.first().timestamp)
  }

  @Test
  fun `parseSettings with BufferedSource should parse all settings`() {
    val bufferedSource = javaClass.getResourceAsStream("/settings.json")!!.source().buffer()

    val settings = parser.parseSettings(bufferedSource)

    assertThat(settings.deviceSettings).hasSize(4)
    assertThat(settings.createdTime).isEqualTo("2013-08-10T18:07:41.251Z")
  }

  @Test
  fun `parseSettings with BufferedSource should match String parsing result`() {
    val json = javaClass.getResourceAsStream("/settings.json")!!.bufferedReader().readText()
    val bufferedSource = javaClass.getResourceAsStream("/settings.json")!!.source().buffer()

    val settingsFromString = parser.parseSettings(json)
    val settingsFromSource = parser.parseSettings(bufferedSource)

    assertThat(settingsFromSource.deviceSettings).hasSize(settingsFromString.deviceSettings.size)
    assertThat(settingsFromSource.createdTime).isEqualTo(settingsFromString.createdTime)
  }

  @Test
  fun `parseSemanticTimeline with BufferedSource should parse all timeline objects`() {
    val bufferedSource = javaClass.getResourceAsStream("/semantic-2021-august.json")!!.source().buffer()

    val timeline = parser.parseSemanticTimeline(bufferedSource)

    assertThat(timeline.timelineObjects).hasSize(125)
  }

  @Test
  fun `parseSemanticTimeline with BufferedSource should match String parsing result`() {
    val json = javaClass.getResourceAsStream("/semantic-2021-august.json")!!.bufferedReader().readText()
    val bufferedSource = javaClass.getResourceAsStream("/semantic-2021-august.json")!!.source().buffer()

    val timelineFromString = parser.parseSemanticTimeline(json)
    val timelineFromSource = parser.parseSemanticTimeline(bufferedSource)

    assertThat(timelineFromSource.timelineObjects).hasSize(timelineFromString.timelineObjects.size)
  }

  @Test
  fun `parseTimelineEdits with BufferedSource should parse all edits`() {
    val bufferedSource = javaClass.getResourceAsStream("/timeline-edits.json")!!.source().buffer()

    val edits = parser.parseTimelineEdits(bufferedSource)

    assertThat(edits.items).hasSize(3)
  }

  @Test
  fun `parseTimelineEdits with BufferedSource should match String parsing result`() {
    val json = javaClass.getResourceAsStream("/timeline-edits.json")!!.bufferedReader().readText()
    val bufferedSource = javaClass.getResourceAsStream("/timeline-edits.json")!!.source().buffer()

    val editsFromString = parser.parseTimelineEdits(json)
    val editsFromSource = parser.parseTimelineEdits(bufferedSource)

    assertThat(editsFromSource.items).hasSize(editsFromString.items.size)
    assertThat(editsFromSource.items.first().deviceId).isEqualTo(editsFromString.items.first().deviceId)
  }
}
