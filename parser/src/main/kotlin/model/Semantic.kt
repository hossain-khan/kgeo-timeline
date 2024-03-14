package dev.hossain.timeline.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimelineObjects(
    /** The type of the TimelineObjects */
    @Json(name = "type")
    val type: String,

    /** The title of the TimelineObjects */
    @Json(name = "title")
    val title: String,

    /** The description of the TimelineObjects */
    @Json(name = "description")
    val description: String,

    /** The properties of the TimelineObjects */
    @Json(name = "properties")
    val properties: TimelineObjectsProperties
)

@JsonClass(generateAdapter = true)
data class TimelineObjectsProperties(
    /** The activity segment of the TimelineObjectsProperties */
    @Json(name = "activitySegment")
    val activitySegment: ActivitySegment,

    /** The place visit of the TimelineObjectsProperties */
    @Json(name = "placeVisit")
    val placeVisit: PlaceVisit
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
    val distance: Int,

    /** The type of the activity */
    @Json(name = "activityType")
    val activityType: String,

    /** The list of activities */
    @Json(name = "activities")
    val activities: List<Activity>,

    /** The confidence of the activity */
    @Json(name = "confidence")
    val confidence: String,

    /** The waypoint path of the activity */
    @Json(name = "waypointPath")
    val waypointPath: WaypointPath,

    /** The simplified raw path of the activity */
    @Json(name = "simplifiedRawPath")
    val simplifiedRawPath: SimplifiedRawPath,

    /** The transit path of the activity */
    @Json(name = "transitPath")
    val transitPath: TransitPath,

    /** The parking event of the activity */
    @Json(name = "parkingEvent")
    val parkingEvent: ParkingEvent,

    /** The edit confirmation status of the activity */
    @Json(name = "editConfirmationStatus")
    val editConfirmationStatus: String,

    /** The edit action metadata of the activity */
    @Json(name = "editActionMetadata")
    val editActionMetadata: EditActionMetadata,

    /** The last edited timestamp of the activity */
    @Json(name = "lastEditedTimestamp")
    val lastEditedTimestamp: String
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
    val placeId: String,

    /** Address of the location */
    @Json(name = "address")
    val address: String,

    /** Name of the location */
    @Json(name = "name")
    val name: String
)

@JsonClass(generateAdapter = true)
data class PlaceVisit(
    /** Location of the place */
    @Json(name = "location")
    val location: Location,

    /** Duration of the place visit */
    @Json(name = "duration")
    val duration: Duration,

    /** Categorized confidence for this place visit */
    @Json(name = "placeConfidence")
    val placeConfidence: String
)

@JsonClass(generateAdapter = true)
data class Duration(
    /** Start timestamp of the duration */
    @Json(name = "startTimestamp")
    val startTimestamp: String,

    /** End timestamp of the duration */
    @Json(name = "endTimestamp")
    val endTimestamp: String
)

@JsonClass(generateAdapter = true)
data class ActivityDuplicate(
    /** Type of activity */
    @Json(name = "activityType")
    val activityType: String,

    /** Probability that the activity type is correct */
    @Json(name = "probability")
    val probability: Double
)

@JsonClass(generateAdapter = true)
data class SourceInfo(
    /** The source of the location information */
    @Json(name = "source")
    val source: String
)

@JsonClass(generateAdapter = true)
data class SimplifiedRawPath(
    /** The points in the simplified raw path */
    @Json(name = "points")
    val points: List<Point>
)

@JsonClass(generateAdapter = true)
data class TransitPath(
    /** The transit stops in the transit path */
    @Json(name = "transitStops")
    val transitStops: List<TransitStop>
)

@JsonClass(generateAdapter = true)
data class ParkingEvent(
    /** The location of the parking event */
    @Json(name = "location")
    val location: Location,

    /** The duration of the parking event */
    @Json(name = "duration")
    val duration: Duration
)

@JsonClass(generateAdapter = true)
data class EditActionMetadata(
    /** The action of the edit */
    @Json(name = "action")
    val action: String,

    /** The timestamp of the edit action */
    @Json(name = "actionTimestamp")
    val actionTimestamp: String
)

@JsonClass(generateAdapter = true)
data class Checkin(
    /** The timestamp of the checkin */
    @Json(name = "timestamp")
    val timestamp: String,

    /** The place ID of the checkin */
    @Json(name = "placeId")
    val placeId: String
)

@JsonClass(generateAdapter = true)
data class TransitStop(
    /** The location of the transit stop */
    @Json(name = "location")
    val location: Location,

    /** The name of the transit stop */
    @Json(name = "name")
    val name: String
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
    val timestamp: String
)

@JsonClass(generateAdapter = true)
data class WaypointPath(
    /** The waypoints in the waypoint path */
    @Json(name = "waypoints")
    val waypoints: List<Point>
)
