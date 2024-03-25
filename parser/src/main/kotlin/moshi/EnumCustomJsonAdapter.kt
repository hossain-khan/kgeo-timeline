package dev.hossain.timeline.moshi

/*
 * Copyright (C) 2018 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import com.squareup.moshi.Json
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonDataException
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonReader.Options
import com.squareup.moshi.JsonReader.Token.STRING
import com.squareup.moshi.JsonWriter
import okio.IOException
import java.lang.NoSuchFieldException
import java.lang.reflect.AnnotatedElement

/**
 * A JsonAdapter for enums that allows having a fallback enum value when a deserialized string does
 * not match any enum value. To use, add this as an adapter for your enum type on your
 * [Moshi.Builder][com.squareup.moshi.Moshi.Builder]:
 *
 * ```
 * Moshi moshi = new Moshi.Builder()
 *   .add(CurrencyCode.class, EnumJsonAdapter.create(CurrencyCode.class)
 *     .withUnknownFallback(CurrencyCode.USD, true))
 *   .build();
 * ```
 */
class EnumCustomJsonAdapter<T : Enum<T>> internal constructor(
  private val enumType: Class<T>,
  private val fallbackValue: T?,
  private val useFallbackValue: Boolean,
  private val useCaseInsensitiveName: Boolean,
) : JsonAdapter<T>() {
  private val constants: Array<T>
  private val options: Options
  private val nameStrings: Array<String>

  init {
    try {
      constants = enumType.enumConstants
      nameStrings =
        Array(constants.size) { i ->
          val constantName = constants[i].name
          enumType.getField(constantName).jsonName(constantName)
        }
      options = Options.of(*nameStrings)
    } catch (e: NoSuchFieldException) {
      throw AssertionError("Missing field in ${enumType.name}", e)
    }
  }

  /**
   * Create a new adapter for this enum with a fallback value to use when the JSON string does not
   * match any of the enum's constants. Note that this value will not be used when the JSON value is
   * null, absent, or not a string. Also, the string values are case-sensitive, and this fallback
   * value will be used even on case mismatches.
   *
   * If the [useCaseInsensitiveName] is set to `true`, then the adapter will try to match the enum
   * value in case-insensitive manner.
   */
  fun withUnknownFallback(
    fallbackValue: T?,
    useCaseInsensitiveName: Boolean = false,
  ): EnumCustomJsonAdapter<T> {
    return EnumCustomJsonAdapter(
      enumType,
      fallbackValue,
      useFallbackValue = true,
      useCaseInsensitiveName = useCaseInsensitiveName,
    )
  }

  @Throws(IOException::class)
  override fun fromJson(reader: JsonReader): T? {
    val index = reader.selectString(options)
    if (index != -1) return constants[index]
    if (!useFallbackValue) {
      val name = reader.nextString()
      throw JsonDataException(
        "Expected one of ${nameStrings.toList()} but was $name at path ${reader.path}",
      )
    }
    if (reader.peek() != STRING) {
      throw JsonDataException(
        "Expected a string but was ${reader.peek()} at path ${reader.path}",
      )
    }
    if (useCaseInsensitiveName) {
      val name = reader.nextString()
      // Find if `name` is in the `nameStrings` array in case-insensitive manner
      val itemIndex = nameStrings.indexOfFirst { it.equals(name, ignoreCase = true) }
      if (itemIndex != -1) {
        return constants[itemIndex]
      }
      return fallbackValue
    } else {
      reader.skipValue()
      return fallbackValue
    }
  }

  @Throws(IOException::class)
  override fun toJson(
    writer: JsonWriter,
    value: T?,
  ) {
    if (value == null) {
      throw NullPointerException(
        "value was null! Wrap in .nullSafe() to write nullable values.",
      )
    }
    writer.value(nameStrings[value.ordinal])
  }

  override fun toString(): String = "EnumJsonAdapter(${enumType.name})"

  companion object {
    @JvmStatic
    fun <T : Enum<T>> create(enumType: Class<T>): EnumCustomJsonAdapter<T> {
      return EnumCustomJsonAdapter(
        enumType,
        fallbackValue = null,
        useFallbackValue = false,
        useCaseInsensitiveName = false,
      )
    }
  }
}

private fun AnnotatedElement.jsonName(declaredName: String): String {
  return getAnnotation(Json::class.java).jsonName(declaredName)
}

private fun Json?.jsonName(declaredName: String): String {
  if (this == null) return declaredName
  val annotationName: String = name
  return if (Json.UNSET_NAME == annotationName) declaredName else annotationName
}
