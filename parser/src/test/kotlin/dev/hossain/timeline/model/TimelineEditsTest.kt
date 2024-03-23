package dev.hossain.timeline.model

import com.google.common.truth.Truth
import dev.hossain.timeline.Parser
import kotlin.test.Test

class TimelineEditsTest {
  private val parser = Parser()

  @Test
  fun `given timeline edits json should parse all timeline edits data`() {
    val json = javaClass.getResourceAsStream("/timeline-edits.json")!!.bufferedReader().readText()
    val edits = parser.parseTimelineEdits(json)

    Truth.assertThat(edits.timelineEdits).hasSize(3)
  }
}
