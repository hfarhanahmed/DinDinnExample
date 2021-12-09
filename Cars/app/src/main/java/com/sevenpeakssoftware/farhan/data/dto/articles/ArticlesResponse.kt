package com.sevenpeakssoftware.farhan.data.dto.articles


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class ArticlesResponse(
    @Json(name = "status")
    val status: String = "",
    @Json(name = "content")
    val content: List<ArticlesItem> = listOf(),
    @Json(name = "serverTime")
    val serverTime: Long = 0
) : Parcelable
