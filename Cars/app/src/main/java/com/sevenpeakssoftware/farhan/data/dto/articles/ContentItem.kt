package com.sevenpeakssoftware.farhan.data.dto.articles


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ContentItem(
    @Json(name = "type")
    val type: String = "",
    @Json(name = "subject")
    val subject: String = "",
    @Json(name = "description")
    val description: String = ""
) : Parcelable
