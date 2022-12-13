package com.sidra.roomhilt.datalayer

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class Note(

    @PrimaryKey(autoGenerate = true)
    val id :Int = 0,
    @ColumnInfo
    val title: String = "",
    @ColumnInfo
    val detail : String = ""
) {
}