package dev.hossain.timeline.model.record

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The `Records.json` file in a Google Takeout Location History extraction.
 * Contains raw Location History information from the user's account.
 */
@JsonClass(generateAdapter = true)
data class Records(
  /**
   * List of all available location records, in chronological order.
   */
  @Json(name = "locations") val locations: List<LocationRecord>,
)

/**
 * A specific location record. Contains the information obtained from a user's device
 * at a specific moment in time and at a given location.
 */
@JsonClass(generateAdapter = true)
data class LocationRecord(
  /**
   * Approximate accuracy radius of the location measurement, in meters.
   * A lower value means better precision.
   */
  val accuracy: Int?,
  /**
   * Information about the access points found in a Wi-Fi scan done by the device
   * and associated with this location record.
   */
  val activeWifiScan: WifiScan?,
  /**
   * Detected activity information at this location, as a list of activity records
   * at slightly different timestamps but all associated with this location record.
   */
  val activity: List<ActivityRecord>?,
  /**
   * Altitude above the WGS84 reference ellipsoid, in meters.
   */
  val altitude: Int?,
  /**
   * Whether the device was charging its battery or not at the time of the record.
   */
  val batteryCharging: Boolean?,
  /**
   * Common values found are: `PRIMARY`, `UNKNOWN`.
   */
  val deviceDesignation: String?,
  /**
   * Integer identifier (specific to Location History) associated with the device that uploaded the location.
   * Refer to `deviceSettings` in Settings.json for information about the device with this `deviceTag`.
   */
  val deviceTag: Int?,
  /**
   * The version of the device's operating system that provided this record.
   * In Android devices, this corresponds to the API Level (e.g. 28 is Android 9 *Pie*).
   */
  val osLevel: Int?,
  /**
   * Heading in degrees east of true north, from 0 to 359.
   */
  val heading: Int?,
  /**
   * WGS84 Latitude coordinate of the location. Degrees multiplied by 10^7
   * and rounded to the nearest integer, in the range -900000000 to +900000000
   * (divide value by 10^7 for the usual range -90° to +90°).
   */
  val latitudeE7: Int?,
  /**
   * Additional location metadata. List of different Wi-Fi scans associated with this location record.
   */
  val locationMetadata: List<LocationMetadata>?,
  /**
   * WGS84 Longitude coordinate of the location. Degrees multiplied by 10^7
   * and rounded to the nearest integer, in the range -1800000000 to +1800000000
   * (divide value by 10^7 for the usual range -180° to +180°).
   */
  val longitudeE7: Int?,
  /**
   * Platform of the device that provided this record. Valid values are: `ANDROID`, `IOS` and `UNKNOWN`.
   */
  val platformType: String?,
  /**
   * Source (technology) that provided the location information for this record.
   * Common values are: `WIFI`, `CELL`, `GPS`, `UNKNOWN` (note: sometimes found in lowercase).
   */
  val source: LocationRecordSource?,
  /**
   * Timestamp (as an ISO 8601 string) of the record.
   */
  val timestamp: String,
  /**
   * Measured velocity (ground speed) in meters per second.
   */
  val velocity: Int?,
  /**
   * Calculated accuracy of the location's altitude measurement, in meters.
   * A lower value means better precision.
   */
  val verticalAccuracy: Int?,
)

/**
 * Represents the source (technology) that provided the location information for this record.
 * Common values are: `WIFI`, `CELL`, `GPS`, `UNKNOWN` (note: sometimes found in lowercase).
 * Example: "WIFI"
 */
enum class LocationRecordSource {
  /**
   * Indicates the location information was provided by WIFI.
   */
  WIFI,

  /**
   * Indicates the location information was provided by CELL.
   */
  CELL,

  /**
   * Indicates the location information was provided by GPS.
   */
  GPS,

  /**
   * Indicates the location information was provided manually by user.
   */
  MANUAL,

  /**
   * Indicates the location information was provided by visiting location (e.g. on arrival).
   */
  VISIT_ARRIVAL,

  /**
   * Indicates the location information was provided by visiting location (e.g. on departure).
   */
  VISIT_DEPARTURE,

  /**
   * Indicates the location information was provided by UNKNOWN.
   */
  UNKNOWN,
}

/**
 * Information about the access points found in a Wi-Fi scan done by the device.
 */
@JsonClass(generateAdapter = true)
data class WifiScan(
  /**
   * List of Wi-Fi access points found with the scan.
   */
  @Json(name = "accessPoints") val accessPoints: List<AccessPoint>,
)

/**
 * Information about a specific wireless access point or router.
 */
@JsonClass(generateAdapter = true)
data class AccessPoint(
  /**
   * MAC address of the access point as an integer. MAC addresses typically consist of 48 bits (6 bytes),
   * so it is likely the value found here needs to be interpreted as a 6 byte integer
   * (which when converted to its hexadecimal representation results in a more typical representation for MAC addresses).
   */
  val mac: String,
  /**
   * Strength of the signal in dBm (decibels per milliwatt) of the access point.
   */
  val strength: Int,
  /**
   * Frequency of the signal (in MHz) that the access point is using.
   */
  val frequencyMhz: Int,
  /**
   * Whether the device that scanned the access point is connected to it.
   */
  val isConnected: Boolean?,
)

/**
 * Information about a Wi-Fi scan done by the device at a given timestamp.
 */
@JsonClass(generateAdapter = true)
data class LocationMetadata(
  /**
   * Timestamp (as an ISO 8601 string) of the location metadata.
   */
  val timestamp: String,
  /**
   * Information about the access points found in a Wi-Fi scan done by the device.
   */
  val wifiScan: WifiScan,
)

/**
 * Activity information for a location at a given timestamp.
 */
@JsonClass(generateAdapter = true)
data class ActivityRecord(
  /**
   * List of candidate detected activities with their associated confidence.
   */
  val activity: List<Activity>,
  /**
   * Timestamp (as an ISO 8601 string) of the activity.
   */
  val timestamp: String,
)

/**
 * A detected activity with an associated confidence.
 * Descriptions partially based on: <https://developers.google.com/android/reference/com/google/android/gms/location/DetectedActivity>
 */
@JsonClass(generateAdapter = true)
data class Activity(
  /**
   * Type of activity detected.
   */
  val type: ActivityType = ActivityType.UNKNOWN,
  /**
   * Value from 0 to 100 indicating the likelihood that the user is performing this activity.
   * The larger the value, the more consistent the data used to perform the classification is with the detected activity.
   * Multiple activities may have high confidence values. For example, the `ON_FOOT` may have a confidence of 100
   * while the `RUNNING` activity may have a confidence of 95. The sum of the confidences of all detected activities
   * for a classification does not have to be <= 100 since some activities are not mutually exclusive
   * (for example, you can be walking while in a bus) and some activities are hierarchical
   * (`ON_FOOT` is a generalization of `WALKING` and `RUNNING`).
   */
  val confidence: Int?,
)

/**
 * Enum class for different types of activities detected by the device, used in [Activity.type].
 */
enum class ActivityType(val title: String) {
  EXITING_VEHICLE("The device is exiting a vehicle."),
  IN_BUS("The device is in a bus."),
  IN_CAR("The device is in a car."),
  IN_FOUR_WHEELER_VEHICLE("The device is in a four-wheeler vehicle."),
  IN_RAIL_VEHICLE("The device is in a rail vehicle."),
  IN_ROAD_VEHICLE("The device is in a road vehicle."),
  IN_TWO_WHEELER_VEHICLE("The device is in a two-wheeler vehicle."),
  IN_VEHICLE("The device is in a vehicle, such as a car."),
  ON_BICYCLE("The device is on a bicycle."),
  ON_FOOT("The device is on a user who is walking or running."),
  RUNNING("The device is on a user who is running."),
  STILL("The device is still (not moving)."),
  TILTING(
    "The device angle relative to gravity changed significantly. " +
      "This often occurs when a device is picked up from a desk or a user who is sitting stands up.",
  ),
  WALKING("The device is on a user who is walking."),
  UNKNOWN("Unable to detect the current activity."),
}
