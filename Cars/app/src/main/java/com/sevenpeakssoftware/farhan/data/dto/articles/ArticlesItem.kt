package com.sevenpeakssoftware.farhan.data.dto.articles


import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = false)
@Parcelize
data class ArticlesItem(
        @Json(name = "id")
        val id: Int = 0,
        @Json(name = "title")
        val title: String = "",
        @Json(name = "ingress")
        val ingress: String = "",
        @Json(name = "image")
        val image: String = "",
        @Json(name = "dateTime")
        val dateTime: String = "",
        @Json(name = "content")
        val content: List<ContentItem>? = listOf()
) : Parcelable
