import com.squareup.moshi.FromJson
import com.squareup.moshi.ToJson
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

/**
 * Moshi adapter for [ZonedDateTime].
 */
internal class ZonedDateTimeAdapter {
  @ToJson
  fun toJson(zonedDateTime: ZonedDateTime): String {
    return DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime)
  }

  @FromJson
  fun fromJson(zonedDateTimeString: String): ZonedDateTime {
    return ZonedDateTime.parse(zonedDateTimeString, DateTimeFormatter.ISO_ZONED_DATE_TIME)
  }
}
