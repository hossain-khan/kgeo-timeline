package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import dev.hossain.timeline.Parser
import dev.hossain.timeline.model.timeline.Point
import kotlin.test.Test

class TimelineEditsTest {
  private val parser = Parser()

  @Test
  fun `given timeline edits json should parse all timeline edits data`() {
    val json = javaClass.getResourceAsStream("/timeline-edits.json")!!.bufferedReader().readText()
    val edits = parser.parseTimelineEdits(json)

    assertThat(edits.timelineEdits).hasSize(3)
  }

  @Test
  fun `given first timeline edit should parse all fields`() {
    val json = javaClass.getResourceAsStream("/timeline-edits.json")!!.bufferedReader().readText()
    val edits = parser.parseTimelineEdits(json)

    val firstEdit = edits.timelineEdits.first()
    assertThat(firstEdit.deviceId).isEqualTo("0")
    assertThat(firstEdit.placeAggregates.placeAggregateInfo).hasSize(3)
    assertThat(firstEdit.placeAggregates.placeAggregateInfo.first().placeId).isEqualTo("ChIJaWUW8E4b1YkRLPJRTVf0RTw")
    assertThat(firstEdit.placeAggregates.placeAggregateInfo.first().placePoint)
      .isEqualTo(Point(latE7 = 439405376, lngE7 = -788457340))
    assertThat(firstEdit.placeAggregates.placeAggregateInfo.first().point)
      .isEqualTo(Point(latE7 = 439406551, lngE7 = -788458768))
    assertThat(firstEdit.placeAggregates.placeAggregateInfo.first().score).isEqualTo(5.0)
    assertThat(firstEdit.placeAggregates.placeAggregateInfo.first().bucketSpanDays).isEqualTo(4)
    assertThat(firstEdit.placeAggregates.placeAggregateInfo.first().numBucketsWithLocation).isEqualTo(14)
    assertThat(firstEdit.placeAggregates.windowSizeHrs).isEqualTo(2016)
    assertThat(firstEdit.placeAggregates.topRankedPlacesPlaceIds).hasSize(3)
    assertThat(firstEdit.placeAggregates.topRankedPlacesPlaceIds.first()).isEqualTo("ChIJV8SII64E1YkRvAqrnP5G_x8")
    assertThat(firstEdit.placeAggregates.processWindow.startTime).isEqualTo("2023-09-20T08:01:15Z")
    assertThat(firstEdit.placeAggregates.processWindow.endTime).isEqualTo("2023-12-13T08:01:15Z")
  }
}
