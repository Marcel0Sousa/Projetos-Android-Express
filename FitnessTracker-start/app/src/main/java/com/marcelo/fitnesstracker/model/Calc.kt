package com.marcelo.fitnesstracker.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity
data class Calc(
    @PrimaryKey(autoGenerate = false)
    val id: Int = 0,
    @ColumnInfo("type")
    val type: String,
    @ColumnInfo("response")
    val response: Double,
    @ColumnInfo("created_date")
    val createdDate: Date = Date()
)
