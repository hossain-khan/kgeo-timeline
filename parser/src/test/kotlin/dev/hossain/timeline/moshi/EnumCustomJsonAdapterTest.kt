package dev.hossain.timeline.moshi

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Json
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.Moshi
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

/**
 * Tests for [EnumCustomJsonAdapter] to verify fallback behavior and case-insensitive matching.
 */
class EnumCustomJsonAdapterTest {
  /** Test enum with @Json annotation for custom name mapping. */
  private enum class Status {
    @Json(name = "active")
    ACTIVE,

    @Json(name = "inactive")
    INACTIVE,
    UNKNOWN,
  }

  /** Test enum without @Json annotations. */
  private enum class Priority {
    HIGH,
    MEDIUM,
    LOW,
    UNKNOWN,
  }

  @Test
  fun `should parse valid enum value with Json annotation`() {
    val moshi =
      Moshi.Builder()
        .add(Status::class.java, EnumCustomJsonAdapter.create(Status::class.java))
        .build()
    val adapter = moshi.adapter(Status::class.java)

    val result = adapter.fromJson("\"active\"")

    assertThat(result).isEqualTo(Status.ACTIVE)
  }

  @Test
  fun `should parse valid enum value without Json annotation`() {
    val moshi =
      Moshi.Builder()
        .add(Priority::class.java, EnumCustomJsonAdapter.create(Priority::class.java))
        .build()
    val adapter = moshi.adapter(Priority::class.java)

    val result = adapter.fromJson("\"HIGH\"")

    assertThat(result).isEqualTo(Priority.HIGH)
  }

  @Test
  fun `should throw exception for unknown value without fallback`() {
    val moshi =
      Moshi.Builder()
        .add(Status::class.java, EnumCustomJsonAdapter.create(Status::class.java))
        .build()
    val adapter = moshi.adapter(Status::class.java)

    val error =
      assertThrows<JsonDataException> {
        adapter.fromJson("\"unknown_value\"")
      }

    assertThat(error.message).contains("Expected one of")
  }

  @Test
  fun `should return fallback value for unknown value with fallback enabled`() {
    val moshi =
      Moshi.Builder()
        .add(
          Status::class.java,
          EnumCustomJsonAdapter.create(Status::class.java)
            .withUnknownFallback(Status.UNKNOWN),
        )
        .build()
    val adapter = moshi.adapter(Status::class.java)

    val result = adapter.fromJson("\"unknown_value\"")

    assertThat(result).isEqualTo(Status.UNKNOWN)
  }

  @Test
  fun `should match case-insensitively when enabled`() {
    val moshi =
      Moshi.Builder()
        .add(
          Priority::class.java,
          EnumCustomJsonAdapter.create(Priority::class.java)
            .withUnknownFallback(Priority.UNKNOWN, useCaseInsensitiveName = true),
        )
        .build()
    val adapter = moshi.adapter(Priority::class.java)

    val resultLower = adapter.fromJson("\"high\"")
    val resultMixed = adapter.fromJson("\"HiGh\"")

    assertThat(resultLower).isEqualTo(Priority.HIGH)
    assertThat(resultMixed).isEqualTo(Priority.HIGH)
  }

  @Test
  fun `should return fallback when case-insensitive match fails`() {
    val moshi =
      Moshi.Builder()
        .add(
          Priority::class.java,
          EnumCustomJsonAdapter.create(Priority::class.java)
            .withUnknownFallback(Priority.UNKNOWN, useCaseInsensitiveName = true),
        )
        .build()
    val adapter = moshi.adapter(Priority::class.java)

    val result = adapter.fromJson("\"nonexistent\"")

    assertThat(result).isEqualTo(Priority.UNKNOWN)
  }

  @Test
  fun `should serialize enum value to json string`() {
    val moshi =
      Moshi.Builder()
        .add(Status::class.java, EnumCustomJsonAdapter.create(Status::class.java))
        .build()
    val adapter = moshi.adapter(Status::class.java)

    val result = adapter.toJson(Status.ACTIVE)

    assertThat(result).isEqualTo("\"active\"")
  }

  @Test
  fun `should serialize enum value without Json annotation`() {
    val moshi =
      Moshi.Builder()
        .add(Priority::class.java, EnumCustomJsonAdapter.create(Priority::class.java))
        .build()
    val adapter = moshi.adapter(Priority::class.java)

    val result = adapter.toJson(Priority.HIGH)

    assertThat(result).isEqualTo("\"HIGH\"")
  }

  @Test
  fun `should throw exception when serializing null without nullSafe`() {
    val moshi =
      Moshi.Builder()
        .add(Status::class.java, EnumCustomJsonAdapter.create(Status::class.java))
        .build()
    val adapter = moshi.adapter(Status::class.java)

    val error =
      assertThrows<NullPointerException> {
        adapter.toJson(null)
      }

    assertThat(error.message).contains("Wrap in .nullSafe()")
  }

  @Test
  fun `should have meaningful toString representation`() {
    val adapter = EnumCustomJsonAdapter.create(Status::class.java)

    assertThat(adapter.toString()).contains("EnumJsonAdapter")
    assertThat(adapter.toString()).contains("Status")
  }

  @Test
  fun `should throw exception for non-string json token`() {
    val moshi =
      Moshi.Builder()
        .add(
          Status::class.java,
          EnumCustomJsonAdapter.create(Status::class.java)
            .withUnknownFallback(Status.UNKNOWN),
        )
        .build()
    val adapter = moshi.adapter(Status::class.java)

    val error =
      assertThrows<JsonDataException> {
        adapter.fromJson("123")
      }

    assertThat(error.message).contains("Expected a string")
  }
}
