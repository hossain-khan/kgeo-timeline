package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SemanticTimeline(
  @Json(name = "timelineObjects")
  val timelineObjects: List<TimelineObjectsProperties> = emptyList(),
)

@JsonClass(generateAdapter = true)
data class TimelineObjectsProperties(
  /** The activity segment of the TimelineObjectsProperties */
  @Json(name = "activitySegment")
  val activitySegment: ActivitySegment?,
  /** The place visit of the TimelineObjectsProperties */
  @Json(name = "placeVisit")
  val placeVisit: PlaceVisit?,
)

@JsonClass(generateAdapter = true)
data class ActivitySegment(
  /**
   * Start location of the activity.
   */
  @Json(name = "startLocation")
  val startLocation: Location,
  /**
   * End location of the activity.
   */
  @Json(name = "endLocation")
  val endLocation: Location,
  /**
   * Duration of the activity.
   * Example: Duration(startTimestamp = "2022-03-03T12:22:24Z", endTimestamp = "2022-03-03T12:43:34Z")
   */
  @Json(name = "duration")
  val duration: Duration,
  /**
   * Distance traveled during the activity, in meters.
   * Example: 2640
   */
  @Json(name = "distance")
  val distance: Int?,
  /**
   * Best match activity type. Corresponds to the activity type with the highest probability in activities.
   * Example: "IN_BUS"
   */
  @Json(name = "activityType")
  val activityType: String,
  /**
   * List of all the considered candidate activity types and their probabilities. The sum of all the probabilities is always <= 100.
   * Example: [Activity(activityType = "IN_BUS", probability = 85.6847882270813), Activity(activityType = "WALKING", probability = 8.418431878089905)]
   */
  @Json(name = "activities")
  val activities: List<Activity> = emptyList(),
  /**
   * Confidence that the chosen activity type is correct. One of: `LOW`, `MEDIUM`, `HIGH` or `UNKNOWN_CONFIDENCE`. Activities that have been manually confirmed always have a confidence of `HIGH`.
   * Example: "HIGH"
   */
  @Json(name = "confidence")
  val confidence: String,
  @Json(name = "waypointPath")
  val waypointPath: WaypointPath?,
  /** The simplified raw path of the activity */
  @Json(name = "simplifiedRawPath")
  val simplifiedRawPath: SimplifiedRawPath?,
  /**
   * Path taken in a public transit system, such as a bus or a metro.
   * Example: TransitPath(transitStops = [TransitStop(latitudeE7 = 414083140, longitudeE7 = 21704000, placeId = "ChIJWey1zMWipBIRiNQSzpI4EDQ", address = "08025 Barcelona\nEspaña", name = "Sant Antoni Maria Claret-Lepant")], name = "H8", hexRgbColor = "009EE0", linePlaceId = "ChIJQVEUoLuipBIRJO37wI4yyBs", stopTimesInfo = [StopTimeInfo(scheduledDepartureTimestamp = "2022-03-03T12:42:00Z", realtimeDepartureTimestamp = "2022-03-03T12:43:37Z")], source = "INFERRED", confidence = 0.9155850640140931, distanceMeters = 2341.0)
   */
  @Json(name = "transitPath")
  val transitPath: TransitPath?,
  /** The parking event of the activity */
  @Json(name = "parkingEvent")
  val parkingEvent: ParkingEvent?,
  /**
   * Whether the user has manually edited the activity segment. Can be `NOT_CONFIRMED` or `CONFIRMED`.
   * Example: "CONFIRMED"
   */
  @Json(name = "editConfirmationStatus")
  val editConfirmationStatus: String?,
  /**
   * Edit-Action Metadata for this activity segment
   * Example: EditActionMetadata(lastEditedTimestamp = "2022-03-06T14:13:11.092Z")
   */
  @Json(name = "editActionMetadata")
  val editActionMetadata: EditActionMetadata?,
  /**
   * Last-Edited Timestamp for this activity segment
   * Example: "2022-03-06T14:13:11.092Z"
   */
  @Json(name = "lastEditedTimestamp")
  val lastEditedTimestamp: String?,
)

@JsonClass(generateAdapter = true)
data class Location(
  /** Latitude coordinate of the location */
  @Json(name = "latitudeE7")
  val latitudeE7: Int,
  /** Longitude coordinate of the location */
  @Json(name = "longitudeE7")
  val longitudeE7: Int,
  /** Google Maps Place ID of the location */
  @Json(name = "placeId")
  val placeId: String?,
  /** Address of the location */
  @Json(name = "address")
  val address: String?,
  /** Name of the location */
  @Json(name = "name")
  val name: String?,
)

@JsonClass(generateAdapter = true)
data class PlaceVisit(
  /**
   * Location of the place
   * Example: Location(latitudeE7 = 414216106, longitudeE7 = 21684775, placeId = "ChIJk_s92NyipBIRUMnDG8Kq2Js")
   */
  @Json(name = "location")
  val location: Location,
  /**
   * Latitude coordinate of the location. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -900000000 to +900000000 (divide value by 10^7 for the usual range -90° to +90°).
   * Example: 414216106
   */
  @Json(name = "centerLatE7")
  val centerLatE7: Int,
  /**
   * Longitude coordinate of the location. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -1800000000 to +1800000000 (divide value by 10^7 for the usual range -180° to +180°).
   * Example: 21684775
   */
  @Json(name = "centerLngE7")
  val centerLngE7: Int,
  /**
   * Duration of the place visit
   * Example: Duration(startTimestamp = "2022-03-06T14:13:11.092Z", endTimestamp = "2022-03-06T15:13:11.092Z")
   */
  @Json(name = "duration")
  val duration: Duration,
  /**
   * Categorized confidence for this place visit. One of: `LOW_CONFIDENCE`, `MEDIUM_CONFIDENCE`, `HIGH_CONFIDENCE` or `USER_CONFIRMED`.
   * Example: "HIGH"
   */
  @Json(name = "placeConfidence")
  val placeConfidence: String,
  /**
   * Visit confidence for this place visit
   * Example: 95
   */
  @Json(name = "visitConfidence")
  val visitConfidence: Int,
  /**
   * Location confidence for this place visit
   * Example: 71
   */
  @Json(name = "locationConfidence")
  val locationConfidence: Int,
  /**
   * Other candidate locations for this place visit
   * Example: [Location(latitudeE7 = 414216106, longitudeE7 = 21684775, placeId = "ChIJk_s92NyipBIRUMnDG8Kq2Js")]
   */
  @Json(name = "otherCandidateLocations")
  val otherCandidateLocations: List<Location>,
  /**
   * Child visits for this place visit
   * Example: [PlaceVisit(location = Location(latitudeE7 = 414216106, longitudeE7 = 21684775, placeId = "ChIJk_s92NyipBIRUMnDG8Kq2Js"), centerLatE7 = 414216106, centerLngE7 = 21684775, duration = Duration(startTimestamp = "2022-03-06T14:13:11.092Z", endTimestamp = "2022-03-06T15:13:11.092Z"), placeConfidence = "HIGH", visitConfidence = 95, locationConfidence = 71, otherCandidateLocations = [Location(latitudeE7 = 414216106, longitudeE7 = 21684775, placeId = "ChIJk_s92NyipBIRUMnDG8Kq2Js")])]
   */
  @Json(name = "childVisits")
  val childVisits: List<PlaceVisit>?,
  /**
   * Section ID for this place visit
   * Example: "section123"
   */
  @Json(name = "sectionId")
  val sectionId: String?,
  /**
   * Simplified raw path for this place visit.
   */
  @Json(name = "simplifiedRawPath")
  val simplifiedRawPath: SimplifiedRawPath?,
  /**
   * Level (depth) of this place visit. This value increases by 1 with each recursive access to a [#/$defs/placeVisit/properties/childVisits] entry.
   * Example: 1
   */
  @Json(name = "placeVisitLevel")
  val placeVisitLevel: Int?,
  /**
   * Whether the user has manually edited the place visit. Can be `NOT_CONFIRMED` or `CONFIRMED`.
   * Example: "CONFIRMED"
   */
  @Json(name = "editConfirmationStatus")
  val editConfirmationStatus: String,
  /**
   * Edit-Action Metadata for this place visit
   * Example: EditActionMetadata(lastEditedTimestamp = "2022-03-06T14:13:11.092Z")
   */
  @Json(name = "editActionMetadata")
  val editActionMetadata: EditActionMetadata?,
  /**
   * Last-Edited Timestamp for this place visit
   * Example: "2022-03-06T14:13:11.092Z"
   */
  @Json(name = "lastEditedTimestamp")
  val lastEditedTimestamp: String?,
  /**
   * Place Visit Type for this place visit. Can be `SINGLE_PLACE`.
   * Example: "SINGLE_PLACE"
   */
  @Json(name = "placeVisitType")
  val placeVisitType: String,
  /**
   * Place Visit Importance for this place visit. One of `MAIN` or `TRANSITIONAL`.
   * Example: "MAIN"
   */
  @Json(name = "placeVisitImportance")
  val placeVisitImportance: String?,
  /**
   * Location Assertion Type for this place visit
   * Example: "AREA"
   */
  @Json(name = "locationAssertionType")
  val locationAssertionType: String?,
  /**
   * Checkin for this place visit
   */
  @Json(name = "checkin")
  val checkin: Checkin?,
)

@JsonClass(generateAdapter = true)
data class Duration(
  /** Start timestamp of the duration */
  @Json(name = "startTimestamp")
  val startTimestamp: String,
  /** End timestamp of the duration */
  @Json(name = "endTimestamp")
  val endTimestamp: String,
)

@JsonClass(generateAdapter = true)
data class ActivityDuplicate(
  /** Type of activity */
  @Json(name = "activityType")
  val activityType: String,
  /** Probability that the activity type is correct */
  @Json(name = "probability")
  val probability: Double,
)

@JsonClass(generateAdapter = true)
data class SourceInfo(
  /** The source of the location information */
  @Json(name = "source")
  val source: String,
)

@JsonClass(generateAdapter = true)
data class SimplifiedRawPath(
  /** The points in the simplified raw path */
  @Json(name = "points")
  val points: List<Point>,
)

@JsonClass(generateAdapter = true)
data class TransitPath(
  /**
   * List of locations of the transit stops used.
   * Example: [TransitStop(latitudeE7 = 414083140, longitudeE7 = 21704000, placeId = "ChIJWey1zMWipBIRiNQSzpI4EDQ", address = "08025 Barcelona\nEspaña", name = "Sant Antoni Maria Claret-Lepant")]
   */
  @Json(name = "transitStops")
  val transitStops: List<TransitStop>,
  /**
   * Name of the transit line.
   * Example: "H8"
   */
  @Json(name = "name")
  val name: String,
  /**
   * Color of the transit line in hexadecimal in the form *RRGGBB*.
   * Example: "009EE0"
   */
  @Json(name = "hexRgbColor")
  val hexRgbColor: String,
  /**
   * Google Maps Place ID of the transit line.
   * Example: "ChIJQVEUoLuipBIRJO37wI4yyBs"
   */
  @Json(name = "linePlaceId")
  val linePlaceId: String,
  /**
   * Time information (departure and arrival times, both real and scheduled) for each transit stop used.
   * Example: [StopTimeInfo(scheduledDepartureTimestamp = "2022-03-03T12:42:00Z", realtimeDepartureTimestamp = "2022-03-03T12:43:37Z")]
   */
  @Json(name = "stopTimesInfo")
  val stopTimesInfo: List<StopTimeInfo>,
  /**
   * Source of the location data of the transit path. Either `BACKFILLED` or `INFERRED`.
   * Example: "INFERRED"
   */
  @Json(name = "source")
  val source: String,
  /**
   * Confidence of the transit path.
   * Example: 0.9155850640140931
   */
  @Json(name = "confidence")
  val confidence: Double,
  /**
   * Distance traveled with the transit path, in meters.
   * Example: 2341.0
   */
  @Json(name = "distanceMeters")
  val distanceMeters: Double,
)

@JsonClass(generateAdapter = true)
data class StopTimeInfo(
  /**
   * Scheduled departure timestamp.
   * Example: "2022-03-03T12:42:00Z"
   */
  @Json(name = "scheduledDepartureTimestamp")
  val scheduledDepartureTimestamp: String,
  /**
   * Real-time departure timestamp.
   * Example: "2022-03-03T12:43:37Z"
   */
  @Json(name = "realtimeDepartureTimestamp")
  val realtimeDepartureTimestamp: String,
)

@JsonClass(generateAdapter = true)
data class ParkingEvent(
  /** The location of the parking event */
  @Json(name = "location")
  val location: Location,
  /** The duration of the parking event */
  @Json(name = "duration")
  val duration: Duration?,
)

@JsonClass(generateAdapter = true)
data class EditActionMetadata(
  /** The action of the edit */
  @Json(name = "action")
  val action: String,
  /** The timestamp of the edit action */
  @Json(name = "actionTimestamp")
  val actionTimestamp: String,
)

@JsonClass(generateAdapter = true)
data class Checkin(
  /** The timestamp of the checkin */
  @Json(name = "timestamp")
  val timestamp: String,
  /** The place ID of the checkin */
  @Json(name = "placeId")
  val placeId: String,
)

@JsonClass(generateAdapter = true)
data class TransitStop(
  /**
   * Latitude coordinate of the transit stop. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -900000000 to +900000000 (divide value by 10^7 for the usual range -90° to +90°).
   * Example: 414083140
   */
  @Json(name = "latitudeE7")
  val latitudeE7: Int,
  /**
   * Longitude coordinate of the transit stop. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -1800000000 to +1800000000 (divide value by 10^7 for the usual range -180° to +180°).
   * Example: 21704000
   */
  @Json(name = "longitudeE7")
  val longitudeE7: Int,
  /**
   * Google Maps Place ID of the transit stop.
   * Example: "ChIJWey1zMWipBIRiNQSzpI4EDQ"
   */
  @Json(name = "placeId")
  val placeId: String,
  /**
   * Address of the transit stop.
   * Example: "08025 Barcelona\nEspaña"
   */
  @Json(name = "address")
  val address: String,
  /**
   * Name of the transit stop.
   * Example: "Sant Antoni Maria Claret-Lepant"
   */
  @Json(name = "name")
  val name: String,
)

@JsonClass(generateAdapter = true)
data class Point(
  /** The latitude of the point */
  @Json(name = "latE7")
  val latE7: Int,
  /** The longitude of the point */
  @Json(name = "lngE7")
  val lngE7: Int,
  /** The timestamp of the point */
  @Json(name = "timestamp")
  val timestamp: String?,
)

@JsonClass(generateAdapter = true)
data class WaypointPath(
  /**
   * Waypoints of the path
   * Example: [Waypoint(latE7 = 416119834, lngE7 = 21768624), Waypoint(latE7 = 416117012, lngE7 = 21899302), Waypoint(latE7 = 416119262, lngE7 = 21802315)]
   */
  @Json(name = "waypoints")
  val waypoints: List<Waypoint>,
  /**
   * Source of the location data of the path. Either `BACKFILLED` or `INFERRED`.
   * Example: "INFERRED"
   */
  @Json(name = "source")
  val source: String,
  /**
   * Total distance of the path, in meters.
   * Example: 396.34176716755843
   */
  @Json(name = "distanceMeters")
  val distanceMeters: Double,
  /**
   * Travel mode of the path. Can be `WALK`, `DRIVE`, or `BICYCLE`.
   * Example: "WALK"
   */
  @Json(name = "travelMode")
  val travelMode: String,
  /**
   * Confidence of the path.
   * Example: 0.7986568220419046
   */
  @Json(name = "confidence")
  val confidence: Double,
  /**
   * Road segments of the path.
   * Example: [RoadSegment(duration = "8s", placeId = "ChIJk_s92NyipBIRUMnDG8Kq2Js")]
   */
  @Json(name = "roadSegment")
  val roadSegment: List<RoadSegment> = emptyList(),
)

@JsonClass(generateAdapter = true)
data class RoadSegment(
  /**
   * Duration of the road segment.
   * Example: "8s"
   */
  @Json(name = "duration")
  val duration: String,
  /**
   * Google Maps Place ID of the location.
   * Example: "ChIJk_s92NyipBIRUMnDG8Kq2Js"
   */
  @Json(name = "placeId")
  val placeId: String,
)

@JsonClass(generateAdapter = true)
data class Waypoint(
  /**
   * Latitude coordinate of the waypoint. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -900000000 to +900000000 (divide value by 10^7 for the usual range -90° to +90°).
   * Example: 414216106
   */
  @Json(name = "latE7")
  val latE7: Int,
  /**
   * Longitude coordinate of the waypoint. Degrees multiplied by 10^7 and rounded to the nearest integer, in the range -1800000000 to +1800000000 (divide value by 10^7 for the usual range -180° to +180°).
   * Example: 21684775
   */
  @Json(name = "lngE7")
  val lngE7: Int,
)
