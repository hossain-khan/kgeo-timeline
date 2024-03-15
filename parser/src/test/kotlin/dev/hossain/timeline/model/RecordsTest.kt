package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import dev.hossain.timeline.Parser
import kotlin.test.Test

/**
 * Test cases for [Settings].
 */
class RecordsTest {
  private val parser = Parser()

  @Test
  fun `given records json should parse all records`() {
    val json = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val records = parser.parseRecords(json)

    assertThat(records.locations).hasSize(12)
  }
}
