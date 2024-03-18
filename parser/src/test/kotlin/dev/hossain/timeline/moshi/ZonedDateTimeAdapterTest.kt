package dev.hossain.timeline.moshi

import ZonedDateTimeAdapter
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.time.ZoneOffset
import java.time.ZonedDateTime

class ZonedDateTimeAdapterTest {
  private val adapter = ZonedDateTimeAdapter()
  private val moshi =
    Moshi.Builder()
      .add(adapter)
      .build()

  @Test
  fun `should correctly serialize ZonedDateTime to string`() {
    val zonedDateTime = ZonedDateTime.parse("2022-03-01T10:15:30+01:00[Europe/Paris]")
    val jsonAdapter = moshi.adapter(ZonedDateTime::class.java)

    val jsonString = jsonAdapter.toJson(zonedDateTime)

    assertEquals("\"2022-03-01T10:15:30+01:00[Europe/Paris]\"", jsonString)
  }

  @Test
  fun `should correctly deserialize string to ZonedDateTime`() {
    val jsonString = "\"2022-03-01T10:15:30+01:00[Europe/Paris]\""
    val jsonAdapter = moshi.adapter(ZonedDateTime::class.java)

    val zonedDateTime = jsonAdapter.fromJson(jsonString)

    assertEquals(ZonedDateTime.parse("2022-03-01T10:15:30+01:00[Europe/Paris]"), zonedDateTime)
  }

  @Test
  fun `should return null when deserializing invalid string`() {
    val jsonString = "\"invalid-date-time\""
    val jsonAdapter = moshi.adapter(ZonedDateTime::class.java)

    val error = assertThrows<JsonDataException> { jsonAdapter.fromJson(jsonString) }

    assertThat(error).hasMessageThat().contains("Text 'invalid-date-time' could not be parsed")
  }

  @Test
  fun `should return null when deserializing null string`() {
    val jsonString = "null"
    val jsonAdapter = moshi.adapter(ZonedDateTime::class.java)

    val zonedDateTime = jsonAdapter.fromJson(jsonString)

    assertNull(zonedDateTime)
  }

  @Test
  fun fromJson() {
    val time = adapter.fromJson("2019-01-13T16:55:40-08:00")
    assertThat(time.year).isEqualTo(2019)
    assertThat(time.monthValue).isEqualTo(1)
    assertThat(time.dayOfMonth).isEqualTo(13)
    assertThat(time.hour).isEqualTo(16)
    assertThat(time.minute).isEqualTo(55)
    assertThat(time.second).isEqualTo(40)
    assertThat(time.offset).isEqualTo(ZoneOffset.ofHours(-8))
  }

  @Test
  fun toJson() {
    val time = ZonedDateTime.of(2022, 3, 30, 23, 40, 50, 0, ZoneOffset.ofHours(-8))
    assertThat(adapter.toJson(time)).isEqualTo("2022-03-30T23:40:50-08:00")
  }
}
