package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import dev.hossain.timeline.Parser
import kotlin.test.Test

/**
 * Test cases for [SemanticTimeline].
 */
class SemanticTimelineTest {
  private val parser = Parser()

  @Test
  fun `given semantic timeline json should parse all timeline objects`() {
    val timeline: SemanticTimeline = parser.parseSemanticTimeline(loadJson("/semantic-2021-august.json"))

    assertThat(timeline.timelineObjects).hasSize(125)
  }

  @Test
  fun `given activity segment from semantic timeline json - parsers activity segment data`() {
    val timeline: SemanticTimeline = parser.parseSemanticTimeline(loadJson("/semantic-2021-august.json"))

    val activitySegment = timeline.timelineObjects.first().activitySegment!!
    assertThat(activitySegment.activityType).isEqualTo("IN_PASSENGER_VEHICLE")
    assertThat(activitySegment.startLocation.latitudeE7).isEqualTo(436996461)
    assertThat(activitySegment.startLocation.longitudeE7).isEqualTo(-798555542)
    assertThat(activitySegment.activities).hasSize(15)
    assertThat(activitySegment.confidence).isEqualTo("HIGH")
  }

  @Test
  fun `given place visit from semantic timeline json - parsers place visit data`() {
    val timeline: SemanticTimeline = parser.parseSemanticTimeline(loadJson("/semantic-2021-august.json"))

    val placeVisit = timeline.timelineObjects[1].placeVisit!!
    assertThat(placeVisit.location.latitudeE7).isEqualTo(438865167)
    assertThat(placeVisit.location.longitudeE7).isEqualTo(-790179014)
    assertThat(placeVisit.location.address).isEqualTo("6 Rumbellow Cres, Ajax, ON L1T 0J9, Canada")

    assertThat(placeVisit.visitConfidence).isEqualTo(85)
    assertThat(placeVisit.placeVisitImportance).isEqualTo("MAIN")
    assertThat(placeVisit.placeVisitLevel).isNull()
    assertThat(placeVisit.childVisits).isNull()
    assertThat(placeVisit.sectionId).isNull()

    assertThat(placeVisit.duration!!.startTimestamp.toString()).isEqualTo("2021-08-01T01:22:24Z")
    assertThat(placeVisit.duration!!.endTimestamp.toString()).isEqualTo("2021-08-01T01:25:36Z")
    assertThat(placeVisit.placeConfidence).isEqualTo("HIGH_CONFIDENCE")
  }

  /**
   * Parses the given JSON file and returns [SemanticTimeline] object.
   */
  private fun loadJson(jsonFile: String): String {
    return javaClass.getResourceAsStream(jsonFile)!!.bufferedReader().readText()
  }
}
