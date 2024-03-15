package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * The `Records.json` file in a Google Takeout Location History extraction. Contains raw Location History information from the user's account.
 */
@JsonClass(generateAdapter = true)
data class Records(
  /**
   * List of all available location records, in chronological order.
   */
  @Json(name = "locations") val locations: List<LocationRecord>,
)

/**
 * A specific location record. Contains the information obtained from a user's device at a specific moment in time and at a given location.
 */
@JsonClass(generateAdapter = true)
data class LocationRecord(
  /**
   * Approximate accuracy radius of the location measurement, in meters. A lower value means better precision.
   */
  val accuracy: Int,
  /**
   * Information about the access points found in a Wi-Fi scan done by the device and associated with this location record.
   */
  val activeWifiScan: WifiScan?,
  /**
   * Detected activity information at this location, as a list of activity records at slightly different timestamps but all associated with this location record.
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
   * Integer identifier (specific to Location History) associated with the device that uploaded the location. Refer to `deviceSettings` in Settings.json for information about the device with this `deviceTag`.
   */
  val deviceTag: Int,
  /**
   * The version of the device's operating system that provided this record. In Android devices, this corresponds to the API Level (e.g. 28 is Android 9 *Pie*).
   */
  val osLevel: Int?,
  /**
   * Heading in degrees east of true north, from 0 to 359.
   */
  val heading: Int?,
  /**
   * WGS84 Latitude coordinate of the location. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -900000000 to +900000000 (divide value by 10^7 for the usual range -90° to +90°).
   */
  val latitudeE7: Int?,
  /**
   * Additional location metadata. List of different Wi-Fi scans associated with this location record.
   */
  val locationMetadata: List<LocationMetadata>?,
  /**
   * WGS84 Longitude coordinate of the location. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -1800000000 to +1800000000 (divide value by 10^7 for the usual range -180° to +180°).
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
  val source: String?,
  /**
   * Timestamp (as an ISO 8601 string) of the record.
   */
  val timestamp: String,
  /**
   * Measured velocity (ground speed) in meters per second.
   */
  val velocity: Int?,
  /**
   * Calculated accuracy of the location's altitude measurement, in meters. A lower value means better precision.
   */
  val verticalAccuracy: Int?,
)

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
   * MAC address of the access point as an integer. MAC addresses typically consist of 48 bits (6 bytes), so it is likely the value found here needs to be interpreted as a 6 byte integer (which when converted to its hexadecimal representation results in a more typical representation for MAC addresses).
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
 * A detected activity with an associated confidence. Descriptions partially based on: <https://developers.google.com/android/reference/com/google/android/gms/location/DetectedActivity>
 */
@JsonClass(generateAdapter = true)
data class Activity(
  /**
   * Type of activity detected.
   */
  val type: ActivityType = ActivityType.UNKNOWN_ACTIVITY_TYPE,
  /**
   * Value from 0 to 100 indicating the likelihood that the user is performing this activity.
   * The larger the value, the more consistent the data used to perform the classification is with the detected activity.
   * Multiple activities may have high confidence values. For example, the `ON_FOOT` may have a confidence of 100 while the `RUNNING` activity may have a confidence of 95. The sum of the confidences of all detected activities for a classification does not have to be <= 100 since some activities are not mutually exclusive (for example, you can be walking while in a bus) and some activities are hierarchical (`ON_FOOT` is a generalization of `WALKING` and `RUNNING`).
   */
  val confidence: Int?,
)

/**
 * Enum class for different types of activities detected by the device, used in [Activity.type].
 */
enum class ActivityType(val title: String, val extraColor: String) {
  BOATING("Boating", "#01579b"),
  CATCHING_POKEMON("Catching Pokémon", "#db4437"),
  CYCLING("Cycling", "#4db6ac"),
  EXITING_VEHICLE("Exiting a vehicle", "#01579b"),
  FLYING("Flying", "#3f51b5"),
  HIKING("Hiking", "#c2185b"),
  HORSEBACK_RIDING("Horseback riding", "#4db6ac"),
  IN_BUS("On a bus", "#01579b"),
  IN_CABLECAR("In a cable car", "#01579b"),
  IN_CAR("In a car", "#01579b"),
  IN_FERRY("On a ferry", "#01579b"),
  IN_FOUR_WHEELER_VEHICLE("In a four wheeler vehicle", "#01579b"),
  IN_FUNICULAR("On a funicular", "#01579b"),
  IN_GONDOLA_LIFT("In a gondola lift", "#01579b"),
  IN_PASSENGER_VEHICLE("Driving", "#01579b"),
  IN_RAIL_VEHICLE("In a rail vehicle", "#01579b"),
  IN_ROAD_VEHICLE("In a road vehicle", "#03a9f4"),
  IN_SUBWAY("On the subway", "#01579b"),
  IN_TAXI("In a taxi", "#01579b"),
  IN_TRAIN("On a train", "#01579b"),
  IN_TRAM("On a tram", "#01579b"),
  IN_TWO_WHEELER_VEHICLE("On a two wheeler vehicle", "#01579b"),
  IN_VEHICLE("In a vehicle", "#03a9f4"),
  IN_WHEELCHAIR("By wheelchair", "#03a9f4"),
  KAYAKING("Kayaking", "#4db6ac"),
  KITESURFING("Kitesurfing", "#4db6ac"),
  MOTORCYCLING("Motorcycling", "#01579b"),
  ON_BICYCLE("On a bicycle", "#4db6ac"),
  ON_FOOT("On foot", "#c2185b"),
  PARAGLIDING("Paragliding", "#4db6ac"),
  ROWING("Rowing", "#c2185b"),
  RUNNING("Running", "#c2185b"),
  SAILING("Sailing", "#4db6ac"),
  SKATEBOARDING("Skateboarding", "#4db6ac"),
  SKATING("Skating", "#4db6ac"),
  SKIING("Skiing", "#4db6ac"),
  SLEDDING("Sledding", "#4db6ac"),
  SNOWBOARDING("Snowboarding", "#4db6ac"),
  SNOWMOBILE("Snowmobiling", "#01579b"),
  SNOWSHOEING("Snowshoeing", "#c2185b"),
  STILL("Still", "#01579b"),
  SURFING("Surfing", "#4db6ac"),
  SWIMMING("Swimming", "#c2185b"),
  TILTING("Tilting", "#01579b"),
  UNKNOWN("Unknown", "#03a9f4"),
  UNKNOWN_ACTIVITY_TYPE("Unknown Activity", "#03a9f4"),
  WALKING("Walking", "#03a9f4"),
  WALKING_NORDIC("Nordic walking", "#c2185b"),
}
