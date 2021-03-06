package com.dev.clevertonsantos.mybeats.data.response

import com.dev.clevertonsantos.mybeats.data.model.Headphone
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HeadphoneResponse(
    @Json(name = "name")
    val name: String,
    @Json(name = "rating")
    val rating: Float,
    @Json(name = "value")
    val value: Float,
    @Json(name = "total_reviews")
    val total_reviews: Int,
    @Json(name = "image")
    val image: String,
    @Json(name = "connection")
    val connection: String,
    @Json(name = "Compatibility")
    val compatibility: String,
    @Json(name = "charge")
    val charge: String,
    @Json(name = "autonomy")
    val autonomy: String,
    @Json(name = "height")
    val height: String,
    @Json(name = "Capture")
    val capture: String
)

fun HeadphoneResponse.convertToHeadphone(): Headphone {
    return Headphone(name, rating, value, total_reviews, image, connection, compatibility, charge,
        autonomy, height, capture)
}

fun List<HeadphoneResponse>.convertToListHeadphone(): List<Headphone> {
    val valuesReturn: MutableList<Headphone> = mutableListOf()
    for (item in this) {
        valuesReturn.add(item.convertToHeadphone())
    }
    return valuesReturn
}