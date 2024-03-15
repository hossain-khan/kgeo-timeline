package dev.hossain.timeline.model

import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import kotlin.test.Test

/**
 * Test cases for [Settings]
 */
class RecordsTest {
  private val moshi: Moshi = Moshi.Builder().build()

  @Test
  fun `given records json should parse all records`() {
    val json = javaClass.getResourceAsStream("/records.json")!!.bufferedReader().readText()
    val records = moshi.adapter(Records::class.java).fromJson(json)!!

    assertThat(records.locations).hasSize(12)
  }
}
