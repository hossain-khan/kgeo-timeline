package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.JsonEncodingException
import dev.hossain.timeline.Parser
import dev.hossain.timeline.model.settings.DeviceSpec
import dev.hossain.timeline.model.settings.Settings
import org.junit.jupiter.api.assertThrows
import kotlin.test.Test

class SettingsTestCopilot {
  private val parser = Parser()

  @Test
  fun `given settings json should parse all device settings data`() {
    val json = javaClass.getResourceAsStream("/settings.json")!!.bufferedReader().readText()
    val settings: Settings = parser.parseSettings(json)

    assertThat(settings.deviceSettings).hasSize(4)
    val deviceSettings = settings.deviceSettings.first()
    assertThat(deviceSettings.deviceTag).isEqualTo(279958700)
    assertThat(deviceSettings.legalCountryCode).isNull()
    assertThat(deviceSettings.reportingEnabled).isTrue()
    assertThat(deviceSettings.devicePrettyName).isEqualTo("SM-S911W")
    assertThat(deviceSettings.platformType).isEqualTo("ANDROID")
    assertThat(deviceSettings.androidOsLevel).isEqualTo(34)
    assertThat(deviceSettings.deviceSpec).isEqualTo(
      DeviceSpec(
        manufacturer = "samsung",
        brand = "samsung",
        product = "dm1qcsx",
        device = "dm1q",
        model = "SM-S911W",
        isLowRam = false,
      ),
    )
  }

  @Test
  fun `given settings json should parse all settings metadata`() {
    val json = javaClass.getResourceAsStream("/settings.json")!!.bufferedReader().readText()
    val settings: Settings = parser.parseSettings(json)

    assertThat(settings.createdTime).isEqualTo("2013-08-10T18:07:41.251Z")
    assertThat(settings.modifiedTime).isEqualTo("2023-07-06T20:07:56.405Z")
    assertThat(settings.historyEnabled).isNull()
    assertThat(settings.hasReportedLocations).isTrue()
    assertThat(settings.retentionWindowDays).isNull()
    assertThat(settings.hasSetRetention).isFalse()
  }

  @Test
  fun `given settings json with no device settings should parse to empty list`() {
    val json = """
        {
            "createdTime": "2013-08-10T18:07:41.251Z",
            "modifiedTime": "2023-07-06T20:07:56.405Z",
            "historyEnabled": null,
            "deviceSettings": [],
            "retentionWindowDays": null,
            "hasReportedLocations": true,
            "hasSetRetention": false
        }
    """
    val settings: Settings = parser.parseSettings(json)

    assertThat(settings.deviceSettings).isEmpty()
  }

  @Test
  fun `given settings json with invalid format should throw exception`() {
    val json = "invalid json"
    val error =
      assertThrows<JsonEncodingException> {
        parser.parseSettings(json)
      }

    assertThat(error).isInstanceOf(JsonEncodingException::class.java)
  }
}
