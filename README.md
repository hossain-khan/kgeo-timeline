[![](https://jitpack.io/v/hossain-khan/kgeo-timeline.svg)](https://jitpack.io/#hossain-khan/kgeo-timeline)

# KGeoTimeline 📍
Kotlin library for parsing Google Location History (Timeline) data.

<img width="700" alt="google-takeout-location-history" src="https://github.com/hossain-khan/google-location-history/assets/99822/64b6627e-bb9e-4c61-bc9a-f0885d0659f8">

## Run Sample App
Open the project in IntelliJ IDEA, and run the `sample/src/main/kotlin/Main.kt` [file](https://github.com/hossain-khan/kgeo-timeline/blob/main/sample/src/main/kotlin/Main.kt).

> ℹ️ **Note:** Read instructions in the [`Main.kt`](https://github.com/hossain-khan/kgeo-timeline/blob/main/sample/src/main/kotlin/Main.kt) on where to add the unzipped Google Location History data.

### Sample Output
Here is a sample output from running the app with Google Location History data.

```
Sample app for Google Location History Parser project.

🟢 Got records: 3,103,992 records.


🟢 Got semantic location history data:
...
2018=[APRIL: 276, AUGUST: 294, DECEMBER: 412, FEBRUARY: 257, JANUARY: 249, JULY: 259, JUNE: 305, MARCH: 287, MAY: 291, NOVEMBER: 250, OCTOBER: 283, SEPTEMBER: 328]
2019=[APRIL: 155, AUGUST: 359, DECEMBER: 254, FEBRUARY: 205, JANUARY: 309, JULY: 313, JUNE: 302, MARCH: 226, MAY: 203, NOVEMBER: 329, OCTOBER: 355, SEPTEMBER: 260]
2020=[APRIL: 48, AUGUST: 166, DECEMBER: 82, FEBRUARY: 244, JANUARY: 475, JULY: 107, JUNE: 90, MARCH: 191, MAY: 66, NOVEMBER: 91, OCTOBER: 172, SEPTEMBER: 148]
....

🟢 Got timeline edits: 195,444 items edited.
```

## API Documentation

📖 https://hossain-khan.github.io/kgeo-timeline/

### How to build and push API doc

API docs are automatically published to the GitHub pages site using [static workflow](https://github.com/hossain-khan/kgeo-timeline/blob/main/.github/workflows/static.yml).

> `dokkaHtml` - Generates documentation in 'html' format

# References

* https://github.com/CarlosBergillos/LocationHistoryFormat


## Related Projects
Some related projects

* https://github.com/pwall567/json-kotlin-schema
* https://github.com/pwall567/json-kotlin-schema-codegen
* https://github.com/jamesjarvis/mappyboi
* https://github.com/DovarFalcone/google-takeout-location-parser
