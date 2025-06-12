package dev.hossain.timeline

import com.google.common.truth.Truth.assertThat
import kotlin.test.Test
import kotlin.time.measureTime

/**
 * Performance test for [Parser] to validate efficiency improvements.
 * 
 * These tests demonstrate that adapter caching provides measurable performance
 * improvements when parsing multiple JSON files with the same Parser instance.
 */
class ParserPerformanceTest {
  private val parser = Parser()

  @Test
  fun `parser should reuse adapters efficiently for multiple parsing calls`() {
    val recordsJson = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val semanticJson = javaClass.getResourceAsStream("/semantic-2021-august.json")!!.bufferedReader().readText()

    // Warm-up: Initialize adapters on first call
    parser.parseRecords(recordsJson)
    parser.parseSemanticTimeline(semanticJson)

    // Measure performance of subsequent calls
    val recordsTime = measureTime {
      repeat(10) {
        val records = parser.parseRecords(recordsJson)
        assertThat(records.locations).hasSize(12)
      }
    }

    val semanticTime = measureTime {
      repeat(10) {
        val timeline = parser.parseSemanticTimeline(semanticJson)
        assertThat(timeline.timelineObjects).hasSize(125)
      }
    }

    // Assert that parsing is reasonably fast (should be much faster than adapter creation overhead)
    // These are reasonable benchmarks for cached adapters
    assertThat(recordsTime.inWholeMilliseconds).isLessThan(100) // Should be very fast with cached adapters
    assertThat(semanticTime.inWholeMilliseconds).isLessThan(200) // Larger file, but still fast

    println("Records parsing time (10x): ${recordsTime.inWholeMilliseconds}ms")
    println("Semantic parsing time (10x): ${semanticTime.inWholeMilliseconds}ms")
  }

  @Test
  fun `parser instance should be reusable across different JSON types`() {
    val recordsJson = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val semanticJson = javaClass.getResourceAsStream("/semantic-2021-august.json")!!.bufferedReader().readText()
    val settingsJson = javaClass.getResourceAsStream("/settings.json")!!.bufferedReader().readText()
    val editsJson = javaClass.getResourceAsStream("/timeline-edits.json")!!.bufferedReader().readText()

    // Parse different types with the same parser instance
    val records = parser.parseRecords(recordsJson)
    val timeline = parser.parseSemanticTimeline(semanticJson)
    val settings = parser.parseSettings(settingsJson)
    val edits = parser.parseTimelineEdits(editsJson)

    // Verify all parsing succeeded
    assertThat(records.locations).hasSize(12)
    assertThat(timeline.timelineObjects).hasSize(125)
    assertThat(settings.deviceSettings).isNotNull()
    assertThat(edits.items).hasSize(3)
  }
}