package com.xische.common.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Universities")
data class UniversityDto(
    @PrimaryKey
    val name: String,
    val country: String,
    val code: String,
    val webPage: String,
    val state: String

)
