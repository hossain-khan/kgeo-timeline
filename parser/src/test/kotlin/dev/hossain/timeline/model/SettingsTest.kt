package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import dev.hossain.timeline.Parser
import dev.hossain.timeline.model.settings.DeviceSpec
import dev.hossain.timeline.model.settings.Settings
import kotlin.test.Test

/**
 * Test cases for [Settings].
 */
class SettingsTest {
  private val parser = Parser()

  @Test
  fun `given settings json should parse all settings data`() {
    val json = javaClass.getResourceAsStream("/settings.json")!!.bufferedReader().readText()
    val settings: Settings = parser.parseSettings(json)

    assertThat(settings.createdTime).isEqualTo("2013-08-10T18:07:41.251Z")
    assertThat(settings.deviceSettings).hasSize(4)
    assertThat(settings.hasSetRetention).isFalse()
    assertThat(settings.historyEnabled).isNull()
    assertThat(settings.hasReportedLocations).isTrue()
    assertThat(settings.modifiedTime).isEqualTo("2023-07-06T20:07:56.405Z")
    assertThat(settings.retentionWindowDays).isNull()
    val deviceSettings = settings.deviceSettings.first()
    assertThat(deviceSettings.deviceTag).isEqualTo(279958700)
    assertThat(deviceSettings.legalCountryCode).isNull()
    assertThat(deviceSettings.reportingEnabled).isTrue()
    assertThat(
      deviceSettings.deviceSpec,
    ).isEqualTo(
      DeviceSpec(
        manufacturer = "samsung",
        brand = "samsung",
        product = "dm1qcsx",
        device = "dm1q",
        model = "SM-S911W",
        isLowRam = false,
      ),
    )
    assertThat(deviceSettings.androidOsLevel).isEqualTo(34)
    assertThat(deviceSettings.devicePrettyName).isEqualTo("SM-S911W")
    assertThat(deviceSettings.platformType).isEqualTo("ANDROID")
  }
}
