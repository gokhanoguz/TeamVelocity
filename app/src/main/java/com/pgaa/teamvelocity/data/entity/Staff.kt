package com.pgaa.teamvelocity.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "staff_table")
data class Staff(@PrimaryKey @ColumnInfo(name = "name") val name: String, @ColumnInfo(name = "type") val type: String)