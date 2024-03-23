package dev.hossain.timeline.model.edits

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * List of all timeline edits.
 */
@JsonClass(generateAdapter = true)
data class TimelineEdits(
  /**
   * Timeline Edits
   */
  @Json(name = "timelineEdits")
  val timelineEdits: List<TimelineEditsItem>,
)

/**
 * Various information about the timeline edit, including device ID, place aggregates, and raw signal data.
 */
@JsonClass(generateAdapter = true)
data class TimelineEditsItem(
  /**
   * The unique identifier for the device that generated the data.
   */
  @Json(name = "deviceId")
  val deviceId: String,
  /**
   * Aggregated information about places visited by the device.
   */
  @Json(name = "placeAggregates")
  val placeAggregates: PlaceAggregates,
)

@JsonClass(generateAdapter = true)
data class PlaceAggregates(
  /**
   * An array of place information, including score, location, and place ID.
   */
  @Json(name = "placeAggregateInfo")
  val placeAggregateInfo: List<PlaceAggregateInfoItem>,
  /**
   * The size of the window in hours for the place aggregates.
   */
  @Json(name = "windowSizeHrs")
  val windowSizeHrs: Int,
  /**
   * An array of place IDs for the top-ranked places.
   */
  @Json(name = "topRankedPlacesPlaceIds")
  val topRankedPlacesPlaceIds: List<String>,
  /**
   * The start and end time of the processing window.
   */
  @Json(name = "processWindow")
  val processWindow: ProcessWindow,
  /**
   * Metadata associated with the place aggregates.
   */
  @Json(name = "metadata")
  val metadata: Metadata,
)

@JsonClass(generateAdapter = true)
data class PlaceAggregateInfoItem(
  /**
   * The score assigned to the place.
   */
  @Json(name = "score")
  val score: Double,
  /**
   * The number of location buckets associated with the place.
   */
  @Json(name = "numBucketsWithLocation")
  val numBucketsWithLocation: Int,
  /**
   * The span of the buckets in days.
   */
  @Json(name = "bucketSpanDays")
  val bucketSpanDays: Int,
  /**
   * The geographical point associated with the place.
   */
  @Json(name = "point")
  val point: Point,
  /**
   * The unique identifier for the place.
   */
  @Json(name = "placeId")
  val placeId: String,
  /**
   * The geographical point of the place.
   */
  @Json(name = "placePoint")
  val placePoint: Point,
)

@JsonClass(generateAdapter = true)
data class ProcessWindow(
  /**
   * The start time of the processing window.
   */
  @Json(name = "startTime")
  val startTime: String,
  /**
   * The end time of the processing window.
   */
  @Json(name = "endTime")
  val endTime: String,
)

/**
 * The signal data, including position, activity record, and wifi scan.
 */
@JsonClass(generateAdapter = true)
data class Signal(
  /**
   * The position data, including point, accuracy, altitude, source, and timestamp.
   */
  @Json(name = "position")
  val position: Position,
  /**
   * The activity record data, including detected activities and timestamp.
   */
  @Json(name = "activityRecord")
  val activityRecord: ActivityRecord,
  /**
   * The wifi scan data, including delivery time, devices, and source.
   */
  @Json(name = "wifiScan")
  val wifiScan: WifiScan,
)

@JsonClass(generateAdapter = true)
data class Position(
  /**
   * The geographical point associated with the position.
   */
  @Json(name = "point")
  val point: Point,
  /**
   * The accuracy of the position in millimeters.
   */
  @Json(name = "accuracyMm")
  val accuracyMm: Int,
  /**
   * The altitude of the position in meters.
   */
  @Json(name = "altitudeMeters")
  val altitudeMeters: Double,
  /**
   * The source of the position data.
   */
  @Json(name = "source")
  val source: String,
  /**
   * Speed in meters per second.
   */
  @Json(name = "speedMetersPerSecond")
  val speedMetersPerSecond: Double,
  /**
   * The timestamp of the position data.
   */
  @Json(name = "timestamp")
  val timestamp: String,
)

@JsonClass(generateAdapter = true)
data class ActivityRecord(
  /**
   * An array of detected activities, including activity type and probability.
   */
  @Json(name = "detectedActivities")
  val detectedActivities: List<DetectedActivityItem>,
  /**
   * The timestamp of the activity record.
   */
  @Json(name = "timestamp")
  val timestamp: String,
)

@JsonClass(generateAdapter = true)
data class DetectedActivityItem(
  /**
   * The type of the detected activity.
   */
  @Json(name = "activityType")
  val activityType: String,
  /**
   * The probability of the detected activity.
   */
  @Json(name = "probability")
  val probability: Double,
)

@JsonClass(generateAdapter = true)
data class WifiScan(
  /**
   * The delivery time of the wifi scan.
   */
  @Json(name = "deliveryTime")
  val deliveryTime: String,
  /**
   * An array of wifi devices, including mac address and raw RSSI.
   */
  @Json(name = "devices")
  val devices: List<WifiDevice>,
  /**
   * The source of the wifi scan data.
   */
  @Json(name = "source")
  val source: String,
)

@JsonClass(generateAdapter = true)
data class WifiDevice(
  /**
   * The mac address of the wifi device.
   */
  @Json(name = "mac")
  val mac: String,
  /**
   * The raw RSSI of the wifi device.
   */
  @Json(name = "rawRssi")
  val rawRssi: Int,
)

/**
 * Metadata about the data object.
 */
@JsonClass(generateAdapter = true)
data class Metadata(
  /**
   * The platform that generated the data.
   */
  @Json(name = "platform")
  val platform: String,
)

/**
 * The geographical point.
 */
@JsonClass(generateAdapter = true)
data class Point(
  /**
   * Latitude coordinate of the point. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -900000000 to +900000000 (divide value by 10^7 for the usual range -90° to +90°).
   */
  @Json(name = "latE7")
  val latE7: Int,
  /**
   * Longitude coordinate of the point. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -1800000000 to +1800000000 (divide value by 10^7 for the usual range -180° to +180°).
   */
  @Json(name = "lngE7")
  val lngE7: Int,
)
