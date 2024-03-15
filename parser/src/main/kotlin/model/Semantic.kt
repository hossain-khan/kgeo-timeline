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
  /** The start location of the activity */
  @Json(name = "startLocation")
  val startLocation: Location,
  /** The end location of the activity */
  @Json(name = "endLocation")
  val endLocation: Location,
  /** The duration of the activity */
  @Json(name = "duration")
  val duration: Duration,
  /** The distance of the activity */
  @Json(name = "distance")
  val distance: Int?,
  /** The type of the activity */
  @Json(name = "activityType")
  val activityType: String,
  /** The list of activities */
  @Json(name = "activities")
  val activities: List<Activity> = emptyList(),
  /** The confidence of the activity */
  @Json(name = "confidence")
  val confidence: String,
  /** The waypoint path of the activity */
  @Json(name = "waypointPath")
  val waypointPath: WaypointPath?,
  /** The simplified raw path of the activity */
  @Json(name = "simplifiedRawPath")
  val simplifiedRawPath: SimplifiedRawPath?,
  /** The transit path of the activity */
  @Json(name = "transitPath")
  val transitPath: TransitPath?,
  /** The parking event of the activity */
  @Json(name = "parkingEvent")
  val parkingEvent: ParkingEvent?,
  /** The edit confirmation status of the activity */
  @Json(name = "editConfirmationStatus")
  val editConfirmationStatus: String?,
  /** The edit action metadata of the activity */
  @Json(name = "editActionMetadata")
  val editActionMetadata: EditActionMetadata?,
  /** The last edited timestamp of the activity */
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
  val checkin: Checkin?
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
  /** The transit stops in the transit path */
  @Json(name = "transitStops")
  val transitStops: List<TransitStop>,
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
  /** The location of the transit stop */
  @Json(name = "location")
  val location: Location,
  /** The name of the transit stop */
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
  /** The waypoints in the waypoint path */
  @Json(name = "waypoints")
  val waypoints: List<Point>,
)
