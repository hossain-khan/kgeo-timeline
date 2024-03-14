package dev.hossain.timeline.model

import com.squareup.moshi.JsonClass

/**
 * The `Settings.json` file in a Google Takeout Location History extraction. Contains information about the Google account settings related to Location History, information about the devices associated, and other auxiliary metadata.
 */
@JsonClass(generateAdapter = true)
data class Settings(
    /**
     * Timestamp (as an ISO 8601 string) when Location History was first available on this Google account.
     */
    val createdTime: String,
    /**
     * Timestamp (as an ISO 8601 string) when any Location History setting was last modified on this Google account.
     */
    val modifiedTime: String,
    /**
     * Whether Location History is enabled on this Google account (controlled in the [Activity Controls](https://myactivity.google.com/activitycontrols?settings=location) page).
     */
    val historyEnabled: Boolean,
    /**
     * List of devices associated with the Location History information on this Google account.
     */
    val deviceSettings: List<DeviceSettings>,
    /**
     * Number of days the Location History information is retained in this Google account. Corresponds to the *Auto-delete* setting in the [Activity Controls](https://myactivity.google.com/activitycontrols?settings=location) page.
     * This value should be ignored if [#/properties/hasSetRetention] is false.
     */
    val retentionWindowDays: Int,
    /**
     * Whether this Google account has any reported Location History information.
     */
    val hasReportedLocations: Boolean,
    /**
     * Whether this Google account has configured the auto-delete setting (retention) for the Location History data. Corresponds to the *Auto-delete* setting in the [Activity Controls](https://myactivity.google.com/activitycontrols?settings=location) page.
     * See also [#/properties/retentionWindowDays].
     */
    val hasSetRetention: Boolean
)

/**
 * Information about a device associated with the Google Location History account.
 */
@JsonClass(generateAdapter = true)
data class DeviceSettings(
    /**
     * Integer identifier (specific to Location History) of the device.
     */
    val deviceTag: Int,
    /**
     * Whether this device is configured to report Location History information (controlled in the [Activity Controls](https://myactivity.google.com/activitycontrols?settings=location) page).
     */
    val reportingEnabled: Boolean,
    /**
     * Two-letter [ISO 3166 country code](https://en.wikipedia.org/wiki/ISO_3166-1_alpha-2).
     */
    val legalCountryCode: String,
    /**
     * Pretty name of the device, recommended for user-facing applications.
     */
    val devicePrettyName: String,
    /**
     * Platform of the device. Valid values are: `ANDROID`, `IOS` and `UNKNOWN`.
     */
    val platformType: String,
    /**
     * Timestamp (as an ISO 8601 string) of the first time this device was available for Location History reporting on this Google account.
     */
    val deviceCreationTime: String,
    /**
     * Information on the latest change(s) to Location History settings on this Google account.
     */
    val latestLocationReportingSettingChange: LatestLocationReportingSettingChange,
    /**
     * The version of the device's operating system (only applies to Android devices). Corresponds to the API Level (e.g. 28 is Android 9 *Pie*).
     */
    val androidOsLevel: Int,
    /**
     * Technical information and specifications about the device.
     */
    val deviceSpec: DeviceSpec
)

/**
 * Information on the latest change(s) to Location History settings on this Google account.
 */
@JsonClass(generateAdapter = true)
data class LatestLocationReportingSettingChange(
    /**
     * Time that Location History reporting was enabled or disabled for the last time on this Google account.
     */
    val reportingEnabledModificationTime: String
)

/**
 * Technical information and specifications about a device.
 */
@JsonClass(generateAdapter = true)
data class DeviceSpec(
    /**
     * Manufacturer of the device.
     */
    val manufacturer: String,
    /**
     * Brand of the device.
     */
    val brand: String,
    /**
     * Product name of the device.
     */
    val product: String,
    /**
     * Name of the device.
     */
    val device: String,
    /**
     * Model of the device.
     */
    val model: String,
    /**
     * Whether the device is considered low-ram.
     */
    val isLowRam: Boolean
)