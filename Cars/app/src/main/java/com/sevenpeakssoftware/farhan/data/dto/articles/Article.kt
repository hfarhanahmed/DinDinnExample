package com.sevenpeakssoftware.farhan.data.dto.articles

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Article(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "ingress") val ingress: String,
    @ColumnInfo(name = "image") val image: String,
    @ColumnInfo(name = "dateTime") val dateTime: String,
)