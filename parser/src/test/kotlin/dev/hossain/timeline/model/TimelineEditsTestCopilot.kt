package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonEncodingException
import dev.hossain.timeline.Parser
import dev.hossain.timeline.model.edits.Point
import dev.hossain.timeline.model.edits.TimelineEdits
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class TimelineEditsTestCopilot {
  private val parser = Parser()

  @Test
  fun `should parse empty timeline items given empty json`() {
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
  fun `parse to empty items when json is empty`() {
    val json = "{}"
    val timelineEdits = parser.parseTimelineEdits(json)

    assertThat(timelineEdits.items).isEmpty()
  }

  @Test
  fun `should throw exception when json is not valid`() {
    val json = "invalid json"
    val error =
      assertThrows<JsonEncodingException> {
        parser.parseTimelineEdits(json)
      }
    assertThat(error).isInstanceOf(JsonEncodingException::class.java)
  }

  @Test
  fun `should parse timeline edits with correct device id when json contains single edit`() {
    val json = """
        {
            "timelineEdits": [
                {
                    "deviceId": "1",
                    "placeAggregates": {
                        "placeAggregateInfo": [],
                        "windowSizeHrs": 0,
                        "topRankedPlacesPlaceIds": [],
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

    assertThat(edits.items.first().deviceId).isEqualTo("1")
  }

  @Test
  fun `should parse timeline edits with aggregates when json contains single edit with single place aggregate`() {
    val json = """
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
    val firstPlaceAggregate = firstEdit.placeAggregates!!.placeAggregateInfo.first()
    assertThat(firstPlaceAggregate.score).isEqualTo(5.0)
    assertThat(firstPlaceAggregate.numBucketsWithLocation).isEqualTo(14)
    assertThat(firstPlaceAggregate.bucketSpanDays).isEqualTo(4)
    assertThat(firstPlaceAggregate.point).isEqualTo(Point(latE7 = 439406551, lngE7 = -788458768))
    assertThat(firstPlaceAggregate.placeId).isEqualTo("ChIJaWUW8E4b1YkRLPJRTVf0RTw")
    assertThat(firstPlaceAggregate.placePoint).isEqualTo(Point(latE7 = 439405376, lngE7 = -788457340))
  }
}
