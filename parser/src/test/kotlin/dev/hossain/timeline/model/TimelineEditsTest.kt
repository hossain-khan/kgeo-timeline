package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonEncodingException
import dev.hossain.timeline.Parser
import dev.hossain.timeline.model.edits.Point
import dev.hossain.timeline.model.edits.TimelineEdits
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

/**
 * Test cases for [TimelineEdits] JSON parsing and validation.
 */
class TimelineEditsTest {
  private val parser = Parser()

  @Test
  fun `given timeline edits json should parse all timeline edits data`() {
    val json = javaClass.getResourceAsStream("/timeline-edits.json")!!.bufferedReader().readText()
    val edits: TimelineEdits = parser.parseTimelineEdits(json)

    assertThat(edits.items).hasSize(3)
  }

  @Test
  fun `given first timeline edit should parse all fields`() {
    val json = javaClass.getResourceAsStream("/timeline-edits.json")!!.bufferedReader().readText()
    val edits: TimelineEdits = parser.parseTimelineEdits(json)

    val firstEdit = edits.items.first()
    assertThat(firstEdit.deviceId).isEqualTo("0")
    assertThat(firstEdit.placeAggregates!!.placeAggregateInfo).hasSize(3)
    assertThat(firstEdit.placeAggregates!!.placeAggregateInfo.first().placeId).isEqualTo("ChIJaWUW8E4b1YkRLPJRTVf0RTw")
    assertThat(firstEdit.placeAggregates!!.placeAggregateInfo.first().placePoint)
      .isEqualTo(Point(latE7 = 439405376, lngE7 = -788457340))
    assertThat(firstEdit.placeAggregates!!.placeAggregateInfo.first().point)
      .isEqualTo(Point(latE7 = 439406551, lngE7 = -788458768))
    assertThat(firstEdit.placeAggregates!!.placeAggregateInfo.first().score).isEqualTo(5.0)
    assertThat(firstEdit.placeAggregates!!.placeAggregateInfo.first().bucketSpanDays).isEqualTo(4)
    assertThat(firstEdit.placeAggregates!!.placeAggregateInfo.first().numBucketsWithLocation).isEqualTo(14)
    assertThat(firstEdit.placeAggregates!!.windowSizeHrs).isEqualTo(2016)
    assertThat(firstEdit.placeAggregates!!.topRankedPlacesPlaceIds).hasSize(3)
    assertThat(firstEdit.placeAggregates!!.topRankedPlacesPlaceIds.first()).isEqualTo("ChIJV8SII64E1YkRvAqrnP5G_x8")
    assertThat(firstEdit.placeAggregates!!.processWindow.startTime).isEqualTo("2023-09-20T08:01:15Z")
    assertThat(firstEdit.placeAggregates!!.processWindow.endTime).isEqualTo("2023-12-13T08:01:15Z")
  }

  @Test
  fun `given empty timeline edits json should parse to empty list`() {
    val json =
      """
      {
          "timelineEdits": []
      }
      """.trimIndent()
    val edits: TimelineEdits = parser.parseTimelineEdits(json)

    assertThat(edits.items).isEmpty()
  }

  @Test
  fun `given empty json object should parse to empty items list`() {
    val json = "{}"
    val timelineEdits = parser.parseTimelineEdits(json)

    assertThat(timelineEdits.items).isEmpty()
  }

  @Test
  fun `given invalid json should throw exception`() {
    val json = "invalid json"
    val error =
      assertThrows<JsonEncodingException> {
        parser.parseTimelineEdits(json)
      }

    assertThat(error).isInstanceOf(JsonEncodingException::class.java)
  }

  @Test
  fun `given single timeline edit should parse device id and aggregates`() {
    val json =
      """
      {
          "timelineEdits": [
              {
                  "deviceId": "1",
                  "placeAggregates": {
                      "placeAggregateInfo": [
                          {
                              "score": 5.0,
                              "numBucketsWithLocation": 14,
                              "bucketSpanDays": 4,
                              "point": {
                                  "latE7": 439406551,
                                  "lngE7": -788458768
                              },
                              "placeId": "ChIJaWUW8E4b1YkRLPJRTVf0RTw",
                              "placePoint": {
                                  "latE7": 439405376,
                                  "lngE7": -788457340
                              }
                          }
                      ],
                      "windowSizeHrs": 2016,
                      "topRankedPlacesPlaceIds": ["ChIJV8SII64E1YkRvAqrnP5G_x8"],
                      "processWindow": {
                          "startTime": "2023-09-20T08:01:15Z",
                          "endTime": "2023-12-13T08:01:15Z"
                      },
                      "metadata": {
                          "platform": "android"
                      }
                  }
              }
          ]
      }
      """
    val edits: TimelineEdits = parser.parseTimelineEdits(json)

    val firstEdit = edits.items.first()
    assertThat(firstEdit.deviceId).isEqualTo("1")

    val firstPlaceAggregate = firstEdit.placeAggregates!!.placeAggregateInfo.first()
    assertThat(firstPlaceAggregate.score).isEqualTo(5.0)
    assertThat(firstPlaceAggregate.numBucketsWithLocation).isEqualTo(14)
    assertThat(firstPlaceAggregate.bucketSpanDays).isEqualTo(4)
    assertThat(firstPlaceAggregate.point).isEqualTo(Point(latE7 = 439406551, lngE7 = -788458768))
    assertThat(firstPlaceAggregate.placeId).isEqualTo("ChIJaWUW8E4b1YkRLPJRTVf0RTw")
    assertThat(firstPlaceAggregate.placePoint).isEqualTo(Point(latE7 = 439405376, lngE7 = -788457340))
  }
}
