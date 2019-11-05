package com.pgaa.teamvelocity.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "sprint_table")
data class Sprint(
    @PrimaryKey @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "quarter") val quarter: String,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "story_points") val storyPoints: Int,
    @ColumnInfo(name = "ratio") val ratio: Double
)