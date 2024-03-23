package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import dev.hossain.timeline.Parser
import dev.hossain.timeline.model.record.ActivityType
import dev.hossain.timeline.model.record.Records
import kotlin.test.Test

/**
 * Test cases for [Records].
 */
class RecordsTest {
  private val parser = Parser()

  @Test
  fun `given records json should parse all records`() {
    val json = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val records: Records = parser.parseRecords(json)

    assertThat(records.locations).hasSize(12)
  }

  @Test
  fun `given first record should parse all fields`() {
    val json = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val records: Records = parser.parseRecords(json)

    val firstRecord = records.locations.first()
    assertThat(firstRecord.timestamp).isEqualTo("2015-10-10T18:47:02.597Z")
    assertThat(firstRecord.latitudeE7).isEqualTo(437789954)
    assertThat(firstRecord.longitudeE7).isEqualTo(-794176419)
    assertThat(firstRecord.accuracy).isEqualTo(30)
    assertThat(firstRecord.activity).hasSize(2)

    val firstActivity = firstRecord.activity!!.first()
    assertThat(firstActivity.activity).hasSize(3)
    assertThat(firstActivity.activity.first().type).isEqualTo(ActivityType.UNKNOWN)
    assertThat(firstActivity.activity.first().confidence).isEqualTo(58)
    assertThat(firstActivity.timestamp).isEqualTo("2015-10-10T18:47:31.321Z")
  }

  @Test
  fun `given last record should parse all fields`() {
    val json = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val records = parser.parseRecords(json)

    val lastRecord = records.locations.last()
    assertThat(lastRecord.timestamp).isEqualTo("2015-10-10T18:58:40.399Z")
    assertThat(lastRecord.latitudeE7).isEqualTo(437790309)
    assertThat(lastRecord.longitudeE7).isEqualTo(-794175893)
    assertThat(lastRecord.accuracy).isEqualTo(33)
    assertThat(lastRecord.activity).hasSize(3)

    val lastActivity = lastRecord.activity!!.last()
    assertThat(lastActivity.activity).hasSize(1)
    assertThat(lastActivity.activity.last().type).isEqualTo(ActivityType.STILL)
    assertThat(lastActivity.activity.last().confidence).isEqualTo(100)
    assertThat(lastActivity.timestamp).isEqualTo("2015-10-10T18:57:39.508Z")
  }
}
