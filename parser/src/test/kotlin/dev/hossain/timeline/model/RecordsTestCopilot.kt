package dev.hossain.timeline.model

import dev.hossain.timeline.model.record.AccessPoint
import dev.hossain.timeline.model.record.Activity
import dev.hossain.timeline.model.record.ActivityRecord
import dev.hossain.timeline.model.record.ActivityType
import dev.hossain.timeline.model.record.LocationMetadata
import dev.hossain.timeline.model.record.LocationRecord
import dev.hossain.timeline.model.record.LocationRecordSource
import dev.hossain.timeline.model.record.Records
import dev.hossain.timeline.model.record.WifiScan
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

/**
 * Tests for [Records] and related classes using copilot.
 */
class RecordsTestCopilot {
  @Test
  fun `Records should contain list of LocationRecord`() {
    val records = Records(listOf())
    assertTrue(records.locations.isEmpty())
  }

  @Test
  fun `LocationRecord should contain valid data`() {
    val locationRecord =
      LocationRecord(
        accuracy = 10,
        activeWifiScan = WifiScan(listOf()),
        activity = listOf(),
        altitude = 100,
        batteryCharging = true,
        deviceDesignation = "PRIMARY",
        deviceTag = 1,
        osLevel = 28,
        heading = 90,
        latitudeE7 = 900000000,
        locationMetadata = listOf(),
        longitudeE7 = 1800000000,
        platformType = "ANDROID",
        source = LocationRecordSource.GPS,
        timestamp = "2022-01-01T00:00:00Z",
        velocity = 10,
        verticalAccuracy = 10,
      )

    assertEquals(10, locationRecord.accuracy)
    assertTrue(locationRecord.activeWifiScan!!.accessPoints.isEmpty())
    assertTrue(locationRecord.activity!!.isEmpty())
    assertEquals(100, locationRecord.altitude)
    assertTrue(locationRecord.batteryCharging!!)
    assertEquals("PRIMARY", locationRecord.deviceDesignation)
    assertEquals(1, locationRecord.deviceTag)
    assertEquals(28, locationRecord.osLevel)
    assertEquals(90, locationRecord.heading)
    assertEquals(900000000, locationRecord.latitudeE7)
    assertTrue(locationRecord.locationMetadata!!.isEmpty())
    assertEquals(1800000000, locationRecord.longitudeE7)
    assertEquals("ANDROID", locationRecord.platformType)
    assertEquals(LocationRecordSource.GPS, locationRecord.source)
    assertEquals("2022-01-01T00:00:00Z", locationRecord.timestamp)
    assertEquals(10, locationRecord.velocity)
    assertEquals(10, locationRecord.verticalAccuracy)
  }

  @Test
  fun `WifiScan should contain list of AccessPoint`() {
    val wifiScan = WifiScan(listOf())
    assertTrue(wifiScan.accessPoints.isEmpty())
  }

  @Test
  fun `AccessPoint should contain valid data`() {
    val accessPoint =
      AccessPoint(
        mac = "00:00:00:00:00:00",
        strength = 100,
        frequencyMhz = 2400,
        isConnected = true,
      )

    assertEquals("00:00:00:00:00:00", accessPoint.mac)
    assertEquals(100, accessPoint.strength)
    assertEquals(2400, accessPoint.frequencyMhz)
    assertTrue(accessPoint.isConnected!!)
  }

  @Test
  fun `LocationMetadata should contain valid data`() {
    val locationMetadata =
      LocationMetadata(
        timestamp = "2022-01-01T00:00:00Z",
        wifiScan = WifiScan(listOf()),
      )

    assertEquals("2022-01-01T00:00:00Z", locationMetadata.timestamp)
    assertTrue(locationMetadata.wifiScan.accessPoints.isEmpty())
  }

  @Test
  fun `ActivityRecord should contain list of Activity`() {
    val activityRecord = ActivityRecord(listOf(), "2022-01-01T00:00:00Z")
    assertTrue(activityRecord.activity.isEmpty())
  }

  @Test
  fun `Activity should contain valid data`() {
    val activity =
      Activity(
        type = ActivityType.WALKING,
        confidence = 100,
      )

    assertEquals(ActivityType.WALKING, activity.type)
    assertEquals(100, activity.confidence)
  }

  @Test
  fun `Activity should contain valid confidence`() {
    val activity = Activity(type = ActivityType.WALKING, confidence = 80)
    assertTrue(activity.confidence in 0..100)
  }
}
