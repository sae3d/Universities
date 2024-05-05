package com.xische.list.data.source.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UniversityModel(
    @Json(name = "state-province")
    val state: String?,
    @Json(name = "name")
    val name: String,
    @Json(name = "country")
    val country: String,
    @Json(name = "alpha_two_code")
    val code: String,
    @Json(name = "web_pages")
    val webPages: List<String>
)