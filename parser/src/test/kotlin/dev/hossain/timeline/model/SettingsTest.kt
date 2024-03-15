package dev.hossain.timeline.model

import ZonedDateTimeAdapter
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import kotlin.test.Test

/**
 * Test cases for [Settings].
 */
class SettingsTest {
  private val moshi: Moshi = Moshi.Builder().add(ZonedDateTimeAdapter()).build()

  @Test
  fun `given settings json should parse all settings data`() {
    val json = javaClass.getResourceAsStream("/settings.json")!!.bufferedReader().readText()
    val settings = moshi.adapter(Settings::class.java).fromJson(json)!!

    assertThat(settings.createdTime).isEqualTo("2013-08-10T18:07:41.251Z")
    assertThat(settings.deviceSettings).hasSize(4)
    assertThat(settings.hasSetRetention).isFalse()
    assertThat(settings.historyEnabled).isNull()
    assertThat(settings.hasReportedLocations).isTrue()
  }
}
